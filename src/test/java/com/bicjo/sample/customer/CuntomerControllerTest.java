package com.bicjo.sample.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

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
public class CuntomerControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private CustomerService customerService;

	@Test
	public void restCall() {

		Customer customer = new Customer();
		customer.setId(101l);
		customer.setName("joven");

		given(customerService.getCustomer())//
				.willReturn(customer);

		String body = restTemplate.getForObject("/customers", String.class);

		assertEquals("{\"id\":101,\"name\":\"joven\"}", body);
	}
}
