package com.bicjo.sample.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private RestTemplate restTemplate;

	@MockBean
	private CustomerService customerService;

	@LocalServerPort
	private int port;

	@Before
	public void before() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(101l, "joven"));
		customers.add(new Customer(102l, "rose"));
		customers.add(new Customer(103l, "chance"));

		given(customerService.getCustomers())//
				.willReturn(customers);

		given(customerService.getCustomer()) //
				.willReturn(new Customer(101L, "jovan"));
	}

	@Test
	public void customers_using_testRestTemplate() {

		String body = testRestTemplate.getForObject("/customers", String.class);
		System.out.println(body);

		String customerBody = testRestTemplate.getForObject("/customers/customer", String.class);
		System.out.println(customerBody);

		ResponseEntity<Customer> customerResponse = testRestTemplate.exchange("/customers/customer", HttpMethod.GET,
				null, ParameterizedTypeReference.forType(Customer.class), //
				Collections.emptyMap());
		System.out.println(customerResponse.getBody());

	}

	/**
	 * include HAL_JSON to message converter
	 * com.bicjo.sample.ProxyServiceApplication.restTemplate(RestTemplateBuilder)
	 */
	@Test
	public void customers_using_restTemplate() {
		String url = "http://localhost:" + port + "/customers";

		ResponseEntity<CustomerResources> entityBody //
				= restTemplate.getForEntity(url, CustomerResources.class);
		System.out.println(entityBody//
				.getBody()//
				.getContent()//
		);

		ResponseEntity<CustomerResources> customerResources //
				= restTemplate.getForEntity(url, CustomerResources.class);
		System.out.println(customerResources//
				.getBody()//
				.getContent()//
		);

		customerResources //
				= restTemplate.exchange(url, //
						HttpMethod.GET, null, //
						ParameterizedTypeReference.forType(CustomerResources.class), //
						Collections.emptyMap());
		System.out.println(customerResources//
				.getBody()//
				.getContent()//
		);

		ResponseEntity<Resources<Customer>> entityCustomerResources //
				= restTemplate.exchange(url, HttpMethod.GET, null,
						new ParameterizedTypeReference<Resources<Customer>>() {
						}, Collections.emptyMap());
		System.out.println(entityCustomerResources//
				.getBody()//
				.getContent()//
		);

	}

	@Test
	public void customerById() {

		String body = testRestTemplate.getForObject("/customers/{id}", String.class, 101l);

		assertEquals("{\"id\":101,\"name\":\"jovan\"}", body);
	}
}
