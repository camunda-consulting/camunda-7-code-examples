package com.camunda.consulting;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class GreetingResourceTest {

  @Test
  public void testHelloEndpoint() {
    given()
        .when()
        .get("/engine-rest/version")
        .then()
        .statusCode(200)
        .body(is("{\"version\":\"7.18.0\"}"));
  }

}