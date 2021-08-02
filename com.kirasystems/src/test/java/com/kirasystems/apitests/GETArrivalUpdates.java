package com.kirasystems.apitests;

import static io.restassured.RestAssured.given;

import java.util.Properties;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.kirasystems.utilities.CommonUtilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GETArrivalUpdates {
	
	
	private Properties rop = CommonUtilities.readPropertiesFile("src/test/resources/Environments/environment_variables_production");
	private String url = rop.getProperty("url"); 
	private String pathStops = rop.getProperty("path_stops"); 
	private String pathPredictions = rop.getProperty("path_predictions");

	@Test(dataProvider="StopsToCheck",enabled=true)
	void getUpdatesonArrivals(Integer params)
	{
		
		Response response = given()
				.get(url+pathStops+params+pathPredictions);
		Reporter.log("\n The response for this request is : \n"+response.body().prettyPrint(), true);
		JsonPath resp = response.jsonPath();
		Assert.assertEquals((resp.getString("agency.id")!=null),true, "Agency id is null");
		Assert.assertEquals((resp.getString("agency.title")!=null),true, "Agency title is null");
		Assert.assertEquals((resp.getString("route.id")!=null),true, "Route id is null");
		Assert.assertEquals((resp.getString("route.title")!=null),true, "Route title is null");
		Assert.assertEquals((resp.getString("stop.id")!=null),true, "Stop id is null");
		Assert.assertEquals((resp.getString("stop.title")!=null),true, "Stop title is null");
		Assert.assertEquals((resp.getInt("values.size()")>= 1),true, "There are no predictions");	
	}
	
	@DataProvider(name="StopsToCheck")
	private Integer[] getParams()
	{
		Integer[] params= {23890,23891,23892};
		return params;
		
	}
}
