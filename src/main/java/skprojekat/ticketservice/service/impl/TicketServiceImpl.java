package skprojekat.ticketservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import skprojekat.ticketservice.client.flightservice.FlightDto;
import skprojekat.ticketservice.dto.TicketCreateDto;
import skprojekat.ticketservice.mapper.TicketMapper;
import skprojekat.ticketservice.model.Ticket;
import skprojekat.ticketservice.repository.TicketRepository;
import skprojekat.ticketservice.service.TicketService;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

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
	public Page<Ticket> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public Ticket add(TicketCreateDto ticket) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.ALL);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpZCI6Mywicm9sZSI6IlJPTEVfQURNSU4ifQ.iXVxrQc6ZkA2hjb7RkRAyufXn1E_n4qRbmi2n272kLBq4ynKubKPA4S2e7kcY1Td4IqYMlM88IUvlg-duDO6ug");

		final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,
				headers);

		ResponseEntity<FlightDto> flightDto = flightServiceRestTemplate.exchange("/flight/" + ticket.getFlightId(),
				HttpMethod.GET, entity, FlightDto.class);
		System.out.println(flightDto);
		ticketRepository.save(ticketMapper.ticketDtoToTicket(ticket));
		return ticketMapper.ticketDtoToTicket(ticket);
	}

}
