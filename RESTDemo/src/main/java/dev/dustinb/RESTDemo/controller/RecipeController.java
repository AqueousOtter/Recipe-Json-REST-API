package dev.dustinb.RESTDemo.controller;

import dev.dustinb.RESTDemo.recipe.Recipe;
import dev.dustinb.RESTDemo.recipe.RecipeDetails;
import dev.dustinb.RESTDemo.service.RecipeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/all")
    public Map<String, List<String>> getAllRecipes() {
        return recipeService.findAll();
    }

    @GetMapping("/details/{recipeName}")
    public Map<String, RecipeDetails> recipeDetails(@PathVariable("recipeName") String recipeName){
        return recipeService.findDetails(recipeName);
    }

    //save a recipe
    @PostMapping()
    public void  saveRecipe(@RequestBody Recipe recipe){
        recipeService.saveRecipe(recipe);
    }
}
