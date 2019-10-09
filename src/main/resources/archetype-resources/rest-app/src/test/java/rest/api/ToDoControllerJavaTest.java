#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.api;

import ${package}.rest.Application;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class})
@ActiveProfiles("test")
public class ToDoControllerJavaTest {

    @LocalServerPort
    int randomServerPort;

    @Value("${symbol_dollar}{server.servlet.context-path}")
    String contextPath;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:" + randomServerPort+contextPath;
    }

    @After
    public void reset() {
        Response r = given().get("/todos").thenReturn() ;
        List<String> idList = r.jsonPath().getList("id");
        for(String id: idList) {
            given().delete("/todos/"+id).andReturn();
        }
        RestAssured.reset();
    }

    @Test
    public void basicTest() {
        given().log().all().
                get("/todos").then().body("${symbol_dollar}", hasSize(0));

    }

    @Test
    public void basicTest2() {
        given().log().all().
                get("/todos/random").then().body("${symbol_dollar}", not(hasSize(0)));

    }

}