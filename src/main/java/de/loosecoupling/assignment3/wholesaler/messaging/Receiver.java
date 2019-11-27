package de.loosecoupling.assignment3.wholesaler.messaging;

import javax.jms.JMSException;
import javax.jms.StreamMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

	@JmsListener(destination = "buyOrdersTopic", containerFactory = "buyOrders")
	public void receiveMessage(StreamMessage message) {
        try {
        	LOG.info("------------------------------");
			LOG.info("Got new product order");
			LOG.info("Product: " + message.readString());
			LOG.info("Amount: " + message.readInt());
			LOG.info("Offer-ID: " + message.getJMSCorrelationID());
		} catch (JMSException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
}
