package com.einfochips.smartinventory.logging;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger=LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(public * com.einfochips.smartinventory.controller.ProductController.getAllProductsByPagingAndSorting(..))")
	public void logBeforeViewProducts() {
		logger.info("View Products Called");
	}
	@AfterReturning("execution(public * com.einfochips.smartinventory.controller.ProductController.getAllProductsByPagingAndSorting(..))")
	public void logAfterViewProducts() {
		logger.info("View Products Executed");
	}
	@AfterThrowing("execution(public * com.einfochips.smartinventory.controller.ProductController.getAllProductsByPagingAndSorting(..))")
	public void logAfterViewProductsException() {
		logger.error("Exception in View Products ");
	}
	@Before("execution(public * com.einfochips.smartinventory.controller.ProductController.searchProduct(..))")
	public void logBeforeSearchProduct() {
		logger.info("Search Product Called ");
	}
	@AfterReturning("execution(public * com.einfochips.smartinventory.controller.ProductController.searchProduct(..))")
	public void logAfterSearchProduct() {
		logger.info("Search Product Executed");
	}
	@AfterThrowing("execution(public * com.einfochips.smartinventory.controller.ProductController.searchProduct(..))")
	public void logAfterSearchProductException() {
		logger.error("Exception in Search Product ");
	}
	@Before("execution(public * com.einfochips.smartinventory.controller.InventoryController.viewAllInventory(..))")
	public void logBeforeViewInventory() {
		logger.info("View Inventory Called");
	}
	@AfterReturning("execution(public * com.einfochips.smartinventory.controller.InventoryController.viewAllInventory(..))")
	public void logAfterViewInventory() {
		logger.info("View Inventory Executed");
	}
	@Before("execution(public * com.einfochips.smartinventory.controller.SupplierController.viewAllSuppliers(..))")
	public void logBeforeViewSuppliers() {
		logger.info("View Supplier Called");
	}
	@AfterReturning("execution(public * com.einfochips.smartinventory.controller.SupplierController.viewAllSuppliers(..))")
	public void logAfterViewSuppliers() {
		logger.info("View Supplier Executed");
	}
	@Before("execution(public * com.einfochips.smartinventory.controller.SupplierController.searchSupplier(..))")
	public void logBeforeSearchSupplier() {
		logger.info("Search Supplier Called ");
	}
	@AfterReturning("execution(public * com.einfochips.smartinventory.controller.SupplierController.searchSupplier(..))")
	public void logAfterSearchSupplier() {
		logger.info("Search Supplier Executed");
	}
	@AfterThrowing("execution(public * com.einfochips.smartinventory.controller.SupplierController.searchSupplier(..))")
	public void logAfterSearchSupplierException() {
		logger.error("Exception in Search Supplier ");
	}
	@Before("execution(public * com.einfochips.smartinventory.controller.InventoryController.searchInventory(..))")
	public void logBeforeSearchInventory() {
		logger.info("Search Inventory Called ");
	}
	@AfterReturning("execution(public * com.einfochips.smartinventory.controller.InventoryController.searchInventory(..))")
	public void logAfterSearchInventory() {
		logger.info("Search Inventory Executed");
	}
	@AfterThrowing("execution(public * com.einfochips.smartinventory.controller.InventoryController.searchInventory(..))")
	public void logAfterSearchInventoryException() {
		logger.error("Exception in Search Inventory ");
	}
	
}
