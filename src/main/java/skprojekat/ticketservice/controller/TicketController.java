package skprojekat.ticketservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import skprojekat.ticketservice.dto.TicketDto;
import skprojekat.ticketservice.model.Ticket;
import skprojekat.ticketservice.service.TicketService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/ticket")
public class TicketController {

	private TicketService ticketService;
	
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@GetMapping
	public ResponseEntity<Page<Ticket>> findAll(@ApiIgnore Pageable pageable){
		return new ResponseEntity<>(ticketService.findAll(pageable), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Ticket> add(@RequestBody TicketDto ticket){
		return new ResponseEntity<Ticket>(ticketService.add(ticket), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		ticketService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
