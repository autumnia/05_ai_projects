package com.autumnia.openai.speechtoimage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ai")
public class STIRouterController {
    @GetMapping("/speech-to-image")
    public String imageview(){
        return "speech_to_image"; // image.html
    }
}
