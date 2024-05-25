package dev.bguina.moment.domain.service;

import dev.bguina.moment.data.FfmpegMomentCommandBuilder;
import dev.bguina.moment.domain.model.CommandLine;
import dev.bguina.moment.domain.model.TextEffect;
import dev.bguina.moment.domain.model.VideoInfo;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MomentService {

    public CommandLine getTextEffectCommand(
            VideoInfo video,
            TextEffect textEffect,
            String outputPath
    ) {
        FfmpegMomentCommandBuilder commandBuilder = new FfmpegMomentCommandBuilder();

        return commandBuilder
                .videoInput(video)
                .addTextEffect(textEffect)
                .build(outputPath);
    }
}
