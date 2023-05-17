package com.einfochips.smartinventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.einfochips.smartinventory.service.InventoryService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
	private static final Logger logger=LoggerFactory.getLogger(CustomErrorController.class);
	@GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        String errorPage = "error"; // default
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
             
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // handle HTTP 404 Not Found error
            	model.addAttribute("statusCode", HttpStatus.NOT_FOUND.value());
            	model.addAttribute("statusMessage",HttpStatus.NOT_FOUND.getReasonPhrase());
            	logger.info("Rendered 404 Error Page");
                errorPage = "error/404";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                // handle HTTP 403 Forbidden error
            	model.addAttribute("statusCode", HttpStatus.FORBIDDEN.value());
            	model.addAttribute("statusMessage",HttpStatus.FORBIDDEN.getReasonPhrase());
            	logger.info("Rendered 403 Error Page");
                errorPage = "error/403";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // handle HTTP 500 Internal Server error
            	model.addAttribute("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            	model.addAttribute("statusMessage",HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            	logger.info("Rendered 500 Error Page");
                errorPage = "error/500";
                 
            }
            else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                // handle HTTP 500 Internal Server error
            	model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.value());
            	model.addAttribute("statusMessage",HttpStatus.UNAUTHORIZED.getReasonPhrase());
            	logger.info("Rendered 401 Error Page");
                errorPage = "error/401";   
            }
        }
         
        return errorPage;
    }
}
