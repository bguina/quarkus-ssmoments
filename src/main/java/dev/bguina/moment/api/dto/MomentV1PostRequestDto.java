package dev.bguina.moment.api.dto;

import java.util.List;

public record MomentV1PostRequestDto(
        String input,
        Float duration,
        Integer width,
        Integer height,
        List<MomentV1PostRequestTextEffectDto> textEffects,
        String output
) {

}
