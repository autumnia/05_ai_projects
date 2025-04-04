package com.autumnia.openai.image.domain;

public record ImageRequestRcd(
        String message,
        String model,
        int n
) {

}
