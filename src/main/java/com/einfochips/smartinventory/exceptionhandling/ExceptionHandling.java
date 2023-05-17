package com.einfochips.smartinventory.exceptionhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
@Component
public class ExceptionHandling {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		logger.error("An error occured "+ex.getMessage(),ex);
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage",ex.getMessage());
		return modelAndView;
	}
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleRunTimeException(RuntimeException ex) {
		logger.error("An error occured "+ex.getMessage());
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage",ex.getMessage());
		return modelAndView;
	}
	

}
