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

import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.userrepository.SupplierRepository;

@Service
public class SupplierService {
	private static final Logger logger=LoggerFactory.getLogger(SupplierService.class);
	@Autowired
	private SupplierRepository supplierRepo;
	
	public List<Supplier> getAllSuppliers() {
		logger.info("Finding All Suppliers");
        return supplierRepo.findAll();
    }
	
	public Page<Supplier> getSupplierByPagingAndSorting(Integer pageNo,Integer pageSize,String sortField,String sortOrder){
		logger.info("Getting Supliers By Paging and Sorting Service");
		Sort sort = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortField).ascending():Sort.by(sortField).descending();
		Pageable paging = PageRequest.of(pageNo,pageSize, sort);
		return supplierRepo.findAll(paging);
	}
	
	public Page<Supplier> searchSupplier(String name, Integer pageNo,Integer pageSize,String sortField,String sortOrder){
		logger.info("Searching for Supplier...");
		Sort sort= sortOrder.equalsIgnoreCase("asc")?Sort.by(sortField).ascending():Sort.by(sortField).descending();
		Pageable paging = PageRequest.of(pageNo, pageSize, sort);
		return supplierRepo.findByNameContainingIgnoreCase(name, paging);
	}
	public Supplier getSupplierById(Long supplierId) {
        logger.info("Getting Supplier By Id");
		return supplierRepo.findById(supplierId).orElse(null);
    }

    public Supplier createSupplier(Supplier supplier) {
    	logger.info("Creating New Supplier");
    	return supplierRepo.save(supplier);
    }
    public boolean deleteSupplier(Long supplierId) {
        Supplier supplier = getSupplierById(supplierId);
        logger.info("Deleting Supplier...");
        supplierRepo.delete(supplier);
        return true;
    }

    public Supplier updateSupplier(Long supplierId, Supplier supplierDetails) {
        Supplier supplier = getSupplierById(supplierId);
        logger.info("Updating Existing Supplier");
        supplier.setName(supplierDetails.getName());
        supplier.setAddress(supplierDetails.getAddress());
        supplier.setContact(supplierDetails.getContact());
        supplier.setProducts(supplierDetails.getProducts());
        return supplierRepo.save(supplier);
    }
    
}
