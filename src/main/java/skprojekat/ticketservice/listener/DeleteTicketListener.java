package skprojekat.ticketservice.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DeleteTicketListener {

	@JmsListener(destination="${destination.delete_flight}", concurrency = "5-10")
	public void handleDeleteTicket(Message message) {
		try {
			String jsonText = ((TextMessage)message).getText();
			System.out.println("alalh");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
