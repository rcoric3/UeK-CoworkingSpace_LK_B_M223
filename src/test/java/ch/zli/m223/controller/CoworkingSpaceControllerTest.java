package ch.zli.m223.controller;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(OrderAnnotation.class)
@TestSecurity(user = "admin@gmail.com", roles = "admin")
public class CoworkingSpaceControllerTest {
    @Test
    @Order(2)
    void testCoworkingSpace() {
        given()
                .when().put("/coworkingSpace/addCoworkingSpaceToFavorite")
                .then()
                .statusCode(500);
    }

    @Test
    @Order(1)
    void testCreateNewBooking() {
        given() .contentType(MediaType.APPLICATION_JSON)
                .body("{" +
                        "\"name\": \"Example Coworking Space\"," +
                        "\"street\": \"123 Main Street\"," +
                        "\"availability\": true," +
                        "\"favorite\": false" +
                        "}")
                .when()
                .post("/coworkingSpace/createCoworkingSpace")
                .then()
                .statusCode(200);

    }

}
