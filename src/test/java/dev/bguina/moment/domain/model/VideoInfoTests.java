package dev.bguina.moment.domain.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VideoInfoTests {
    @Test
    void videoInfoBlankPath_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new VideoInfo("  \t", 5, 32, 32),
                "Expected VideoInfo() to throw IllegalArgumentException"
        );
    }

    @Test
    void videoInfoNegativeDuration_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new VideoInfo("video.mp4", .0f, 32, 32),
                "Expected VideoInfo() to throw IllegalArgumentException"
        );
    }

    @Test
    void videoInfoNegativeWidth_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new VideoInfo("video.mp4", 5, 0, 32),
                "Expected VideoInfo() to throw IllegalArgumentException"
        );
    }

    @Test
    void videoInfoNegativeHeight_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new VideoInfo("video.mp4", 5, 32, 0),
                "Expected VideoInfo() to throw IllegalArgumentException"
        );
    }

    @Test
    void videoInfoValid_doesNotThrow() {
        assertDoesNotThrow(
                () -> new VideoInfo("video.mp4", 5, 32, 32),
                "Expected VideoInfo() to not throw"
        );
    }
}
