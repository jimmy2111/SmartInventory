package com.einfochips.smartinventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.einfochips.smartinventory.model.Product;


@Service
public class EmailService {
	private static final Logger logger=LoggerFactory.getLogger(EmailService.class);
	@Autowired
	private JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	
	public void sendEmail(String to,String subject,String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		javaMailSender.send(mailMessage);
		logger.info("Email Sent Successfully");
	}
	
	public void sendAlertEmail(Product product,String to,String subject,String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		javaMailSender.send(mailMessage);
		logger.info("Alert on Product Quantity Sent Successfully");
	}
}
