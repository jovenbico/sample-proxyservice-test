package com.bicjo.sample.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public Customer getCustomer() {
		Customer customer = new Customer();
		customer.setId(102l);
		customer.setName("rose");
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {

		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(101l, "joven"));
		customers.add(new Customer(102l, "rose"));
		customers.add(new Customer(103l, "chance"));

		return customers;
	}

}
