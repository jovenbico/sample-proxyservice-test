package com.bicjo.sample.customer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
	public ResponseEntity<Resources<Customer>> getCustomers() {
		List<Customer> customers = customerService.getCustomers();

		return ResponseEntity.ok(new CustomerResources(//
				customers, linkTo(CustomerController.class).withRel("self")//
		));
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource<Customer>> getCustomer(//
			@PathVariable long id//
	) {
		Customer customer = customerService.getCustomer();
		return ResponseEntity.ok(new Resource<Customer>(customer));
	}

	@GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomerObject() {
		return ResponseEntity.ok(customerService.getCustomer());
	}

}
