package com.bicjo.sample.customer;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { CustomerController.class })
public class CustomerControllerMockMvcTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerService customerService;

	@Test
	public void test() throws Exception {

		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(101l, "joven"));
		customers.add(new Customer(102l, "rose"));
		customers.add(new Customer(103l, "chance"));

		given(customerService.getCustomers())//
				.willReturn(customers);

		mvc.perform(get("/customers").accept(MediaType.APPLICATION_JSON_VALUE))//
				.andExpect(status().isOk()) //
				.andDo(print());
	}

}
