package skprojekat.ticketservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import skprojekat.ticketservice.dto.TicketCreateDto;
import skprojekat.ticketservice.model.Ticket;

@Service
public interface TicketService {

	void deleteAllByFlightId(Integer id);
	
	Page<Ticket> findAll(Pageable pageable);
	
	void deleteById(Integer id);
	
	Ticket add(TicketCreateDto ticket);
}
