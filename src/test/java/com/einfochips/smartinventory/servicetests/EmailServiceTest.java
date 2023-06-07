package com.einfochips.smartinventory.servicetests;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.EmailService;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

	@InjectMocks
	private EmailService emailService;
	@Mock
	private JavaMailSender javaMailSender;
	
	private SimpleMailMessage getExpectedMailMessage(String to, String subject, String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		return mailMessage;
	}
	@Test
	public void sendEmailTest() {
		String to = "example@example.com";
		String subject= "Below Threshold Level";
		String message = "Kindly update the Product Quantity";
		
		emailService.sendEmail(to, subject, message);
		verify(javaMailSender).send(getExpectedMailMessage(to, subject, message));
	}
	@Test
	public void sendAlertEmailTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        String to = "example@example.com";
		String subject= "Below Threshold Level";
		String message = "Kindly update the Product Quantity";
		
		emailService.sendAlertEmail(products.get(0), to, subject, message);
		verify(javaMailSender).send(getExpectedMailMessage(to, subject, message));
	}
}
