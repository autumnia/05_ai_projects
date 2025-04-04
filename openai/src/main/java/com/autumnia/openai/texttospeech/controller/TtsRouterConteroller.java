package com.autumnia.openai.texttospeech.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ai")
public class TtsRouterConteroller {
    @GetMapping("/tts")
    public String audioPlay(){
        return "tts";
    }
}
