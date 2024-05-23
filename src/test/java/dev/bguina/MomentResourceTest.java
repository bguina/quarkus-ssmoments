package dev.bguina;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class MomentResourceTest {
    @Test
    void testPostMomentEndpoint() {
        given()
          .when().get("/moments")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

}