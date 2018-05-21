package com.bicjo.sample.customer;

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

}
