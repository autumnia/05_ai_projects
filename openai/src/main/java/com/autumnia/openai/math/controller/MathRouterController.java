package com.autumnia.openai.math.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ai")
public class MathRouterController {
    @GetMapping("/mathview")
    public String imageview(){
        return "math_view";
    }
}
