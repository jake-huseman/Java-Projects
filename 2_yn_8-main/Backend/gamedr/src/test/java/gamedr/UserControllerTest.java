package gamedr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import gamedr.Users.Actor;
import gamedr.Users.User;
import gamedr.Users.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	
	@LocalServerPort
	int port;
	
//	@Autowired
//	UserRepository userRepository;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
		User user = new User("name1", "username", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
	}
	
	@Test
	public void deleteLastUser() {
		Response response1 = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset", "utf-8").
				when().
				get("/users");
		try {
			JSONArray returnArr1 = new JSONArray(response1.getBody().asString());
			int len1 = returnArr1.length();
			JSONObject returnObj = returnArr1.getJSONObject(returnArr1.length()-1);
			int last_id = returnObj.getInt("id");
			Response tempResponse = RestAssured.given().
					header("Content-Type", "text/plain").
					header("charset", "utf-8").
					when().
					delete("/users/" + last_id);
			Response response2 = RestAssured.given().
					header("Content-Type", "text/plain").
					header("charset", "utf-8").
					when().
					get("/users");
			JSONArray returnArr2 = new JSONArray(response2.getBody().asString());
			int len2 = returnArr2.length();
			assertEquals(len2, len1-1);
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllUsersTest1() {
		Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset", "utf-8").
				when().
				get("/users");
		try {
			JSONArray returnArr = new JSONArray(response.getBody().asString());
			JSONObject returnObj = returnArr.getJSONObject(returnArr.length()-1);
			assertEquals(returnObj.get("name"), "name1");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getSpecificUserTest1() {
		Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/users/1");

		try {
			JSONObject responseJSONObject = new JSONObject(response.getBody().asString());
			assertEquals(responseJSONObject.get("id"), 1);
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getSpecificUserTest2() {
		Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/users/2");

		try {
			JSONObject responseJSONObject = new JSONObject(response.getBody().asString());
			assertEquals(responseJSONObject.get("id"), 2);
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void deleteAllUsers() {
		Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset", "utf-8").
				when().
				delete("/users");
	}

}









