package com.upgradeinc.restapi;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import utils.Constants;
import utils.APICommonUtils;

import static utils.Configuration.API_DATA;

public class CredApiTest extends APICommonUtils {

	@BeforeTest
	public void setUp() {
		initialSetup(API_DATA.getProperty(Constants.POST_URL), API_DATA.getProperty(Constants.SOURCE_ID), 
				UUID.randomUUID(), "application/json");
	}

	@Test
	public void apiCallWithValidCred_returnsStatus200() {
		request.body(getBodyWithValidCredentials()).when().post().then().statusCode(200);
	}

	@Test
	public void apiCallWithValidCreds_checkProductType() {
		Response response = request.body(getBodyWithValidCredentials()).when().post().then()
				.contentType(ContentType.JSON).extract().response();

		Set<String> allProductTypes = getAllValuesForKeyFromResponse(new JSONObject(response.asPrettyString()), "productType",
				new HashSet<String>());

		Assert.assertTrue(allProductTypes.contains("PERSONAL_LOAN"));
	}

	@Test
	public void apiCallWithInvalidCred_returnsStatus401() {
		request.body(getBodyWithCredentials("unknownUser", API_DATA.getProperty(Constants.PASSWORD), API_DATA.getProperty(Constants.CAPTCHA))).when().post().then()
				.statusCode(401);
	}


	private String getBodyWithValidCredentials() {
		return getBodyWithCredentials(API_DATA.getProperty(Constants.USER_NAME), API_DATA.getProperty(Constants.PASSWORD), API_DATA.getProperty(Constants.CAPTCHA));
	}

	private String getBodyWithCredentials(String username, String password, String captcha) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("username", username);
		requestBody.put("password", password);
		requestBody.put("recaptchaToken", captcha);
		return requestBody.toString();
	}
}
