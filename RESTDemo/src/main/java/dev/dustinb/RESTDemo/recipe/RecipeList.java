package dev.dustinb.RESTDemo.recipe;

import java.util.List;

public class RecipeList {

    private List<Recipe> recipes;

    public RecipeList(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
