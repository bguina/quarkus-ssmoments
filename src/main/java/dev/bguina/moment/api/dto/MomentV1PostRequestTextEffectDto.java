package dev.bguina.moment.api.dto;

public record MomentV1PostRequestTextEffectDto(
        String text,
        Integer x,
        Integer y,
        Integer fontSize,
        String fontColor,
        Float startSecond,
        Float endSecond
) {

}
