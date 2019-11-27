//package de.loosecoupling.assignment3.wholesaler.services;
//
//import javax.annotation.PostConstruct;
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSContext;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class InitService {
//
//	@Autowired
//	JmsTemplate myJmsTemplate;
//	
//	@PostConstruct
//	private void init() {
//		ConnectionFactory connectionFactory = myJmsTemplate.getConnectionFactory();
//		JMSContext context = connectionFactory.createContext();
//	}
//}
