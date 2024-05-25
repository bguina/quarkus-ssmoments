package dev.bguina.moment.domain.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextEffectTests {
    @Test
    void textEffectEmptyText_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new TextEffect("", 0, 0, 32, Color.WHITE, 0, 1),
                "Expected TextEffect() to throw IllegalArgumentException"
        );
    }

    @Test
    void textEffectNegativeFontSize_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new TextEffect("text", 0, 0, 0, Color.WHITE, 0, 1),
                "Expected TextEffect() to throw IllegalArgumentException"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new TextEffect("text", 0, 0, -1, Color.WHITE, 0, 1),
                "Expected TextEffect() to throw IllegalArgumentException"
        );
    }

    @Test
    void textEffectNegativeStart_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new TextEffect("text", 0, 0, 32, Color.WHITE, -1, 1),
                "Expected TextEffect() to throw IllegalArgumentException"
        );
    }

    @Test
    void textEffectEndBeforeStart_throwsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new TextEffect("text", 0, 0, 32, Color.WHITE, 5, 4),
                "Expected TextEffect() to throw IllegalArgumentException"
        );
    }

    @Test
    void textEffectValid_doesNotThrow() {
        assertDoesNotThrow(
                () -> new TextEffect("text", 0, 0, 32, Color.WHITE, 4, 5),
                "Expected TextEffect() to not throw"
        );
    }
}
