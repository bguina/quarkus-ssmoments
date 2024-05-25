package dev.bguina.moment.data;

import dev.bguina.moment.domain.model.TextEffect;
import dev.bguina.moment.domain.model.VideoInfo;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FfmpegMomentCommandBuilderTests {

    private final FfmpegMomentCommandBuilder builder = new FfmpegMomentCommandBuilder();

    @Test
    void testGivenInputNotSet_throwsIllegalArgumentException() {
        builder
                .addTextEffect(new TextEffect(
                        "hi",
                        0, 0,
                        32, Color.WHITE,
                        4.0f, 8.0f
                ));

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.build("out.mp4"),
                "Expected build() to throw IllegalArgumentException"
        );
    }

    @Test
    void testGivenTextEffectXOutOfBound_throwsIllegalArgumentException() {
        int videoWidth = 50;

        builder
                .videoInput(new VideoInfo(
                        "input.mp4",
                        80f,
                        videoWidth, 50
                ))
                .addTextEffect(new TextEffect(
                        "hi",
                        videoWidth + 1, 0,
                        32, Color.WHITE,
                        4.0f, 8.0f
                ));

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.build("out.mp4"),
                "Expected build() to throw IllegalArgumentException"
        );
    }

    @Test
    void testGivenTextEffectYOutOfBound_throwsIllegalArgumentException() {
        int videoHeight = 50;

        builder
                .videoInput(new VideoInfo(
                        "input.mp4",
                        80f,
                        50, videoHeight
                ))
                .addTextEffect(new TextEffect(
                        "hi",
                        0, videoHeight + 1,
                        32, Color.WHITE,
                        4.0f, 8.0f
                ));

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.build("out.mp4"),
                "Expected build() to throw IllegalArgumentException"
        );
    }

    @Test
    void testGivenTextEffectOutputBlank_throwsIllegalArgumentException() {
        builder
                .videoInput(new VideoInfo(
                        "input.mp4",
                        80f,
                        50, 50
                ))
                .addTextEffect(new TextEffect(
                        "hi",
                        0, 0,
                        32, Color.WHITE,
                        4.0f, 8.0f
                ));

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.build("  "),
                "Expected build() to throw IllegalArgumentException"
        );
    }

    @Test
    void testGivenTextEffectValid_returnsCommandLine() {
        builder
                .videoInput(new VideoInfo(
                        "input.mp4",
                        80f,
                        50, 50
                ))
                .addTextEffect(new TextEffect(
                        "hi",
                        0, 0,
                        32, Color.WHITE,
                        4.0f, 8.0f
                ));

        assertEquals(
                "ffmpeg -i input.mp4 -vf drawtext=\"enable='between(t,4.00, 8.00)':text='hi':fontColor=0xffffff:fontSize=32:x=0:y=0\" out.mp4",
                builder.build("out.mp4").text()
        );
    }
}
