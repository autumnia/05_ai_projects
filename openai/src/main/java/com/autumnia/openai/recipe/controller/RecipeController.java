package com.autumnia.openai.recipe.controller;

import com.autumnia.openai.recipe.domain.Recipe;
import com.autumnia.openai.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping("/v1/recipe")
    public Map<String, Object> recipe(@RequestBody Recipe recipe) throws IOException {
        return recipeService.createRecipeWithUrls(recipe);
    }
}