package com.promineotech.inventoryManagement.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.inventoryManagement.entity.Customer;
import com.promineotech.inventoryManagement.repository.CustomerRepository;

@Service
public class CustomerService {

	private static final Logger logger = LogManager.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository repo;
	
	public Customer getCustomerById(Long id) throws Exception {
		try {
			return repo.findById(id).get();
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve customer: " + id, e);
			throw e;
		}
	}
	
	public Iterable<Customer> getCustomers() {
		return repo.findAll();
	}
	
	public Customer createCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	public Customer updateCustomer(Customer customer, Long id) throws Exception {
		try {
			Customer oldCustomer = repo.findById(id).get();
			oldCustomer.setAddress(customer.getAddress());
			oldCustomer.setFirstName(customer.getFirstName());
			oldCustomer.setLastName(customer.getLastName());
			oldCustomer.setLevel(customer.getLevel());
			return repo.save(oldCustomer);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update customer: " + id, e);
			throw new Exception("Unable to update customer.");
		}
	}
	
	public void deleteCustomer(Long id) throws Exception {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete customer: " + id, e);
			throw new Exception("Unable to delete customer.");
		}
	}
	
}
