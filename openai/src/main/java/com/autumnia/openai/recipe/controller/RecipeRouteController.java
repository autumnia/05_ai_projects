package com.autumnia.openai.recipe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ai")
public class RecipeRouteController {
    @GetMapping("/v1/recipeview")
    public String recipeview(){
        return "recipe";
    }
}
