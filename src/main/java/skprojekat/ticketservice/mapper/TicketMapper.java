package skprojekat.ticketservice.mapper;

import org.springframework.stereotype.Component;

import skprojekat.ticketservice.dto.TicketCreateDto;
import skprojekat.ticketservice.model.Ticket;

@Component
public class TicketMapper {

	public TicketCreateDto ticketToTicketDto(Ticket ticket) {
		TicketCreateDto ticketCreateDto = new TicketCreateDto(ticket.getFlightId(), ticket.getUserId(), ticket.getStatus(), ticket.getPrice());
		return ticketCreateDto;
	}
	
	public Ticket ticketDtoToTicket(TicketCreateDto ticketCreateDto) {
		Ticket ticket = new Ticket();
		ticket.setFlightId(ticketCreateDto.getFlightId());
		ticket.setUserId(ticketCreateDto.getUserId());
		ticket.setStatus("ACTIVE");
		return ticket;
	}
	
}
