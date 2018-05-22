package com.bicjo.sample.customer;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

public class CustomerResources extends Resources<Customer> {

	public CustomerResources() {
		super();
	}

	public CustomerResources(Iterable<Customer> content, Iterable<Link> links) {
		super(content, links);
	}

	public CustomerResources(Iterable<Customer> content, Link... links) {
		super(content, links);
	}

}
