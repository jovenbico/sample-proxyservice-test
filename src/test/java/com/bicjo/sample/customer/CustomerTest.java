package com.bicjo.sample.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class CustomerTest {

	@Autowired
	private JacksonTester<Customer> json;

	@Autowired
	private JacksonTester<List<Customer>> jsonCustomerResources;

	@Test
	public void deserializeCustomers() throws IOException {
		String jsonString = readFileToString("src/test/resources/customers.json");

		List<Customer> customers = jsonCustomerResources//
				.parse(jsonString)//
				.getObject();

		for (Customer c : customers) {
			assertNotNull(c.getId());
			assertNotNull(c.getName());
		}
	}

	@Test
	public void deserialize() throws IOException {

		String jsonString = readFileToString("src/test/resources/customer.json");

		Customer customer = json.parse(jsonString).getObject();

		assertEquals(Long.valueOf(101l), customer.getId());
		assertEquals("joven", customer.getName());

	}

	@Test
	public void serialize() throws IOException {

		Customer customer = new Customer();
		customer.setId(101l);
		customer.setName("joven");

		assertThat(json.write(customer))//
				.hasJsonPathStringValue("@.name");
		assertThat(json.write(customer))//
				.extractingJsonPathStringValue("@.name").isEqualTo("joven");

	}

	private String readFileToString(String filename) {
		try {
			return new String(Files.readAllBytes(Paths.get(filename)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
