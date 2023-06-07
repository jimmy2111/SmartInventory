package com.einfochips.smartinventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private static final Logger logger=LoggerFactory.getLogger(WebConfig.class);
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		logger.info("Setting CORS Registry");
		registry.addMapping("/**")
        .allowedOrigins("http://localhost:4200") // Add the origin of your Angular application
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("*");
	}
	

}
