package gamedr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import gamedr.Report.ReportRepository;
import gamedr.Users.Actor;
import gamedr.Users.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * @author David Dong
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RejectionControllerTest {
	
	@LocalServerPort
	int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
	}
	
	
	@Test
	public void rejectionTest1() {
		User user = new User("name1", "rejectionUsername1", "email", "desc", Actor.SKR);
		User user2 = new User("name2", "rejectionUsername2", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
		RestAssured.given().contentType(ContentType.JSON).body(user2).when().post("/users");
		int user1_id = user.getId();
		int user2_id = user2.getId();
		RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				post("/rejection/"+user1_id+"/"+user2_id+"/false");
		try {
			Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/rejection/user");
			JSONArray returnArr0 = new JSONArray(response.getBody().asString());
			JSONArray returnArr1 = returnArr0.getJSONArray(returnArr0.length()-1);
			JSONObject returnObj0 = returnArr1.getJSONObject(0);
			JSONObject returnObj1 = returnArr1.getJSONObject(1);
			assertEquals(returnObj0.get("username"), "rejectionUsername1");
			assertEquals(returnObj1.get("username"), "rejectionUsername2");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void rejectionTest2() {
		User user = new User("name1", "rejectionUsername3", "email", "desc", Actor.SKR);
		User user2 = new User("name2", "rejectionUsername4", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
		RestAssured.given().contentType(ContentType.JSON).body(user2).when().post("/users");
		int user1_id = user.getId();
		int user2_id = user2.getId();
		RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				post("/rejection/"+user1_id+"/"+user2_id+"/false");
		try {
			Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/rejection/user");
			JSONArray returnArr0 = new JSONArray(response.getBody().asString());
			JSONArray returnArr1 = returnArr0.getJSONArray(returnArr0.length()-1);
			JSONObject returnObj0 = returnArr1.getJSONObject(0);
			JSONObject returnObj1 = returnArr1.getJSONObject(1);
			assertEquals(returnObj0.get("username"), "rejectionUsername3");
			assertEquals(returnObj1.get("username"), "rejectionUsername4");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void rejectionTest3() {
		User user = new User("name1", "rejectionUsername5", "email", "desc", Actor.SKR);
		User user2 = new User("name2", "rejectionUsername6", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
		RestAssured.given().contentType(ContentType.JSON).body(user2).when().post("/users");
		int user1_id = user.getId();
		int user2_id = user2.getId();
		RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				post("/rejection/"+user1_id+"/"+user2_id+"/false");
		try {
			Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/rejection/user");
			JSONArray returnArr0 = new JSONArray(response.getBody().asString());
			JSONArray returnArr1 = returnArr0.getJSONArray(returnArr0.length()-1);
			JSONObject returnObj0 = returnArr1.getJSONObject(0);
			JSONObject returnObj1 = returnArr1.getJSONObject(1);
			assertEquals(returnObj0.get("username"), "rejectionUsername5");
			assertEquals(returnObj1.get("username"), "rejectionUsername6");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	

}









