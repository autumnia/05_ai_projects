package com.autumnia.openai.recipe.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Data
public class Recipe {
    //   음식재료       요리유형      식단제한정보
    private String ingredients;
    private String cuisine;
    private String dietaryRestrictions;
}
