package com.numpyninja.herbalance.utils;

import java.io.InputStream;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDataReader {

	private JsonNode rootNode;

	public TestDataReader() {
		loadTestData();
	}

	private void loadTestData() {
		try {
			ObjectMapper mapper = new ObjectMapper();

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testdata/testData.json");

			if (inputStream == null) {
				throw new RuntimeException("Test data file not found in classpath");
			}

			rootNode = mapper.readTree(inputStream);

		} catch (Exception e) {
			throw new RuntimeException("Failed to load test data", e);
		}
	}

	public JsonNode getNode(String path) {
		String[] keys = path.split("\\.");
		JsonNode current = rootNode;

		for (String key : keys) {
			current = current.path(key);
			if (current.isMissingNode()) {
				throw new RuntimeException("Invalid JSON path: " + path);
			}
		}

		return current;
	}

	public JsonNode getRootNode() {
		return rootNode;
	}

	public String getStringValue(String... keys) {
		JsonNode currentNode = rootNode;
		for (String key : keys) {
			currentNode = currentNode.path(key);
		}
		return currentNode.asText();
	}

	public int getIntValue(String... keys) {
		JsonNode currentNode = rootNode;
		for (String key : keys) {
			currentNode = currentNode.path(key);
		}
		return currentNode.asInt();
	}

	public boolean getBooleanValue(String... keys) {
		JsonNode currentNode = rootNode;
		for (String key : keys) {
			currentNode = currentNode.path(key);
		}
		return currentNode.asBoolean();
	}
}
