package com.bicjo.sample.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Data;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.autoconfigure.json.JsonTest
public class JsonTest {

	@Autowired
	private JacksonTester<Map<Integer, Integer>> jsonMap;

	@Autowired
	private JacksonTester<List<EnumeratedObject<Integer, Integer>>> jsonEnumObject;

	@Test
	public void testMap() throws IOException {
		Map<Integer, Integer> mapObject = new HashMap<>();
		mapObject.put(4, 100);
		mapObject.put(6, 200);
		mapObject.put(8, 300);

		System.out.println(jsonMap.write(mapObject).getJson());
	}

	@Test
	public void testEnumObject() throws IOException {

		List<EnumeratedObject<Integer, Integer>> enumObjects = new ArrayList<>();

		EnumeratedObject<Integer, Integer> enumObject1 = new EnumeratedObject<>();
		enumObject1.setKey(4);
		enumObject1.setValue(100);
		enumObjects.add(enumObject1);

		EnumeratedObject<Integer, Integer> enumObject2 = new EnumeratedObject<>();
		enumObject2.setKey(6);
		enumObject2.setValue(200);
		enumObjects.add(enumObject2);

		EnumeratedObject<Integer, Integer> enumObject3 = new EnumeratedObject<>();
		enumObject3.setKey(8);
		enumObject3.setValue(300);
		enumObjects.add(enumObject3);

		System.out.println(jsonEnumObject.write(enumObjects).getJson());

	}

	@Data
	class EnumeratedObject<K, V> {
		private K key;
		private V value;
	}

}
