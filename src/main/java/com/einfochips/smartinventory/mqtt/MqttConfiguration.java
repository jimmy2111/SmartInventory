package com.einfochips.smartinventory.mqtt;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.ProductRack;
import com.einfochips.smartinventory.mongorepository.ProductRackRepository;
import com.einfochips.smartinventory.service.ProductService;

import jakarta.annotation.PreDestroy;


@Configuration
public class MqttConfiguration implements MqttCallback{
	private static final Logger logger=LoggerFactory.getLogger(MqttConfiguration.class);
	
	@Value("${smartinventory.mqtt.brokerUrl}")
	private String brokerUrl;
	@Value("${smartinventory.mqtt.clientId}")
	private String clientId;
	@Value("${smartinventory.mqtt.username}")
	private String username;
	@Value("${smartinventory.mqtt.password}")
	private String password;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRackRepository productRackRepository;
	
	@Bean
	public MqttClient mqttClient() throws Exception {
	    MqttClient mqttClient = new MqttClient(brokerUrl, clientId);
	    MqttConnectOptions connectOptions = new MqttConnectOptions();
	    connectOptions.setUserName(username);
	    connectOptions.setPassword(password.toCharArray());
	    mqttClient.connect(connectOptions);
	    mqttClient.setCallback(this);
	    if(mqttClient.isConnected()) {
	    logger.info("Client Connected with Broker");
	    mqttClient.subscribe("product/rack");
	    }
	    return mqttClient;
		
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Message from Device");
		String payload = new String(message.getPayload());
        logger.info("Message passed to subscribed client");
        JSONObject jsonObject = new JSONObject(payload);
        ProductRack productRack = new ProductRack();
        productRack.setRackId(jsonObject.getLong("rackId"));
        productRack.setProductId(jsonObject.getLong("productId"));
        productRack.setName(jsonObject.getString("name"));
        productRack.setQuantity(jsonObject.getInt("quantity"));
        productRackRepository.save(productRack);
        
        Product product = productService.getProductById(jsonObject.getLong("productId")); 
		int remquantity = product.getQuantity() - jsonObject.getInt("quantity");
		product.setQuantity(remquantity);
		productService.updateProduct(product.getId(), product);
		logger.info("Updated Product Quantity");
	}
	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}
	
}
