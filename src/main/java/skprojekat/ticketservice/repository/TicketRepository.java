package skprojekat.ticketservice.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skprojekat.ticketservice.model.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

	void deleteAllByFlightId(Integer id);

	List<Ticket> findAllByFlightId(Integer id);

	List<Ticket> findAllByUserId(Integer id);

//	Optional<Ticket> findByFlightIdEquals(Integer id);
}
