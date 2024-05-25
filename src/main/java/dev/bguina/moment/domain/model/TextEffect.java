package dev.bguina.moment.domain.model;

import java.awt.*;

public record TextEffect(
        String text,
        int x,
        int y,
        int fontSize,
        Color fontColor,
        float startSecond,
        float endSecond
) {
    public TextEffect {
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Text must not be empty");
        }

        if (fontSize <= 0) {
            throw new IllegalArgumentException("Font size must be positive");
        }

        if (startSecond < 0) {
            throw new IllegalArgumentException("Start second must be positive");
        }

        if (endSecond < startSecond) {
            throw new IllegalArgumentException("End second must exceed start second");
        }
    }
}
