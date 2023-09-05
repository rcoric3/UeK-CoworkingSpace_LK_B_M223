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
public class BookingControllerTest {
    @Test
    @Order(4)
    void testChangeStatus() {
        given()
        .contentType(MediaType.APPLICATION_JSON)
                .when().put("/booking/changeStatus/2")
                .then()
                .statusCode(500);
    }

    @Test
    void testCheckBooking() {
        given()
                .when().get("/booking/checkBookingStatus/1")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(1)
    void testCreateNewBooking() {
        given()
                .contentType(MediaType.APPLICATION_JSON).body("{" +
                        "\"type\": \"exampleType\"," +
                        "\"date\": \"2023-09-10T00:00:00Z\"" +
                        "}")
                .when().post("/booking/createBooking")
                .then()
                .statusCode(200);

    }

    @Test
    @Order(5)
    void testDeleteBooking() {
        given()
                .when().delete("/booking/cancelBooking/1" + 1)
                .then()
                .statusCode(204);
    }

    @Test
    @Order(3)
    void testManageBookingRequest() {
        given()
                .when()
                .put("/booking/manageBookingRequests/2")
                .then()
                .statusCode(204);
    }
}
