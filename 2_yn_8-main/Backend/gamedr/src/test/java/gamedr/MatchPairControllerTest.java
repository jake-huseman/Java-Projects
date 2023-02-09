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
public class MatchPairControllerTest {
	
	@LocalServerPort
	int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
	}
	
	
	@Test
	public void matchTest1() {
		User user = new User("name1", "matchUsername1", "email", "desc", Actor.SKR);
		User user2 = new User("name2", "matchUsername2", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
		RestAssured.given().contentType(ContentType.JSON).body(user2).when().post("/users");
		int user1_id = user.getId();
		int user2_id = user2.getId();
		RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				post("/matchPairs/"+user1_id+"/"+user2_id);
		try {
			Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/matchPairs/users");
			JSONArray returnArr = new JSONArray(response.getBody().asString());
			JSONObject returnObj0 = returnArr.getJSONObject(0);
			JSONObject returnObj1 = returnArr.getJSONObject(1);
			assertEquals(returnObj0.get("username"), "matchUsername1");
			assertEquals(returnObj1.get("username"), "matchUsername2");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void matchTest2() {
		User user = new User("name1", "matchUsername3", "email", "desc", Actor.SKR);
		User user2 = new User("name2", "matchUsername4", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
		RestAssured.given().contentType(ContentType.JSON).body(user2).when().post("/users");
		int user1_id = user.getId();
		int user2_id = user2.getId();
		RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				post("/matchPairs/"+user1_id+"/"+user2_id);
		try {
			Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/matchPairs/users");
			JSONArray returnArr = new JSONArray(response.getBody().asString());
			JSONObject returnObj0 = returnArr.getJSONObject(0);
			JSONObject returnObj1 = returnArr.getJSONObject(1);
			assertEquals(returnObj0.get("username"), "matchUsername3");
			assertEquals(returnObj1.get("username"), "matchUsername4");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void matchTest3() {
		User user = new User("name1", "matchUsername5", "email", "desc", Actor.SKR);
		User user2 = new User("name2", "matchUsername6", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
		RestAssured.given().contentType(ContentType.JSON).body(user2).when().post("/users");
		int user1_id = user.getId();
		int user2_id = user2.getId();
		RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				post("/matchPairs/"+user1_id+"/"+user2_id);
		try {
			Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/matchPairs/users");
			JSONArray returnArr = new JSONArray(response.getBody().asString());
			JSONObject returnObj0 = returnArr.getJSONObject(0);
			JSONObject returnObj1 = returnArr.getJSONObject(1);
			assertEquals(returnObj0.get("username"), "matchUsername5");
			assertEquals(returnObj1.get("username"), "matchUsername6");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void matchTest4() {
		User user = new User("name1", "matchUsername7", "email", "desc", Actor.SKR);
		User user2 = new User("name2", "matchUsername8", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
		RestAssured.given().contentType(ContentType.JSON).body(user2).when().post("/users");
		int user1_id = user.getId();
		int user2_id = user2.getId();
		RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				post("/matchPairs/"+user1_id+"/"+user2_id);
		try {
			Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/matchPairs/users");
			JSONArray returnArr = new JSONArray(response.getBody().asString());
			JSONObject returnObj0 = returnArr.getJSONObject(0);
			JSONObject returnObj1 = returnArr.getJSONObject(1);
			assertEquals(returnObj0.get("username"), "matchUsername7");
			assertEquals(returnObj1.get("username"), "matchUsername8");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	

}









