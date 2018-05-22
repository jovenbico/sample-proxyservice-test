package com.bicjo.sample.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private CustomerService customerService;

	@Test
	public void customers() {

		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(101l, "joven"));
		customers.add(new Customer(102l, "rose"));
		customers.add(new Customer(103l, "chance"));

		given(customerService.getCustomers())//
				.willReturn(customers);

		String body = restTemplate.getForObject("/customers", String.class);
		System.out.println(body);

		// ResponseEntity<CustomerResources> customerResources //
		// = restTemplate.getForEntity("/customers", CustomerResources.class);

		// ResponseEntity<Resources<Customer>> customerResources //
		// = restTemplate.exchange("/customers", HttpMethod.GET, null,
		// new ParameterizedTypeReference<Resources<Customer>>() {
		// }, new HashMap<String, String>());

		// TODO make it work
		ResponseEntity<CustomerResources> customerResources //
				= restTemplate.exchange("/customers", HttpMethod.GET, null,
						ParameterizedTypeReference.forType(CustomerResources.class), //
						new HashMap<String, String>());

		System.out.println(customerResources.getBody());
	}

	@Test
	public void customerById() {

		Customer customer = new Customer();
		customer.setId(101l);
		customer.setName("joven");

		given(customerService.getCustomer())//
				.willReturn(customer);

		String body = restTemplate.getForObject("/customers/{id}", String.class, 101l);

		assertEquals("{\"id\":101,\"name\":\"joven\"}", body);
	}
}
