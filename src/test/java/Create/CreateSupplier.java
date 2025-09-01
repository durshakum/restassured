package Create;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.hamcrest.core.StringStartsWith;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.fge.jsonschema.examples.Utils;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import com.thoughtworks.qdox.model.expression.Equals;

import authentication.Login;
import util.ExtentReportListener;
import static org.hamcrest.Matchers.*;


import java.io.File;


@Listeners(ExtentReportListener.class)
public class CreateSupplier extends Login{
  
	Faker faker = new Faker();
@Test(enabled = false)	
	void supplierCreate() {

	try {
		
		RestAssured.baseURI = "http://qa-api.launch-sandbox.com";
		String bodyData = "{\"emailDistribution\":{\"requisitions\":[],\"docs\":[]},\"name\":\"Monty123\",\"activeStatus\":\"Active\",\"phone\":\"3373282389\",\"federalTaxID\":\"373737373\",\"address1\":\"Mintt\",\"postalCode\":\"13123213\",\"state\":\"CA\",\"country\":\"United States\",\"mainContact\":{\"email\":\"vmssup01@yopmail.com\",\"phone\":\"3832893893\"},\"notes\":\"Some Notes\",\"diversityCategories\":[\"LBE\",\"SBE\"]}";
		
		Response response =  given()
        		.header("Content-Type", "application/json; charset=UTF-8")
        		.header("x-access-token",getToken())
        		.body(bodyData).log().all()
        		.when()
        		.post("/api/suppliers")
        		.then()
        		.statusCode(201)
        		.extract().response();
        		
        		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
        		
	}


@Test	
void workerCreate() {
	SoftAssert sf= new SoftAssert();
try {	
	String candfname = faker.name().firstName();
	String lastname = faker.name().lastName();
	String candemail = faker.internet().emailAddress();
	String candphone = faker.phoneNumber().cellPhone();
	String streetadd = faker.address().streetAddress();
	String streetname = faker.address().streetName();
	String city = faker.address().city();
	String state = faker.address().state();
	String post = faker.address().zipCode();
	String cntry = faker.address().country();
	RestAssured.baseURI = "http://qa-api.launch-sandbox.com";
	String bodyData = "{\"firstName\":\""+candfname+"\","
	        + "\"lastName\":\""+lastname+"\","
	        + "\"email\":\""+candemail+"\","
	        + "\"phone\":\""+candphone+"\","
	        + "\"dobDay\":\"10\","
	        + "\"dobMonth\":\"04\","
	        + "\"dobYear\":\"2000\","
	        + "\"address1\":\""+streetadd+"\","
	        + "\"address2\":\""+streetname+"\","
	        + "\"city\":\""+city+"\","
	        + "\"state\":\""+state+"\","
	        + "\"postalCode\":\""+post+"\","
	        + "\"country\":\""+cntry+"\","
	        + "\"emergencyContactName\":\"NAMO\","
	        + "\"emergencyContactRelationship\":\"NAMO\","
	        + "\"emergencyContactEmail\":\"qaworker1@yopmail.com\","
	        + "\"emergencyContactPhone\":\"4748348934\","
	        + "\"externalVmsId\":\"737383\","
	        + "\"accountingId\":\"7373833\","
	        + "\"notes\":\"Nothing to add\","
	        + "\"workEmail\":\""+candemail+"\","
	        + "\"persEmail\":\""+candemail+"\","
	        + "\"mobilePhone\":\"6337373484\","
	        + "\"workPhone\":\"7483839933\","
	        + "\"homePhone\":\"7389292922\"}";

	
	Response response =  given()
    		.header("Content-Type", "application/json; charset=UTF-8")
    		.header("x-access-token",getToken())
    		.body(bodyData).log().all()
    		.when()
    		.post("/api/workers")
    		.then()
    		.statusCode(200)
    		.extract().response();
	
//	String external = response.path("externalVmsId");
//	System.out.println("External vsm id value is "+external);
	response.then().body("data.externalVmsId", equalTo("737383"));
	response.then().body("data.workPhone", notNullValue());
	response.then().body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\admin\\eclipse-workspace\\com.restassured\\loginschema.json")));
	System.out.println("Response Body is *********************"+response.getBody().asString());

	 String firstName = response.path("data.firstName");
     System.out.println("firstName: " + firstName);
	
	JsonPath jsonPath= response.jsonPath();
	String external = jsonPath.getString("data.externalVmsId");
	System.out.println("external vms is "+external);
	System.out.println("Phone number is "+response.path("data.workPhone"));	
	
	int statusCode = response.getStatusCode();
	System.out.println("Status Code: " + statusCode);
	
	sf.assertEquals(201, statusCode);
	
	// Check if error (4xx or 5xx)
	if (statusCode >= 400) {
	    // Extract error message from JSON response body
	    String errorMessage = response.jsonPath().getString("error.message");
	    System.out.println("Error Message: " + errorMessage);
	}
    		
}catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
}
   sf.assertAll();		
}

}
