package de.loosecoupling.assignment3.wholesaler.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageSubscriber implements MessageListener {

	private static final Logger LOG = LoggerFactory.getLogger(MessageSubscriber.class);

	@Override
	public void onMessage(Message message) {
		StreamMessage streamMessage = (StreamMessage) message;
		try {
			LOG.info("------------------------------");
			LOG.info("Got new product order");
			LOG.info("Product: " + streamMessage.readString());
			LOG.info("Amount: " + streamMessage.readInt());
			LOG.info("Offer-ID: " + streamMessage.getJMSCorrelationID());
		} catch (JMSException e) {
			LOG.error(e.getMessage(), e);
		}			
	}
}
