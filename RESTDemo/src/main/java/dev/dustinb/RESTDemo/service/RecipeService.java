package dev.dustinb.RESTDemo.service;

import dev.dustinb.RESTDemo.recipe.RecipeDetails;
import dev.dustinb.RESTDemo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class RecipeService  {
    RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository theRecipeRepository){
        recipeRepository = theRecipeRepository;
    }

    public Map<String, List<String>> findAll(){
        return recipeRepository.findAll();
    }

    public Map<String, RecipeDetails> findDetails(String recipeName){
        return recipeRepository.recipeDetails(recipeName);
    }
}
