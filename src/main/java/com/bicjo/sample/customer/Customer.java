package com.bicjo.sample.customer;

import org.springframework.hateoas.core.Relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Relation(value = "customer", collectionRelation = "customers")
public class Customer {

	private Long id;
	private String name;

}
