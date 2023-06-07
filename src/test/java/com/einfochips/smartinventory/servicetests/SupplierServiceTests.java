package com.einfochips.smartinventory.servicetests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.SupplierService;
import com.einfochips.smartinventory.userrepository.SupplierRepository;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTests {
	@InjectMocks
	private SupplierService supplierService;
	@Mock
	private SupplierRepository supplierRepository; 
	
	@Test
	public void getAllSuppliersTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        suppliers.add(new Supplier(99L, "J K Trading", "Godhra", "989867569", products));
		
        when(supplierRepository.findAll()).thenReturn(suppliers);
		
		List<Supplier> s = supplierService.getAllSuppliers();
		assertEquals(suppliers, s);
	}
	@Test
	public void getSupplierByIdTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        suppliers.add(new Supplier(99L, "J K Trading", "Godhra", "989867569", products));
        
        when(supplierRepository.findById(98L)).thenReturn(Optional.of(suppliers.get(0)));
        Supplier s = supplierService.getSupplierById(98L);
        assertEquals(suppliers.get(0), s);
	}
	@Test
	public void createSupplierTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        suppliers.add(new Supplier(99L, "J K Trading", "Godhra", "989867569", products));
        
        when(supplierRepository.save(suppliers.get(0))).thenReturn(suppliers.get(0));
        Supplier s = supplierService.createSupplier(suppliers.get(0));
        assertEquals(suppliers.get(0), s);
	}
	@Test
	public void deleteSupplierTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        suppliers.add(new Supplier(99L, "J K Trading", "Godhra", "989867569", products));
        
        when(supplierRepository.findById(98L)).thenReturn(Optional.of(suppliers.get(0)));
        boolean result = supplierService.deleteSupplier(98L);
        assertTrue(result);
	}
	@Test
	public void getSupplierByPagingAndSortingTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        suppliers.add(new Supplier(99L, "J K Trading", "Godhra", "989867569", products));
        
        when(supplierRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(suppliers));
        Page<Supplier> page = supplierService.getSupplierByPagingAndSorting(0, 3, "name", "asc");
        assertEquals(2, page.getTotalElements());
	}
	@Test
	public void searchSupplierTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        suppliers.add(new Supplier(99L, "J K Trading", "Godhra", "989867569", products));
        
        when(supplierRepository.findByNameContainingIgnoreCase(any(), any(Pageable.class))).thenReturn(new PageImpl<>(suppliers));
        Page<Supplier> page = supplierService.searchSupplier("D C Traders", 0, 3, "name", "asc");
        assertEquals(1, page.getTotalPages());
	}
	@Test
	public void updateSupplierTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        suppliers.add(new Supplier(99L, "J K Trading", "Godhra", "989867569", products));
        
        when(supplierRepository.findById(98L)).thenReturn(Optional.of(suppliers.get(0)));
        when(supplierRepository.save(any())).thenReturn(suppliers.get(0));
        Supplier supplier = supplierService.updateSupplier(98L, suppliers.get(0));
        assertEquals(suppliers.get(0), supplier);
	}
}
