package authentication;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(util.ExtentReportListener.class)
public class Login {
	public String tokenString;
	@Test
	protected
    String getToken() {
        RestAssured.baseURI = "http://qa-api.launch-sandbox.com";
        Response response = given()
        		.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("email", "qasankar.kumar@yopmail.com")
                .formParam("password", "Test1234")
                .log().all()
                .when()
                .post("/api/login") // Replace with actual login endpoint path
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\admin\\eclipse-workspace\\com.restassured\\loginschema.json")))
                .statusCode(200)
                .log().all()
                .extract()
                .response();
               

        String tokenString = response.then().extract().path("data.authToken");
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\admin\\eclipse-workspace\\com.restassured\\loginschema.json")));
        response.then().assertThat().statusCode(200);
        
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Token: " + tokenString);
		
		
		JsonPath jsonPath=response.jsonPath();
		String tokenString1 = jsonPath.getString("data.authToken");
		System.out.println("Token next is "+tokenString1);
		
		String tokenString2 = jsonPath.getString("data.userInfo.userID");
		System.out.println("Token next is "+tokenString2);
		
		return tokenString;
    }
	

}
