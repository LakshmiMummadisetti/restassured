package restAssured.restAssured;

import org.apache.http.entity.ContentType;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Sampletest {
	
	@Test(enabled=false)
	public void testcase1()
	{
		Response response =RestAssured.get("http://localhost:3000/students");
		
		System.out.println(response.asString());
		System.out.println(response.getStatusLine());
		//System.out.println(response.asPrettyString());
	}
	
	@Test(enabled=false)
	public void testcase2()
	{	
		Response response = RestAssured.delete("http://localhost:3000/students/27");
		System.out.println(response.asString());
		System.out.println(response.getStatusLine());
	}
	
	@Test(enabled=false, dependsOnMethods = "testcase4")
	public void testcase3(ITestContext obj)
	{
		RestAssured.baseURI = "http://localhost:3000";
		
		given()
		.get("/students/"+obj.getAttribute("id").toString()).
		then().statusCode(200).log().all();
	}
	@Test(enabled=false)
	public void testcase4(ITestContext obj)
	{
		RestAssured.baseURI = "http://localhost:3000";
		String body = "{\"stream\":\"commerce\",\"firstname\":\"Lakshmik\",\"lastname\":\"luthra\"}";
		Response res = given().header("content-type", "application/json")
		//contentType(ContentType.JSON)
		.body(body)
		.when().put("/students/38").
		then().statusCode(200).log().all().extract().response();
		String id = res.jsonPath().getString("id");
		System.out.print(id);
		obj.setAttribute("id", id);
	}
	@Test
	public void testcase5()
	{
		JSONObject jobj = new JSONObject();
		RestAssured.baseURI = "http://localhost:3000";
		//String body = "{\"stream\":\"commerce\",\"firstname\":\"Lakshmik\",\"lastname\":\"kathi\"}";
		jobj.put("stream", "ece");
		jobj.put("firstname", "Lakshmi");
		jobj.put("lastname", "kumari");
		//System.out.println(jobj.toJSONString());
		given().header("content-type","application/json")
		.body(jobj.toJSONString()).
		when().post("/students").
		then().statusCode(201).log().all();
	}

}
