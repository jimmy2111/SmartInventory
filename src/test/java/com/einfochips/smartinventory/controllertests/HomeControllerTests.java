package com.einfochips.smartinventory.controllertests;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.einfochips.smartinventory.controller.HomeController;
import com.einfochips.smartinventory.service.ProductService;
import com.einfochips.smartinventory.service.UserServiceImpl;
import com.einfochips.smartinventory.userrepository.UserRepository;
@ExtendWith(SpringExtension.class)
@WebMvcTest(value=HomeController.class)
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class HomeControllerTests {
	
	@InjectMocks
    private HomeController userController;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserRepository userRepo;
	@MockBean
	private UserServiceImpl userService;
	@MockBean
	private ProductService productService;
	@Test
	public void homePageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/index");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("index"))
				.andReturn();
	}
	@Test
	public void indexPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("error"))
				.andReturn();
	}
	@Test
	public void loginPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/login");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				//.andExpect(view().name("login"))
				.andReturn();
		
	}
	@Test
	public void registrationPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/register");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("registration"))
				.andReturn();
		
	}
	@Test
	public void OrderPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/order");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("order"))
				.andReturn();
		
	}
}
