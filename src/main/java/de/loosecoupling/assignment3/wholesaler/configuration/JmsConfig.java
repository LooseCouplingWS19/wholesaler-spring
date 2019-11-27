package de.loosecoupling.assignment3.wholesaler.configuration;

import javax.jms.DeliveryMode;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
public class JmsConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	@Value("${spring.activemq.user}")
	private String brokerUser;
	@Value("${spring.activemq.password}")
	private String brokerPassword;

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		activeMQConnectionFactory.setUserName(brokerUser);
		activeMQConnectionFactory.setPassword(brokerPassword);

		return activeMQConnectionFactory;
	}

	@Bean(name = "buyOrders")
	public DefaultJmsListenerContainerFactory buyOrders() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(activeMQConnectionFactory());
		factory.setPubSubDomain(true);
		factory.setSubscriptionDurable(true);

		return factory;
	}
	
	@Bean 
	public Destination buyOrdersDestination() {
		return new ActiveMQTopic("buyOrdersTopic");
	}
	
	@Bean 
	public Destination hotDealsDestination() {
		return new ActiveMQTopic("HotDeals");
	}
	
	@Bean
	public JmsTemplate myJmsTemplate() {
		JmsTemplate myTemplate = new JmsTemplate();
		myTemplate.setDefaultDestination(hotDealsDestination());
		myTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
		myTemplate.setConnectionFactory(activeMQConnectionFactory());
		myTemplate.setPubSubDomain(true);
		myTemplate.setMessageIdEnabled(true);
		return myTemplate;
	}
	
}
