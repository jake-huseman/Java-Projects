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
public class ReportControllerTest {
	
	@LocalServerPort
	int port;
	
	@Autowired
	ReportRepository reportRepository;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
	}
	
	@Test
	public void postReport1() {
		User user = new User("name1", "reportUsername1", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
		RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				//body("users").
				when().
				post("/report/?username=reportUsername1&reason=annoying");
		int len = reportRepository.findAll().size();
		int report_id = reportRepository.findAll().get(len-1).getId();
		Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				//body("users").
				when().
				get("/report/" + report_id);
		try {
			JSONObject returnObj = new JSONObject(response.getBody().asString());
			System.out.println(returnObj.get("reason"));
			assertEquals(returnObj.get("username"), "reportUsername1");
			assertEquals(returnObj.get("reason"), "annoying");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void postReport2() {
		User user = new User("name1", "reportedUsername2", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
		RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				//body("users").
				when().
				post("/report/?username=reportedUsername2&reason=annoying");
		int len = reportRepository.findAll().size();
		int report_id = reportRepository.findAll().get(len-1).getId();
		Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				//body("users").
				when().
				get("/report/" + report_id);
		try {
			JSONObject returnObj = new JSONObject(response.getBody().asString());
			System.out.println(returnObj.get("reason"));
			assertEquals(returnObj.get("username"), "reportedUsername2");
			assertEquals(returnObj.get("reason"), "annoying");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void postReport3() {
		User user = new User("name1", "reportedUsername3", "email", "desc", Actor.SKR);
		RestAssured.given().contentType(ContentType.JSON).body(user).when().post("/users");
		RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				//body("users").
				when().
				post("/report/?username=reportedUsername3&reason=annoying");
		int len = reportRepository.findAll().size();
		int report_id = reportRepository.findAll().get(len-1).getId();
		Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				//body("users").
				when().
				get("/report/" + report_id);
		try {
			JSONObject returnObj = new JSONObject(response.getBody().asString());
			System.out.println(returnObj.get("reason"));
			assertEquals(returnObj.get("username"), "reportedUsername3");
			assertEquals(returnObj.get("reason"), "annoying");
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void reportTest() {
		Response response = RestAssured.given().
				header("Content-Type", "text/plain").
				header("charset","utf-8").
				when().
				get("/report");


		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
	}
	

}









