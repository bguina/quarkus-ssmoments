package dev.bguina.moment.data;

import dev.bguina.moment.domain.model.CommandLine;
import dev.bguina.moment.domain.model.TextEffect;
import dev.bguina.moment.domain.model.VideoInfo;
import dev.bguina.moment.domain.service.IMomentCommandBuilder;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FfmpegMomentCommandBuilder implements IMomentCommandBuilder {
    private static final String BIN = "ffmpeg";
    private final List<TextEffect> textEffects = new ArrayList<>();
    private VideoInfo video = null;

    public FfmpegMomentCommandBuilder videoInput(VideoInfo video) {
        this.video = video;
        return this;
    }

    public FfmpegMomentCommandBuilder addTextEffect(TextEffect fx) {
        textEffects.add(fx);
        return this;
    }

    public CommandLine build(String outputPath) {
        StringBuilder sb = new StringBuilder(BIN);

        if (null == video) {
            throw new IllegalArgumentException("Video must be set");
        }

        sb.append(" -i ").append(video.path());

        for (TextEffect fx : textEffects) {
            if (fx.startSecond() > video.durationSeconds()) {
                throw new IllegalArgumentException("Invalid Start Time");
            }

            if (fx.endSecond() > video.durationSeconds()) {
                throw new IllegalArgumentException("Invalid End Time");
            }

            if (fx.x() >= video.width() || fx.y() >= video.height()) {
                throw new IllegalArgumentException("Invalid X,Y coordinate");
            }

            sb.append(
                    String.format(
                            " -vf drawtext=\""
                                    + "enable='between(t,%.2f, %.2f)'" +
                                    ":text='%s'" +
                                    ":fontColor=0x%s" +
                                    ":fontSize=%d" +
                                    ":x=%d:y=%d" +
                                    "\"",
                            fx.startSecond(),
                            fx.endSecond(),
                            fx.text(),
                            Integer.toHexString(fx.fontColor().getRGB() & 0xffffff),
                            fx.fontSize(),
                            fx.x(), fx.y()
                    )
            );
        }

        if (outputPath.isBlank()) {
            throw new IllegalArgumentException("Output video path must not be blank");
        }

        return new CommandLine(
                sb.append(" ").append(outputPath).toString()
        );
    }
}
