package com.einfochips.smartinventory.servicetests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.einfochips.smartinventory.model.Inventory;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.InventoryService;
import com.einfochips.smartinventory.userrepository.InventoryRepository;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {
	@InjectMocks
	private InventoryService inventoryService;
	@Mock
	private InventoryRepository inventoryRepository;
	
	@Test
	public void getAllInventoryTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        List<Inventory> inventory = new ArrayList<>();
        inventory.add(new Inventory(1L, products.get(0), products.get(0).getQuantity(), products.get(0).getThresholdQuantity()));
        inventory.add(new Inventory(2L, products.get(1), products.get(1).getQuantity(), products.get(1).getThresholdQuantity()));
        
        when(inventoryRepository.findAll()).thenReturn(inventory);
        List<Inventory> i = inventoryService.getAllInventories();
        assertEquals(inventory, i);
	}
	@Test
	public void getInventoryByIdTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        List<Inventory> inventory = new ArrayList<>();
        inventory.add(new Inventory(1L, products.get(0), products.get(0).getQuantity(), products.get(0).getThresholdQuantity()));
        inventory.add(new Inventory(2L, products.get(1), products.get(1).getQuantity(), products.get(1).getThresholdQuantity()));
        
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory.get(0)));
        Inventory i = inventoryService.getInventoryById(1L);
        assertEquals(inventory.get(0), i);
	}
	@Test
	public void findInventoryByProductTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        List<Inventory> inventory = new ArrayList<>();
        inventory.add(new Inventory(1L, products.get(0), products.get(0).getQuantity(), products.get(0).getThresholdQuantity()));
        inventory.add(new Inventory(2L, products.get(1), products.get(1).getQuantity(), products.get(1).getThresholdQuantity()));
        
        when(inventoryRepository.findByProduct(any())).thenReturn(inventory.get(0));
        Inventory i = inventoryService.findInventoryByProduct(products.get(0));
        assertEquals(inventory.get(0), i);
	}
	@Test
	public void addInventoryTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        List<Inventory> inventory = new ArrayList<>();
        inventory.add(new Inventory(1L, products.get(0), products.get(0).getQuantity(), products.get(0).getThresholdQuantity()));
        inventory.add(new Inventory(2L, products.get(1), products.get(1).getQuantity(), products.get(1).getThresholdQuantity()));
        
        when(inventoryRepository.save(any())).thenReturn(inventory.get(0));
        Inventory i = inventoryService.addInventory(inventory.get(0));
        assertEquals(inventory.get(0), i);
	}
	@Test
	public void updateInventoryTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        List<Inventory> inventory = new ArrayList<>();
        inventory.add(new Inventory(1L, products.get(0), products.get(0).getQuantity(), products.get(0).getThresholdQuantity()));
        inventory.add(new Inventory(2L, products.get(1), products.get(1).getQuantity(), products.get(1).getThresholdQuantity()));
        
        when(inventoryRepository.findById(any())).thenReturn(Optional.of(inventory.get(0)));
        when(inventoryRepository.save(any())).thenReturn(inventory.get(0));
        Inventory i =inventoryService.updateInventory(1L, inventory.get(0));
        assertEquals(inventory.get(0), i);
       	}
	
	@Test
	public void deleteInventoryTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        List<Inventory> inventory = new ArrayList<>();
        inventory.add(new Inventory(1L, products.get(0), products.get(0).getQuantity(), products.get(0).getThresholdQuantity()));
        inventory.add(new Inventory(2L, products.get(1), products.get(1).getQuantity(), products.get(1).getThresholdQuantity()));
        
        when(inventoryRepository.findById(any())).thenReturn(Optional.of(inventory.get(0)));
        boolean result  = inventoryService.deleteInventory(1L);
        assertTrue(result);
        
	}
	@Test
	public void getAllInventoryByPagingAndSortingTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        List<Inventory> inventory = new ArrayList<>();
        inventory.add(new Inventory(1L, products.get(0), products.get(0).getQuantity(), products.get(0).getThresholdQuantity()));
        inventory.add(new Inventory(2L, products.get(1), products.get(1).getQuantity(), products.get(1).getThresholdQuantity()));
        
        when(inventoryRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(inventory));
        Page<Inventory> page = inventoryService.getAllInventoryByPagingAndSorting(0, 3, "name", "desc");
        assertEquals(2, page.getTotalElements());
	}
	@Test
	public void searchInventoryTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        List<Inventory> inventory = new ArrayList<>();
        inventory.add(new Inventory(1L, products.get(0), products.get(0).getQuantity(), products.get(0).getThresholdQuantity()));
        inventory.add(new Inventory(2L, products.get(1), products.get(1).getQuantity(), products.get(1).getThresholdQuantity()));
        
        when(inventoryRepository.searchInventoryByProductName(any(), any())).thenReturn(new PageImpl<>(inventory));
        Page<Inventory> page = inventoryService.searchInventory("Fanta", 0, 3, "name", "desc");
        assertEquals(1, page.getTotalPages());
	}
}
