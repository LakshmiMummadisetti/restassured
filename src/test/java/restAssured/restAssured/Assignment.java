package restAssured.restAssured;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Assignment {
	public static Response res = null;
	@Test(enabled=false)
	public void testcase1()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		String body = "{\"username\":\"LakccshmiM\",\"firstName\":\"Lakshmcci\",\"lastName\":\"Kuccmari\",\"email\":\"xyz@abc.com\",\"password\":\"passwordfindme\",\"phone\":\"9087654321\"}";
		 res = given().header("content-type", "application/json")
				//contentType(ContentType.JSON)
				.body(body)
				.when().post("/user").
				then().statusCode(200).log().all().extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(enabled=false)
	public void testcase2()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
	
		 res = given().header("content-type", "application/json")
				.pathParam("username", "LakccshmiM")
		.when().get("/user/{username}").
		then().statusCode(200).log().all().extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	@Test(enabled=true)
	public void testcase3() throws IOException
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		FileInputStream jsonfile = new FileInputStream(new File(".\\data\\jsonfile.json"));
		//RestAssured.baseURI="https://petstore.swagger.io/v2";
		res= given().body(IOUtils.toString(jsonfile))
		.header("Content-Type" , "application/json").
		when().post("/user").
		then().statusCode(200).log().all().extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
	}

}
