package com.bicjo.sample.customer;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

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

	@Test
	public void deserialize() throws IOException {

		String jsonString = readFileToString("src/test/resources/customer.json");

		Customer customer = json.parse(jsonString).getObject();

		assertEquals(Long.valueOf(101l), customer.getId());
		assertEquals("joven", customer.getName());

	}

	private String readFileToString(String filename) {
		StringBuilder data = new StringBuilder();
		try {
			Path path = new File(filename).toPath();
			Stream<String> lines = Files.lines(path);
			lines.forEach(line -> data.append(line).append("\n"));
			lines.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data.toString();

	}

}
