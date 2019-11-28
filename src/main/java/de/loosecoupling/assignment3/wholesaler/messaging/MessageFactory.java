package de.loosecoupling.assignment3.wholesaler.messaging;

import javax.jms.JMSException;
import javax.jms.StreamMessage;
import javax.jms.TemporaryTopic;
import javax.jms.Topic;
import javax.jms.TopicSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory {
	
	@Autowired
	Topic hotDealsTopic;
	@Autowired
	TemporaryTopic buyOrdersTopic;
	@Autowired
	TopicSession activeMQSession;

	public StreamMessage generateWholesalerMessage() throws JMSException {
		StreamMessage message = activeMQSession.createStreamMessage();
		message.setJMSDestination(hotDealsTopic);
		message.setJMSReplyTo(buyOrdersTopic);
		return message;
		
	}
	
	public StreamMessage fillWholesalerMessage(String productName, Float newPrice, Float currentPrice, StreamMessage message) throws JMSException {
		message.writeString(productName);
		message.writeFloat(newPrice);
		// If current price does not exist -> new product -> currentPrice = newPrice
		if (currentPrice == null) {
			message.writeFloat(newPrice);
		} else {
			message.writeFloat(currentPrice);
		}
		return message;
	}
}
