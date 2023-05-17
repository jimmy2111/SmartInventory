package com.einfochips.smartinventory.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.mqtt.MqttConfiguration;

@Service
@EnableScheduling
public class EmailScheduler {
	private static final Logger logger=LoggerFactory.getLogger(EmailScheduler.class);
	@Autowired
	private ProductService productService;
	@Autowired 
	private EmailService emailService;
	@Value("${smartinventory.mail.to}")
	private String to;
	@Value("${smartinventory.mail.subject}")
	private String sub;
	@Value("${smartinventory.mail.message}")
	private String body;
	
	@Scheduled(fixedDelay = 60*60*1000)
	public void checkProductQuantity() {
		List<Product> products = productService.getAllProducts();
		for(Product product: products) {
			if(product.getQuantity()<product.getThresholdQuantity()) {
				String subject = sub+" "+product.getId()+" "+product.getName();
				String message = body+" "+product.getId()+" "+product.getName();
				emailService.sendAlertEmail(product, to, subject, message);
				logger.info("Email Sent Successfully");
			}
		}
	}

}
