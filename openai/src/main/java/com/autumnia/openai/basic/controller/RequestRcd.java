package com.autumnia.openai.basic.controller;


public record RequestRcd(
        String subject,
        String tone,
        String message,
        String format,
        String direcotor_name
) {
}
