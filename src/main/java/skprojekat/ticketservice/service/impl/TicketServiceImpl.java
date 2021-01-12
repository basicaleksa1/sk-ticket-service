package skprojekat.ticketservice.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import skprojekat.ticketservice.client.flightservice.FlightDto;
import skprojekat.ticketservice.client.userservice.UserDto;
import skprojekat.ticketservice.dto.TicketCreateDto;
import skprojekat.ticketservice.mapper.TicketMapper;
import skprojekat.ticketservice.model.Ticket;
import skprojekat.ticketservice.repository.TicketRepository;
import skprojekat.ticketservice.service.TicketService;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

	@Value("${oauth.jwt.secret}")
	private String jwtSecret;

	private TicketRepository ticketRepository;
	private TicketMapper ticketMapper;
	private RestTemplate flightServiceRestTemplate;
	private RestTemplate userServiceRestTemplate;

//	private JmsTemplate jmsTemplate;
	
	public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper,
							 RestTemplate flightServiceRestTemplate, RestTemplate userServiceRestTemplate) {
		super();
		this.ticketRepository = ticketRepository;
		this.ticketMapper = ticketMapper;
		this.flightServiceRestTemplate = flightServiceRestTemplate;
//		this.jmsTemplate = jmsTemplate;
		this.userServiceRestTemplate = userServiceRestTemplate;
	}

	@Override
	public void deleteAllByFlightId(Integer id) {
		List<Ticket> tickets = ticketRepository.findAllByFlightId(id);
		for(Ticket ticket: tickets){
			ticket.setStatus("CANCELED");
			ticketRepository.save(ticket);
		}
	}

	@Override
	public List<Ticket> findAllByUserId(String authorization) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authorization.split(" ")[1]).getBody();
		Integer id = claims.get("id", Integer.class);

		return ticketRepository.findAllByUserId(id);
	}

	@Override
	public void deleteById(Integer id) {
		ticketRepository.deleteById(id);
	}


	@Override
	public Ticket add(String authorization, Integer flightId) {
		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", authorization);
		HttpEntity<Void> entity = new HttpEntity<>(null, headers);

		ResponseEntity<FlightDto> flightDto = flightServiceRestTemplate.exchange("/flight/" + flightId,
				HttpMethod.GET, entity, FlightDto.class);

		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authorization.split(" ")[1]).getBody();
		Integer userId = claims.get("id", Integer.class);

		ResponseEntity<UserDto> userDto = userServiceRestTemplate.exchange("/user/" + userId,
				HttpMethod.GET, entity, UserDto.class);

		UserDto korisnik = userDto.getBody();

		FlightDto let = flightDto.getBody();

		Double ticketPrice = let.getPrice() - let.getPrice() * korisnik.getRank().getPopust() / 100;

		TicketCreateDto ticket = new TicketCreateDto(let.getId(), korisnik.getId(), "ACTIVE", ticketPrice);

		ResponseEntity<FlightDto> updateFlight = flightServiceRestTemplate.exchange("/flight/" + flightId,
				HttpMethod.PUT, entity, FlightDto.class);

		double miles = let.getDistance();
		int milje = (int)miles;
		System.out.println(milje);

		HttpEntity<Integer> entitet = new HttpEntity<Integer>((Integer)milje, headers);



		ResponseEntity<UserDto> updateUser = userServiceRestTemplate.exchange("/user/updateMiles", HttpMethod.PUT,
				entitet, UserDto.class);

		System.out.println(flightDto.getBody().getId());
		Ticket karta = ticketMapper.ticketDtoToTicket(ticket);

		ticketRepository.save(ticketMapper.ticketDtoToTicket(ticket));
		return ticketMapper.ticketDtoToTicket(ticket);
	}

	private void getInfo(){

	}
}
