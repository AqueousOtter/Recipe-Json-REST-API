package dev.dustinb.RESTDemo.recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeList {

    /*
        Wrapper class for recipes
     */

    private List<Recipe> recipes;
    private RecipeDetails details;


    public RecipeList() {
        this.recipes = new ArrayList<>();
    }

    public RecipeList(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void setRecipeDetails(String theRecipe){
        List<String> recipeDetails = (recipes.stream().filter(recipe -> recipe.getName().equalsIgnoreCase(theRecipe)).toList().get(0).getIngredients());
        int steps = (recipes.stream().filter(recipe -> recipe.getName().equalsIgnoreCase(theRecipe)).toList().get(0).getInstructions().size());
        setDetails(new RecipeDetails(recipeDetails,steps));
    }

    public RecipeDetails getDetails() {
        return details;
    }

    public void setDetails(RecipeDetails details) {
        this.details = details;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
