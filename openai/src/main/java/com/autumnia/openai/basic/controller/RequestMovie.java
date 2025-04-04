package com.autumnia.openai.basic.controller;

public record RequestMovie(
        String subject,
        String tone,
        String message,

        String director_name
) {
}
