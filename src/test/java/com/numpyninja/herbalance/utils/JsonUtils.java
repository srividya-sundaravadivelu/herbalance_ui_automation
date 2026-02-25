package com.numpyninja.herbalance.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static final ObjectMapper mapper = new ObjectMapper();

	private JsonUtils() {
	}

	public static <T> T readJson(String filePath, Class<T> clazz) {
		try {
			return mapper.readValue(new File(filePath), clazz);
		} catch (Exception e) {
			throw new RuntimeException("Failed to read JSON file: " + filePath, e);
		}
	}

	public static <T> T readJsonFromResources(String fileName, Class<T> clazz) {
		try (InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testdata/" + fileName)) {
			if (is == null) {
				throw new RuntimeException("File not found in classpath: " + fileName);
			}
			return new ObjectMapper().readValue(is, clazz);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read JSON file: " + fileName, e);
		}
	}
}
