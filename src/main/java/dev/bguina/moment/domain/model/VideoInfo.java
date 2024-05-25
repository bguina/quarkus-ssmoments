package dev.bguina.moment.domain.model;

public record VideoInfo(
        String path,
        float durationSeconds,
        int width,
        int height
) {
    public VideoInfo {
        if (path.isBlank()) {
            throw new IllegalArgumentException("Video path must not be blank");
        }

        if (durationSeconds <= .0f) {
            throw new IllegalArgumentException("Video duration must be positive");
        }

        if (width <= 0) {
            throw new IllegalArgumentException("Video width must be positive");
        }

        if (height <= 0) {
            throw new IllegalArgumentException("Video height must be positive");
        }
    }
}
