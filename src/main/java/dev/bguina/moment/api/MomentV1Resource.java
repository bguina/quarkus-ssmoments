package dev.bguina.moment.api;

import dev.bguina.moment.domain.model.TextEffect;
import dev.bguina.moment.domain.model.VideoInfo;
import dev.bguina.moment.domain.service.MomentService;
import dev.bguina.moment.api.dto.MomentV1PostRequestDto;
import dev.bguina.moment.api.dto.MomentV1PostRequestTextEffectDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.awt.*;

@Path("/v1/moment")
public class MomentV1Resource {
    @Inject
    MomentService service;

    @POST
    public String postMoment(MomentV1PostRequestDto req) {
        MomentV1PostRequestTextEffectDto fx = req.textEffects().getFirst();

        return service.getTextEffectCommand(
                new VideoInfo(
                        req.input(),
                        req.duration(),
                        req.width(),
                        req.height()
                ),
                new TextEffect(
                        fx.text(),
                        fx.x(),
                        fx.y(),
                        fx.fontSize(),
                        Color.decode(fx.fontColor()),
                        fx.startSecond(),
                        fx.endSecond()
                ),
                req.output()
        ).text();
    }
}
