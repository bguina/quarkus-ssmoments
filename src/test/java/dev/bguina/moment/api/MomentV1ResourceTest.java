package dev.bguina.moment.api;

import dev.bguina.moment.api.dto.MomentV1PostRequestDto;
import dev.bguina.moment.api.dto.MomentV1PostRequestTextEffectDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

@QuarkusTest
public class MomentV1ResourceTest {
    @Test
    void postMomentV1BlankInput_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                        " ", 42f, 1024, 768,
                        List.of(new MomentV1PostRequestTextEffectDto(
                                "text",
                                0, 0,
                                32,
                                "0xfff",
                                32f,
                                40f
                        )),
                        "output.mp4"
                ))
                .then()
                .statusCode(400)
                .body(is("Video path must not be blank"))
        ;
    }

    @Test
    void postMomentV1NegativeDuration_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", -42f, 1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        0, 0,
                        32,
                        "0xfff",
                        32f,
                        40f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("Video duration must be positive"))
        ;
    }

    @Test
    void postMomentV1NegativeWidth_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, -1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        0, 0,
                        32,
                        "0xfff",
                        32f,
                        40f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("Video width must be positive"))
        ;
    }

    @Test
    void postMomentV1NegativeHeight_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, 1024, -768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        0, 0,
                        32,
                        "0xfff",
                        32f,
                        40f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("Video height must be positive"))
        ;
    }

    @Test
    void postMomentV1EmptyText_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, 1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "",
                        0, 0,
                        32,
                        "0xfff",
                        32f,
                        40f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("Text must not be empty"))
        ;
    }

    @Test
    void postMomentV1OutOfBoundsX_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, 1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        1024, 0,
                        32,
                        "0xfff",
                        32f,
                        40f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("Invalid X,Y coordinate"))
        ;
    }

    @Test
    void postMomentV1OutOfBoundsY_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, 1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        0, 768,
                        32,
                        "0xfff",
                        32f,
                        40f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("Invalid X,Y coordinate"))
        ;
    }

    @Test
    void postMomentV1NegativeFontSize_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, 1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        0, 0,
                        0,
                        "0xfff",
                        32f,
                        40f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("Font size must be positive"))
        ;
    }

    @Test
    void postMomentV1InvalidColor_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, 1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        0, 0,
                        32,
                        "xyz",
                        32f,
                        40f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("For input string: \"xyz\""))
        ;
    }

    @Test
    void postMomentV1NegativeStart_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, 1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        0, 0,
                        32,
                        "0xfff",
                        -1f,
                        40f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("Start second must be positive"))
        ;
    }

    @Test
    void postMomentV1EndLesserThanStart_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, 1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        0, 0,
                        32,
                        "0xfff",
                        2f,
                        1f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("End second must exceed start second"))
        ;
    }

    @Test
    void postMomentV1EndOutOfBound_returnsBadRequest() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, 1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        0, 0,
                        32,
                        "0xfff",
                        32f,
                        42.5f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(400)
                .body(is("Invalid End Time"))
        ;
    }

    @Test
    void postMomentV1Valid_returnsOk() {
        givenPost(new MomentV1PostRequestDto(
                "input.mp4", 42f, 1024, 768,
                List.of(new MomentV1PostRequestTextEffectDto(
                        "text",
                        0, 0,
                        32,
                        "0xffffff",
                        32f,
                        42f
                )),
                "output.mp4"
        ))
                .then()
                .statusCode(200)
                .body(startsWith("ffmpeg"))
        ;
    }

    private Response givenPost(MomentV1PostRequestDto req) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.TEXT)
                .body(req)
                .when()
                .post("/v1/moment");
    }
}
