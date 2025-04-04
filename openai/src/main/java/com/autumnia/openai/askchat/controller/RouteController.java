package com.autumnia.openai.askchat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ai")
@Slf4j
public class RouteController {
    @GetMapping("/ask-form")
    public String ask_form( ) {
        return "ask";
    }
}
