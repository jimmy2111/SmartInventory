package com.einfochips.smartinventory.controller;

import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.einfochips.smartinventory.logging.LoggingAspect;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.ProductRack;
import com.einfochips.smartinventory.mongorepository.ProductRackRepository;
import com.einfochips.smartinventory.mqtt.MqttConfiguration;
import com.einfochips.smartinventory.service.ProductService;
import com.einfochips.smartinventory.userrepository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@RestController
public class MqttController{
	private static final Logger logger=LoggerFactory.getLogger(MqttController.class);
	@Autowired
	private ProductRackRepository productRackRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private MqttClient mqttClient;
	@Autowired
	private ObjectMapper objectMapper;
	
//	@PostMapping("publish-message")
//	public ProductRack publishMessage(@RequestBody ProductRack productRack) throws Exception, MqttException {
//		String topic = "test/topic";
//		String message = objectMapper.writeValueAsString(productRack);
//	    MqttMessage mqttMessage = new MqttMessage(message.getBytes());
//	    mqttClient.publish(topic, mqttMessage);
//	    logger.info("Message published by Client");
//		return productRackRepository.save(productRack);
//	}
	@PostConstruct
	public String subscribe() throws MqttException {
		logger.info("Subscribe method called ");
		String topic = "device/topic";
		   mqttClient.subscribe(topic, (t, m) -> {
		        logger.info("Message passed to subscribed client");
		    });
		   return "Subscribed";
	}
	@GetMapping("publishfordevice/{rackId}")
	public List<ProductRack> publishForDevice(@PathVariable Long rackId) throws Exception, MqttException {
		String topic = "device/topic";
		List<ProductRack> productRack = productRackRepository.findAllByRackId(rackId);
		String message = objectMapper.writeValueAsString(productRack);
	    MqttMessage mqttMessage = new MqttMessage(message.getBytes());
	    mqttClient.publish(topic, mqttMessage);
	    logger.info("Message published for Device");
	    return productRack;
	}
}
