
package com.numpyninja.herbalance.testdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.numpyninja.herbalance.utils.JsonUtils;

public class TestDataManager {

	private static final JsonNode data = JsonUtils.readJsonFromResources("testData.json", JsonNode.class);

	public static JsonNode get() {
		return data;
	}
}
