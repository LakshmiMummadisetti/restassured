package restAssured.restAssured;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Assignment {
	public static Response res = null;

	@Test(enabled = true, description = "perform the post operation using escape character")
	public void testcase1() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String body = "{\"username\":\"LakshmiM\",\"firstName\":\"Lakshmi\",\"lastName\":\"Kumari\",\"email\":\"xyz@abc.com\",\"password\":\"passwordfindme\",\"phone\":\"9087654321\"}";
		res = given().header("content-type", "application/json")
				// contentType(ContentType.JSON)
				.body(body).when().post("/user").then().statusCode(200).log().all().extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(enabled = true, description = "perform the get operation")
	public void testcase2() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";

		res = given().header("content-type", "application/json").pathParam("username", "LakshmiM").when()
				.get("/user/{username}").then().statusCode(200).log().all().extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(enabled = true, description = "perform the post operation using jsonfile")
	public void testcase3() throws IOException {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		FileInputStream jsonfile = new FileInputStream(new File(".\\data\\jsonfile.json"));
		res = given().body(IOUtils.toString(jsonfile)).header("Content-Type", "application/json").when().post("/user")
				.then().statusCode(200).log().all().extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(enabled = true, description = "perform the put operation using escape character")
	public void testcase4() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String body = "{\"username\":\"LakshmiM\",\"firstName\":\"Thanuja\",\"lastName\":\"KumariM\",\"email\":\"xyz@abc.com\",\"password\":\"passwordfindme\",\"phone\":\"9087654321\"}";
		res = given().header("content-type", "application/json").pathParam("username", "LakshmiM").body(body).when()
				.put("/user/{username}").then().statusCode(200).log().all().extract().response();
	}

	@Test(enabled = true, description = "perform the put operation using jsonfile character")
	public void testcase5() throws FileNotFoundException {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		FileInputStream jsonfile = new FileInputStream(new File(".\\data\\jsonfile.json"));
		res = given().header("content-type", "application/json").pathParam("username", "chervik").body(jsonfile).when()
				.put("/user/{username}").then().statusCode(200).log().all().extract().response();
	}

	@Test(enabled = true, description = "perform the delete operation")
	public void testcase6() throws FileNotFoundException {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";

		res = given().header("content-type", "application/json").pathParam("username", "LakshmiM").when()
				.delete("/user/{username}").then().statusCode(200).log().all().extract().response();
	}

	@Test(enabled = true, description = "verify the 404 error code")
	public void testcase7() throws FileNotFoundException {
		RestAssured.baseURI = "https://petstore.swagger.io/v3";

		res = given().header("content-type", "application/json").pathParam("username", "LakccshmiM").when()
				.get("/user/{username}").then().statusCode(404).log().all().extract().response();
	}
}
