package stepDefBDDFrame;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import  static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import  static org .junit.Assert.*;

import pojo.AddPlace;
import pojo.Location;


import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
public class StepDefinition 
{    
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	
	
	 @Given("^Add Place Payload$")
	    public void add_place_payload() throws Throwable
	    {
		 RestAssured.baseURI="https://rahulshettyacademy.com";
		

		 AddPlace p =new AddPlace();
		 p.setAccuracy(50);
		 p.setAddress("29, side layout, cohen 09");
		 p.setLanguage("French-IN");
		 p.setPhone_number("(+91) 983 893 3937");
		 p.setWebsite("https://rahulshettyacademy.com");
		 p.setName("Frontline house");
		 List<String> myList =new ArrayList<String>();
		 myList.add("shoe park");
		 myList.add("shop");

		 p.setTypes(myList);
		 Location l =new Location();
		 l.setLat(-38.383494);
		 l.setLng(33.427362);
		 p.setLocation(l);

		 RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
					 
					 
		 resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 res=given().spec(req).body(p);

	    }

	    @When("^user calls \"([^\"]*)\" with post http request $")
	    public void user_calls_something_with_post_http_request(String strArg1) throws Throwable
	    {
	    	 response =res.when().post("/maps/api/place/add/json").
	    			then().spec(resspec).extract().response();
	    	 System.out.println(response);
	    	 
	    }

	    @Then("^The API call is success with status code 200$")
	    public void the_api_call_is_success_with_status_code_200() throws Throwable 
	    {

	    	assertEquals(response.getStatusCode(),200); 
	    	System.out.println(getStatusCode());
	    }

	    private char[] getStatusCode() {
			// TODO Auto-generated method stub
			return null;
		}

		@And("^\"([^\"]*)\" in response body \"([^\"]*)\"$")
	    public void something_in_response_body_something(String keyValue, String Expectedvalue) throws Throwable 
	    {
	    	String resp=response.asString();
	    	JsonPath js= new JsonPath(resp);
	    	assertEquals(js.get(keyValue).toString(),Expectedvalue);
	    	System.out.println(js.get(keyValue));
	    	System.out.println(js.get(Expectedvalue));
	    	
	        
	    }
		
		
	    

	
}
