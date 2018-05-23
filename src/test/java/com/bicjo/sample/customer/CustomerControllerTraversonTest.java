package com.bicjo.sample.customer;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTraversonTest {

	@LocalServerPort
	private int port;

	@Test
	public void test() throws URISyntaxException {
		Traverson traverson = new Traverson(//
				new URI("http://localhost:" + this.port + "/customers"), MediaTypes.HAL_JSON//
		);

		String stringBody = traverson.follow("self").toObject(String.class);
		System.out.println(stringBody);

		CustomerResources customerResources = traverson.follow("self")//
				.toObject(ParameterizedTypeReference.forType(CustomerResources.class));
		System.out.println(customerResources.getContent());
	}

}
