package com.einfochips.smartinventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.SupplierService;

@RestController
public class SupplierController {
	private static final Logger logger=LoggerFactory.getLogger(SupplierController.class);
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/addSuppliers")
	public ModelAndView getAddSupplierPage() {
		logger.info("Display Add Suppliers Page");
		return new ModelAndView("addSupplier");
	}
	@PostMapping("/savesupplier")
	public ModelAndView saveSupplier(@ModelAttribute Supplier supplier) {
		ModelAndView modelAndView = new ModelAndView("addSupplier");
		modelAndView.addObject("supplier1", supplier);
		if(supplier.getId()!= null) {
		Supplier existingSupplier = supplierService.getSupplierById(supplier.getId());
		if (existingSupplier!=null){
			logger.info("Updating the existing supplier");
			supplierService.updateSupplier(supplier.getId(), existingSupplier);
			modelAndView.addObject("success", "user already exists");
		}
		}
		else {
			logger.info("Creating New Supplier");
			supplierService.createSupplier(supplier);
			logger.info("New Supplier Created");
			modelAndView.addObject("success", "Added Successfully");
		}
		return modelAndView;
	}
	@GetMapping("/viewsuppliers")
	public ModelAndView viewAllSuppliers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "4") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		ModelAndView modelAndView = new ModelAndView("suppliers");
		Page<Supplier> page = supplierService.getSupplierByPagingAndSorting(pageNo, pageSize, sortField, sortOrder);
		modelAndView.addObject("suppliers", page.getContent());
		modelAndView.addObject("totalPages", page.getTotalPages());
		modelAndView.addObject("currentPage", pageNo);
		modelAndView.addObject("sortField", sortField);
		modelAndView.addObject("sortOrder", sortOrder);
		modelAndView.addObject("revSortOrder", sortOrder.equalsIgnoreCase("asc")?"desc":"asc");
		return modelAndView;
	}
	@GetMapping("/searchsupplier")
	public ModelAndView searchSupplier(@RequestParam String name,@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "4") Integer pageSize,
			@RequestParam(defaultValue = "name") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder) throws Exception {
		ModelAndView modelAndView= new ModelAndView("suppliers");
		logger.info("Searching for Supplier");
		Page<Supplier> page = supplierService.searchSupplier(name, pageNo, pageSize, sortField, sortOrder);
		if(page.isEmpty()) {
			throw new Exception("Supplier Not Found");
		}
		logger.info("Found Supplier");
		modelAndView.addObject("suppliers", page.getContent());
		modelAndView.addObject("totalPages", page.getTotalPages());
		modelAndView.addObject("currentPage", pageNo);
		modelAndView.addObject("sortField", sortField);
		modelAndView.addObject("sortOrder", sortOrder);
		modelAndView.addObject("revSortOrder", sortOrder.equalsIgnoreCase("asc")?"desc":"asc");
		return modelAndView;
	}

}