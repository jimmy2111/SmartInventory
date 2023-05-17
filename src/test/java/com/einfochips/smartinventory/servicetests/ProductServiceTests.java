package com.einfochips.smartinventory.servicetests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.test.context.support.WithMockUser;

import com.einfochips.smartinventory.model.Inventory;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.InventoryService;
import com.einfochips.smartinventory.service.ProductService;
import com.einfochips.smartinventory.userrepository.InventoryRepository;
import com.einfochips.smartinventory.userrepository.ProductRepository;
@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
	@InjectMocks
	private ProductService productService;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private InventoryRepository inventoryRepository;
	@Mock
	private InventoryService inventoryService;
	
	@Test
	public void getAllProductsByPagingAndSortingTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        Page<Product> page = new PageImpl<>(products);
        when(productRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        Page<Product> expectedpage = productService.getAllProductsByPagingAndSorting(0, 3, "name", "asc");
        assertEquals(3, expectedpage.getTotalElements());
        
	}
	@Test
	public void searchProductsTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        Page<Product> page = new PageImpl<>(products);
        
        when(productRepository.findByNameContainingIgnoreCase(any(String.class), any(Pageable.class))).thenReturn(page);
        
        Page<Product> product  = productService.searchProducts("Fanta", 0, 3, "name", "asc");
        assertEquals(1,product.getTotalPages());
	}
	@Test
	public void getByNameAndSupplierTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        
        when(productRepository.findByNameAndSupplier(any(String.class), any(Supplier.class))).thenReturn(products.get(0));
        
        Product product = productService.getByNameAndSupplier("Fanta", suppliers.get(0));
        assertEquals(products.get(0), product);
	}
	@Test
	public void getProductByIdTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        when(productRepository.findById(1L)).thenReturn(Optional.of(products.get(0)));
        
        Product product = productService.getProductById(1L);
        assertEquals(products.get(0), product);
	}
	@Test
	public void getProductByIdNullTest() {
		List<Product> products = new ArrayList<>();
//        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
//        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
//        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        when(productRepository.findById(4L)).thenReturn(Optional.empty());
        
        Product product = productService.getProductById(4L);
        assertEquals(null, product);
	}
	@Test
	public void createProductTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        Product p = products.get(0);
        when(productRepository.save(any())).thenReturn(products.get(0));
        when(inventoryRepository.save(any())).thenReturn(new Inventory(p.getId(), p, p.getQuantity(), p.getThresholdQuantity()));
        Product product = productService.createProduct(products.get(0));
        assertEquals(products.get(0), product);
	}
	@Test
	public void updateProductTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        Product p = new Product(1L, "Fanta", "New Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products));
        when(productRepository.findById(1L)).thenReturn(Optional.of(p));
        when(productRepository.save(any())).thenReturn(p);
        Inventory inventory = new Inventory(1L, p, p.getQuantity(), p.getThresholdQuantity());
        when(inventoryRepository.findByProductId(1L)).thenReturn(inventory);
        when(inventoryService.updateInventory(1L, inventory)).thenReturn(any(Inventory.class));
        Product product = productService.updateProduct(1L, p);
        assertEquals(p, product);
	}
}
