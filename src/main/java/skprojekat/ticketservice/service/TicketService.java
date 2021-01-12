package skprojekat.ticketservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import skprojekat.ticketservice.dto.TicketCreateDto;
import skprojekat.ticketservice.model.Ticket;

import java.util.List;

@Service
public interface TicketService {

	void deleteAllByFlightId(Integer id);

	List<Ticket> findAllByUserId(String authorization);
	
	void deleteById(Integer id);
	
	Ticket add(String authorization, Integer flightId);
}
