package ch.zli.m223.controller;

import org.junit.jupiter.api.Test;

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
import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(OrderAnnotation.class)
@TestSecurity(user = "admin@gmail.com", roles = "admin")
public class AdminControllerTest {
    @Test
    void testLogin() {
        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body("{"
                + "\"username\": \"user\","
                + "\"lastname\": \"userlastname\","
                + "\"email\": \"user@gmail.com\","
                + "\"password\": \"4321\","
                + "\"isAdmin\": false"
                + "}")
        .when()
        .post("/admin/login")
        .then()
        .statusCode(500)
        .extract()
        .response();
    }
}
