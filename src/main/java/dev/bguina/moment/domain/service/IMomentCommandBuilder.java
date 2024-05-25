package dev.bguina.moment.domain.service;

import dev.bguina.moment.data.FfmpegMomentCommandBuilder;
import dev.bguina.moment.domain.model.CommandLine;
import dev.bguina.moment.domain.model.TextEffect;
import dev.bguina.moment.domain.model.VideoInfo;

public interface IMomentCommandBuilder {
    IMomentCommandBuilder videoInput(VideoInfo video);

    IMomentCommandBuilder addTextEffect(TextEffect fx);

    CommandLine build(String outputPath);
}
