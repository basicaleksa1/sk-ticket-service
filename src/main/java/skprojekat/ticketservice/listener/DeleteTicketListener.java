package skprojekat.ticketservice.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DeleteTicketListener {
	
	private ObjectMapper objectMapper;
	
	public DeleteTicketListener(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@JmsListener(destination="${destination.delete_flight}", concurrency = "5-10")
	public void handleDeleteTicket(Message message) {
		try {
			String jsonText = ((TextMessage)message).getText();
			try {
				Integer id = objectMapper.readValue(jsonText, Integer.class);
				System.out.println(id);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
