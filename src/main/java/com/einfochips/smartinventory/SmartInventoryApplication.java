package com.einfochips.smartinventory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.einfochips.smartinventory.controller.MqttController;

@SpringBootApplication
public class SmartInventoryApplication {
	
	public static void main(String[] args) throws MqttException {
		SpringApplication.run(SmartInventoryApplication.class, args);
	}
}
