package skprojekat.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skprojekat.ticketservice.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

	void deleteAllByFlightId(Integer id);
	
}
