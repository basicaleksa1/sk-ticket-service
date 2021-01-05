package skprojekat.ticketservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
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
//	private JmsTemplate jmsTemplate;
	
	public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper) {
		super();
		this.ticketRepository = ticketRepository;
		this.ticketMapper = ticketMapper;
//		this.jmsTemplate = jmsTemplate;
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

		ticketRepository.save(ticketMapper.ticketDtoToTicket(ticket));
		return ticketMapper.ticketDtoToTicket(ticket);
	}

}
