package ch.zli.m223.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ch.zli.m223.model.AppUser;

@QuarkusTest
@Transactional
public class AppUserControllerTest {
    @Test
    void testCreateUser() {

        AppUser appUser = new AppUser();
        appUser.setUsername("testuser");
        appUser.setEmail("test@example.com");

        AppUser createdUser = given()
        .contentType(ContentType.JSON)
        .body(appUser, ObjectMapperType.JACKSON_2)
        .when()
        .post("/users/createUser")
        .then()
        .statusCode(200)
        .extract()
        .as(AppUser.class, ObjectMapperType.JACKSON_2);

        assertEquals(appUser.getUsername(), createdUser.getUsername());
        assertEquals(appUser.getEmail(), createdUser.getEmail());
    }

    @Test
    void testDeleteBooking() {
        AppUser appUser = new AppUser();
        appUser.setUsername("deletetestuser");
        appUser.setEmail("delete@test.com");

        AppUser createdUser = given()
        .contentType(ContentType.JSON)
        .body(appUser, ObjectMapperType.JACKSON_2)
        .when()
        .post("/users/createUser")
        .then()
        .statusCode(200)
        .extract()
        .as(AppUser.class, ObjectMapperType.JACKSON_2);

        given()
        .pathParam("userId", createdUser.getId())
        .when()
        .delete("/users/deleteUser/{userId}")
        .then()
        .statusCode(204);

        given()
        .pathParam("userId", createdUser.getId())
        .when()
        .get("/users/getUser/{userId}")
        .then()
        .statusCode(404);
    }

    @Test
    void testManageMembers() {
        AppUser appUser = new AppUser();
        appUser.setUsername("updatetestuser");
        appUser.setEmail("update@test.com");

        AppUser createdUser = given()
            .contentType(ContentType.JSON)
            .body(appUser, ObjectMapperType.JACKSON_2)
            .when()
            .post("/users/createUser")
            .then()
            .statusCode(200)
            .extract()
            .as(AppUser.class, ObjectMapperType.JACKSON_2);

            AppUser updatedUser = new AppUser();
            updatedUser.setUsername("updateduser");
            updatedUser.setEmail("updated@example.com");
    
            AppUser resultUser = given()
                .contentType(ContentType.JSON)
                .pathParam("userId", createdUser.getId())
                .body(updatedUser, ObjectMapperType.JACKSON_2)
                .when()
                .put("/users/manageMembers/{userId}")
                .then()
                .statusCode(200)
                .extract()
                .as(AppUser.class, ObjectMapperType.JACKSON_2);

                assertEquals(updatedUser.getUsername(), resultUser.getUsername());
                assertEquals(updatedUser.getEmail(), resultUser.getEmail());
    }

    @Test
    void testShowUser() {
        given()
        .when()
        .get("/users/getAll")
        .then()
        .statusCode(200)
        .body("size()", greaterThanOrEqualTo(0));
    }
}
