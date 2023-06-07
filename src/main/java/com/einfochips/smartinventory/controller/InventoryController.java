package com.einfochips.smartinventory.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.einfochips.smartinventory.model.Inventory;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.service.InventoryService;

@RestController
@CrossOrigin
public class InventoryController {
	private static final Logger logger=LoggerFactory.getLogger(InventoryController.class);
	
	@Autowired
	private InventoryService inventoryservice;
	
	@GetMapping("/viewallinventory")
	public ModelAndView viewAllInventory(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		ModelAndView modelAndView = new ModelAndView("inventory");
		logger.info("Getting All Inventory");
		Page<Inventory> page = inventoryservice.getAllInventoryByPagingAndSorting(pageNo, pageSize, sortField, sortOrder);
		modelAndView.addObject("inventory", page.getContent());
		modelAndView.addObject("totalPages", page.getTotalPages());
		modelAndView.addObject("currentPage", pageNo);
		modelAndView.addObject("sortField", sortField);
		modelAndView.addObject("sortOrder", sortOrder);
		modelAndView.addObject("revSortOrder", sortOrder.equalsIgnoreCase("asc")?"desc":"asc");
		logger.info("All Inventory Displayed");
		return modelAndView;
	}
	
	@GetMapping("/searchinventory")
	public ModelAndView searchInventory(@RequestParam String name, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder) throws Throwable {
		ModelAndView modelAndView = new ModelAndView("inventory");
		logger.info("Searching for Inventory");
		Page<Inventory> page = inventoryservice.searchInventory(name, pageNo, pageSize, sortField, sortOrder);
		if(page.isEmpty()) {
			throw new Exception("Inventory Not Found");
		}
		modelAndView.addObject("inventory", page.getContent());
		modelAndView.addObject("totalPages", page.getTotalPages());
		modelAndView.addObject("currentPage", pageNo);
		modelAndView.addObject("sortField", sortField);
		modelAndView.addObject("sortOrder", sortOrder);
		modelAndView.addObject("revSortOrder", sortOrder.equalsIgnoreCase("asc")?"desc":"asc");
		logger.info("Found Inventory...");
		return modelAndView;
	}
	@GetMapping("/viewinventory")
	public List<Inventory> viewInventory(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder){
		return inventoryservice.getAllInventoryByPagingAndSorting(pageNo, pageSize, sortField, sortOrder).toList();
	}
	@PostMapping("/searchforinventory")
	public List<Inventory> searchForInventory(@RequestParam String name,@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "4") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder) throws Exception{
		logger.info("Searching for Inventory");
		Page<Inventory> page = inventoryservice.searchInventory(name, pageNo, pageSize, sortField, sortOrder);
		if(page.isEmpty()) {
			throw new Exception("Inventory is Not Found");
		}
		logger.info("Found Inventory...");
		return page.getContent();
		
	}

}
