package ch.zli.m223.controller;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.google.inject.Inject;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.service.AppUserService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(OrderAnnotation.class)
@TestSecurity(user = "admin@gmail.com", roles = "admin")
public class AppUserControllerTest {

    @Inject
    AppUserService userService;

    @Test
    @Order(1)
    void testCreateUser() {

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{"
                        + "\"username\": \"admin\","
                        + "\"lastname\": \"Gwerder\","
                        + "\"email\": \"admin@gmail.com\","
                        + "\"password\": \"1234\","
                        + "\"isAdmin\": true"
                        + "}")
                .when()
                .post("/users/createUser")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    @Test
    @Order(2)
    public void testShowUser() {
        given()
                .when().get("/users/getAll")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void testManageMembers() {
        given()
                .when().put("/users/manageMembers/1")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(3)
    public void testDeleteUser() {
        given()
                .when().delete("/users/deleteUser/1" + 1)
                .then()
                .statusCode(204);
    }
}
