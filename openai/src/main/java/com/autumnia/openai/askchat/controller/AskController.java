package com.autumnia.openai.askchat.controller;

import com.autumnia.openai.askchat.service.AskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@Slf4j
@RequiredArgsConstructor
public class AskController {
    private final AskService askService;

    @GetMapping("/ask")
    public String ask( String message) {
//        log.info("message: {}", message);
        return askService.get_response(message);
    }

    @GetMapping("/ask-ai")
    public String ask_ai( String message) {
        log.info("message: {}", message);
        return askService.get_response_with_options(message);
    }
}
