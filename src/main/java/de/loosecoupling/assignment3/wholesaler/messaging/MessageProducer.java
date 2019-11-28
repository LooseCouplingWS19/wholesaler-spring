package de.loosecoupling.assignment3.wholesaler.messaging;

import javax.jms.JMSException;
import javax.jms.StreamMessage;
import javax.jms.TopicPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

	@Autowired
	private MessageFactory messageGenerator;
	@Autowired
	private TopicPublisher topicPublisher;

	public void sendMessage(String productName, Float newPrice, Float currentPrice) throws JMSException {
		StreamMessage message = messageGenerator.generateWholesalerMessage();
		message = messageGenerator.fillWholesalerMessage(productName, newPrice, currentPrice, message);
		topicPublisher.send(message);
	}
}
