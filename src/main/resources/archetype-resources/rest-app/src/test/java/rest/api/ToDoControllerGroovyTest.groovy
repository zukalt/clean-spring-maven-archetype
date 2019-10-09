#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.api;

import ${package}.rest.Application;
import io.restassured.RestAssured;
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
import spock.lang.Specification;
import groovyx.net.http.RESTClient;
import static groovyx.net.http.ContentType.JSON;

import groovyx.net.http.HTTPBuilder;
import static groovyx.net.http.Method.*;
import static groovyx.net.http.ContentType.*;
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application.class])
@ActiveProfiles("test")
class ToDoControllerGroovyTest {

    @LocalServerPort
    int randomServerPort;

    @Value('${symbol_dollar}{server.servlet.context-path}')
    String contextPath;

    RESTClient restClient ;

    @Before
    void setup() {
        restClient = new RESTClient("http://localhost:" + randomServerPort, JSON)
    }

    @After
    void clean() {
        def response  = restClient.get(path : "${symbol_dollar}{contextPath}/todos")
        response.data.forEach {item->
            restClient.delete(path: "${symbol_dollar}{contextPath}/todos/${symbol_dollar}{item.id}")
        }
    }

    @Test
    void "basic test" () {

        def response  = restClient.get(path : "${symbol_dollar}{contextPath}/todos")

        assert response.status == 200
        assert response.contentType == 'application/json'

        def todoList = response.data ;
        assert todoList.size == 0

        restClient.post(path: "${symbol_dollar}{contextPath}/todos", body: [description: "wake up early"])
        response  = restClient.get(path : "${symbol_dollar}{contextPath}/todos")

        assert 1 == response.data.size
        assertNotNull response.data[0].id

        restClient.put(path: "${symbol_dollar}{contextPath}/todos/${symbol_dollar}{response.data[0].id}", body: [description: "wake up early once", done: true])
        response  = restClient.get(path : "${symbol_dollar}{contextPath}/todos")

        assert 1 == response.data.size
        assert response.data[0].done

        restClient.delete(path: "${symbol_dollar}{contextPath}/todos/${symbol_dollar}{response.data[0].id}")
        response  = restClient.get(path : "${symbol_dollar}{contextPath}/todos")

        assert 0 == response.data.size

        response  = restClient.get(path : "${symbol_dollar}{contextPath}/todos/random")
        todoList = response.data

        assert todoList.size > 1


    }

}