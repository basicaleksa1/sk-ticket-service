package skprojekat.ticketservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import skprojekat.ticketservice.dto.TicketCreateDto;
import skprojekat.ticketservice.model.Ticket;
import skprojekat.ticketservice.service.TicketService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {

	private TicketService ticketService;
	
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@GetMapping
	public ResponseEntity<List<Ticket>> findAll(@RequestHeader("Authorization") String authorization){
		return new ResponseEntity<>(ticketService.findAllByUserId(authorization), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Ticket> add(@RequestHeader("Authorization") String authorization, Integer flightId){
		return new ResponseEntity<Ticket>(ticketService.add(authorization, flightId), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		ticketService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
