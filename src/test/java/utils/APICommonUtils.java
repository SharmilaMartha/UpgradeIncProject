package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

public class APICommonUtils {
	
	protected static RequestSpecification request;
	
	public void initialSetup(String url, String sourceId, UUID uuid, String contentType) {
		RestAssured.baseURI = url;
		request = given().header("x-cf-source-id", sourceId).header("x-cf-corr-id", uuid)
				.header("Content-Type", contentType);
	}
	
	public Set<String> getAllValuesForKeyFromResponse(JSONObject response, String desiredKey, Set<String> values) {

		Iterator<String> keys = response.keys();

		while (keys.hasNext()) {
			String key = keys.next();
			if (response.get(key) instanceof String && key.equals(desiredKey)) {
				values.add(response.getString(key));
			} else if (response.get(key) instanceof JSONObject) {
				getAllValuesForKeyFromResponse((JSONObject) response.get(key), desiredKey, values);
			} else if (response.get(key) instanceof JSONArray) {
				JSONArray nodes = (JSONArray) response.get(key);
				for (Object node : nodes) {
					if (node instanceof JSONObject) {
						getAllValuesForKeyFromResponse((JSONObject) node, desiredKey, values);
					}
				}
			}
		}

		return values;
	}
}
