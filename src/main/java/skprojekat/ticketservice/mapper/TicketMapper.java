package skprojekat.ticketservice.mapper;

import org.springframework.stereotype.Component;

import skprojekat.ticketservice.dto.TicketDto;
import skprojekat.ticketservice.model.Ticket;

@Component
public class TicketMapper {

	public TicketDto ticketToTicketDto(Ticket ticket) {
		TicketDto ticketDto = new TicketDto(ticket.getFlightId(), ticket.getUserId());
		return ticketDto;
	}
	
	public Ticket ticketDtoToTicket(TicketDto ticketDto) {
		Ticket ticket = new Ticket();
		ticket.setFlightId(ticketDto.getFlightId());
		ticket.setUserId(ticketDto.getUserId());
		return ticket;
	}
	
}
