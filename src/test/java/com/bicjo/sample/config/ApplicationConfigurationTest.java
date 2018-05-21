package com.bicjo.sample.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationConfigurationTest {

	@Autowired
	private ApplicationConfiguration configuration;

	@Test
	public void applicationId() {
		assertEquals("APPLICATION_ID", configuration.getApplicationId());
	}

}
