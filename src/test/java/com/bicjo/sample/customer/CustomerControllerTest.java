package com.bicjo.sample.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
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

		String body = restTemplate.getForObject("/customers", String.class, 101l);
		assertNotNull(body);
		System.out.println(body);
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
