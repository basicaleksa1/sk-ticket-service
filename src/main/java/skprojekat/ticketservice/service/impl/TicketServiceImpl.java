package skprojekat.ticketservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import skprojekat.ticketservice.model.Ticket;
import skprojekat.ticketservice.repository.TicketRepository;
import skprojekat.ticketservice.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

	private TicketRepository ticketRepository;
	private JmsTemplate jmsTemplate;
	
	@Override
	public void deleteAllByFlightId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Ticket> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ticket add(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}

}
