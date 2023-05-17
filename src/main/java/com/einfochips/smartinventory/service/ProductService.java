package com.einfochips.smartinventory.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.einfochips.smartinventory.model.Inventory;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.userrepository.InventoryRepository;
import com.einfochips.smartinventory.userrepository.ProductRepository;

@Service
public class ProductService {
	private static final Logger logger=LoggerFactory.getLogger(ProductService.class);
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private InventoryService inventoryService;
	
	public List<Product> getAllProducts() {
		logger.info("Finding All Products");
        return productRepo.findAll();
    }
	
	public Page<Product> getAllProductsByPagingAndSorting(Integer pageNo, Integer pageSize,String sortField, String sortOrder){
		logger.info("Getting All Products By Paging And Sorting Through Service");
		Sort sort = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortField).ascending():Sort.by(sortField).descending();
		Pageable paging = PageRequest.of(pageNo, pageSize,sort);
		Page<Product> pagedResult = productRepo.findAll(paging);
		return pagedResult;
	}
	
	public Page<Product> searchProducts(String name,Integer pageNo,Integer pageSize,String sortField,String sortOrder) {
		logger.info("Searching For Product Through Service");
		Sort sort = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortField).ascending():Sort.by(sortField).descending();
		Pageable paging = PageRequest.of(pageNo, pageSize,sort);
		Page<Product> pagedResult = productRepo.findByNameContainingIgnoreCase(name, paging);
		return pagedResult;
	}
	
	public Product getProductById(Long id) {
		logger.info("Getting Product By Id");
        return productRepo.findById(id).orElse(null);
    }
	public Product getByNameAndSupplier(String name,Supplier s) {
		return productRepo.findByNameAndSupplier(name, s);
	}
	public Product createProduct(Product product) {
		logger.info("Creating New Product");
		Product savedProduct= productRepo.save(product);
		Inventory inventory = new Inventory();
		inventory.setProduct(savedProduct);
		inventory.setQuantity(product.getQuantity());
		inventory.setThreshold(product.getThresholdQuantity());
		inventoryRepository.save(inventory);
		logger.info("New Product Created");
        return savedProduct;
    }
	public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getProductById(id);
        if (product == null) {
            return null;
        }
        logger.info("Updating Existing Product");
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setQuantity(updatedProduct.getQuantity());
        product.setUnitPrice(updatedProduct.getUnitPrice());
        product.setThresholdQuantity(updatedProduct.getThresholdQuantity());
        product.setSupplier(updatedProduct.getSupplier());
        Product savedProduct = productRepo.save(product);
        Inventory inventory=inventoryRepository.findByProductId(id);
        inventory.setProduct(savedProduct);
        inventory.setQuantity(savedProduct.getQuantity());
        inventory.setThreshold(savedProduct.getThresholdQuantity());
        inventoryService.updateInventory(inventory.getId(), inventory);
        logger.info("Product Updated...");
        return savedProduct;
    }
	public boolean deleteProduct(Long id) {
        Product product = getProductById(id);
        if (product == null) {
            return false;
        }
        logger.info("Deleting Product");
        productRepo.delete(product);
        logger.info("Product Deleted");
        return true;
    }
}
