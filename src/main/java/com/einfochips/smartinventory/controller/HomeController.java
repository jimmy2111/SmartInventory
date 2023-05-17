package com.einfochips.smartinventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.einfochips.smartinventory.model.Inventory;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.User;
import com.einfochips.smartinventory.service.InventoryService;
import com.einfochips.smartinventory.service.ProductService;
import com.einfochips.smartinventory.service.UserServiceImpl;
//import com.einfochips.smartinventory.service.UserServiceImpl;
import com.einfochips.smartinventory.userrepository.UserRepository;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
public class HomeController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public ModelAndView getIndex(OAuth2AuthenticationToken token) {
//		String email = token.getPrincipal().getAttribute("email");
//		String name = token.getPrincipal().getAttribute("name");
//		if(email.equalsIgnoreCase("pateljimi21@gmail.com")) {
//			token.getAuthorities().add(new SimpleGrantedAuthority("OIDC_ADMIN"));
//		}
//		userService.createUserWithRole(email, name,"ROLE_USER");
	    return new ModelAndView("index");
	}
	
	@GetMapping("/index")
	public ModelAndView getHomePage() {
		return new ModelAndView("index");
	}
	
	@GetMapping("/register")
	public ModelAndView getRegistrationPage() {
		return new ModelAndView("registration");
	}
	
	@GetMapping("/login")
	public ModelAndView getLoginPage() {
		return new ModelAndView("login");
	}
	
	@PostMapping("/registerUser")
	public ModelAndView registerUser(@ModelAttribute User u) {
		ModelAndView modelAndView = new ModelAndView("login");
		User user = userRepo.findById(u.getEmail()).orElse(null);
		if(user==null) {
		userRepo.save(u);
		}
		else {
			modelAndView.addObject("error", "User with this email id already exists, Login here");
		}
		return modelAndView;
	}
//	@RequestMapping("validateUser")
//	public ModelAndView login(@RequestParam String email,@RequestParam String password,Model model) {
//		User user = userRepo.findByEmailAndPassword(email, password);
//		if(user!=null) {
//			return new ModelAndView("index");
//		}
//		else {
//			ModelAndView modelAndView = new ModelAndView("login");
//			model.addAttribute("error", "Invalid Email or Password");
//			return modelAndView; 
//		}
//	}
	
	@GetMapping("/order")
	public ModelAndView getOrderPage() {
		ModelAndView modelAndView = new ModelAndView("order");
		List<Product> products = productService.getAllProducts();
		modelAndView.addObject("products", products);
		return modelAndView;
	}	
}
