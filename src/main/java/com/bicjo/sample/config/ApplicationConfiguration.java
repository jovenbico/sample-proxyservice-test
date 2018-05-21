package com.bicjo.sample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "sample.service")
@Getter
@Setter
public class ApplicationConfiguration {

	private String applicationId;

}
