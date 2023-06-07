package com.einfochips.smartinventory.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.ProductService;
import com.einfochips.smartinventory.service.SupplierService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private SupplierService supplierService;

	@GetMapping("/addProducts")
	public ModelAndView getAddProductsPage() {
		ModelAndView modelAndView = new ModelAndView("addProducts");
		List<Supplier> suppliers = supplierService.getAllSuppliers();
		modelAndView.addObject("suppliers", suppliers);
		logger.info("Add Products Page Displayed");
		return modelAndView;
	}

	@GetMapping("/viewallproducts")
	public ModelAndView getAllProductsByPagingAndSorting(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "4") Integer pageSize, @RequestParam(defaultValue = "name") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		ModelAndView modelAndView = new ModelAndView("products");
		logger.info("Viewing All Products");
		Page<Product> page = productService.getAllProductsByPagingAndSorting(pageNo, pageSize, sortField, sortOrder);
		modelAndView.addObject("products", page.getContent());
		modelAndView.addObject("totalPages", page.getTotalPages());
		modelAndView.addObject("currentPage", pageNo);
		modelAndView.addObject("sortField", sortField);
		modelAndView.addObject("sortOrder", sortOrder);
		modelAndView.addObject("revSortOrder", sortOrder.equalsIgnoreCase("asc") ? "desc" : "asc");
		logger.info("Displayed All Products");
		return modelAndView;
	}

	@PostMapping("/searchproduct")
	public ModelAndView searchProduct(@RequestParam String name, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "4") Integer pageSize, @RequestParam(defaultValue = "name") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder) throws Exception {
		ModelAndView modelAndView = new ModelAndView("products");
		logger.info("Searching for Product");
		Page<Product> page = productService.searchProducts(name, pageNo, pageSize, sortField, sortOrder);
		if (page.isEmpty()) {
			throw new Exception("Product is Not Found");
		}
		modelAndView.addObject("products", page.getContent());
		modelAndView.addObject("totalPages", page.getTotalPages());
		modelAndView.addObject("currentPage", pageNo);
		modelAndView.addObject("sortField", sortField);
		modelAndView.addObject("sortOrder", sortOrder);
		modelAndView.addObject("revSortOrder", sortOrder.equalsIgnoreCase("asc") ? "desc" : "asc");
		logger.info("Found Product...");
		return modelAndView;
	}

	@PostMapping(path = "/saveproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView saveProduct(@ModelAttribute Product product, @RequestParam Long id) {
		ModelAndView modelAndView = new ModelAndView("addProducts");
		Supplier s = supplierService.getSupplierById(id);
		Product p = productService.getByNameAndSupplier(product.getName(), s);
		if (p == null) {
			Supplier supplier = supplierService.getSupplierById(id);
			product.setSupplier(supplier);
			productService.createProduct(product);
			modelAndView.addObject("success", "Product Added Successfully");
		} else {
			product.setSupplier(s);
			productService.updateProduct(p.getId(), product);
			modelAndView.addObject("success", "Product Updated Successfully");
		}
		logger.info("Saving Product");
		return modelAndView;
	}

	@GetMapping("/viewproducts")
	public List<Product> getProducts(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "4") Integer pageSize, @RequestParam(defaultValue = "name") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		return productService.getAllProductsByPagingAndSorting(pageNo, pageSize, sortField, sortOrder).getContent();
	}

	@PostMapping("/searchforproduct")
	public List<Product> searchforproduct(@RequestParam String name, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "4") Integer pageSize, @RequestParam(defaultValue = "name") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder) throws Exception {
		logger.info("Searching for Product");
		Page<Product> page = productService.searchProducts(name, pageNo, pageSize, sortField, sortOrder);
		if (page.isEmpty()) {
			throw new Exception("Product is Not Found");
		}
		logger.info("Found Product...");
		return page.getContent();
	}
	@PostMapping("/addproduct")
	public ResponseEntity<?> addProduct(@ModelAttribute Product product, @RequestParam Long id) {
		Supplier s = supplierService.getSupplierById(id);
		Product p = productService.getByNameAndSupplier(product.getName(), s);
		if (p == null) {
			Supplier supplier = supplierService.getSupplierById(id);
			product.setSupplier(supplier);
			productService.createProduct(product);
			String success = "Product Added Successfully";
			return ResponseEntity.status(HttpStatus.OK).body(success);
		} else {
			product.setSupplier(s);
			productService.updateProduct(p.getId(), product);
			String success = "Product Updated Successfully";
			return ResponseEntity.status(HttpStatus.OK).body(success);
		}
	}

}
