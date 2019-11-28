package de.loosecoupling.assignment3.wholesaler.rest;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.loosecoupling.assignment3.wholesaler.messaging.MessageProducer;

@RestController
@RequestMapping(value = "/wholesaler")
public class WholesalerController {
	
	@Autowired
	MessageProducer producer;

	@PostMapping(value = "/newProductOffer")
	public ResponseEntity<?> newProductOffer(@RequestParam(required = true) String productName,
			@RequestParam(required = true) Float newPrice, @RequestParam(required = false) Float currentPrice) throws JMSException {
		producer.sendMessage(productName, newPrice, currentPrice);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
