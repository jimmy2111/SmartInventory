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
import com.einfochips.smartinventory.userrepository.InventoryRepository;

@Service
public class InventoryService {
	private static final Logger logger=LoggerFactory.getLogger(InventoryService.class);
	@Autowired
	private InventoryRepository inventoryRepo;
	
	public List<Inventory> getAllInventories() {
		logger.info("Finding All Inventories");
        return inventoryRepo.findAll();
    }
	
	public Page<Inventory> getAllInventoryByPagingAndSorting(Integer pageNo,Integer pageSize,String sortField,String sortOrder){
		logger.info("Geeting All Inventory By Paging And Sorting Service");
		Sort sort = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortField).ascending():Sort.by(sortField).descending();
		Pageable paging = PageRequest.of(pageNo, pageSize, sort);
		return inventoryRepo.findAll(paging);
	}
	
	public Page<Inventory> searchInventory(String name,Integer pageNo,Integer pageSize,String sortField,String sortOrder){
		logger.info("Searching for Inventory");
		Sort sort = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortField).ascending():Sort.by(sortField).descending();
		Pageable paging = PageRequest.of(pageNo, pageSize, sort);
		return inventoryRepo.searchInventoryByProductName(name, paging);
	}
    public Inventory getInventoryById(Long id) {
    	logger.info("Getting Inventory By Id");
        return inventoryRepo.findById(id).orElse(null);
    }

    public Inventory addInventory(Inventory inventory) {
    	logger.info("Creating New Inventory");
        return inventoryRepo.save(inventory);
    }
    
    public Inventory findInventoryByProduct(Product product) {
    	logger.info("Finding Inventory By Product");
    	return inventoryRepo.findByProduct(product);
    }

    public Inventory updateInventory(Long id, Inventory inventoryDetails) {
    	logger.info("Updating Inventory Record");
        Inventory inventory = getInventoryById(id);
        inventory.setProduct(inventoryDetails.getProduct());
        inventory.setQuantity(inventoryDetails.getQuantity());
        return inventoryRepo.save(inventory);
    }

    public void deleteInventory(Long id) {
    	logger.info("Deleting Inventory");
        Inventory inventory = inventoryRepo.findById(id).orElse(null);
        inventoryRepo.delete(inventory);
    }
}
