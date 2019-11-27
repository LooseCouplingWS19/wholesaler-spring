package de.loosecoupling.assignment3.wholesaler.messaging;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.StreamMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private JmsTemplate myJmsTemplate;
	@Autowired
	private Destination buyOrdersDestination;

	public void sendMessage(String productName, Float newPrice, Float currentPrice) {
		myJmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				StreamMessage streamMessage = session.createStreamMessage();
				streamMessage.writeString(productName);
				streamMessage.writeFloat(newPrice);
				// If current price does not exist -> new product -> currentPrice = newPrice
				if (currentPrice == null) {
					streamMessage.writeFloat(newPrice);
				} else {
					streamMessage.writeFloat(currentPrice);
				}
				streamMessage.setJMSReplyTo(buyOrdersDestination);
				return streamMessage;
			}
		});
	}
}
