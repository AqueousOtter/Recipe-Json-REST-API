package dev.dustinb.RESTDemo.recipe;

import java.util.List;

public class RecipeDetails {

    private List<String> ingredients;
    private int numSteps;

    public RecipeDetails(List<String> ingredients, int numSteps) {
        this.ingredients = ingredients;
        this.numSteps = numSteps;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> details) {
        this.ingredients = details;
    }

    public int getnumSteps() {
        return numSteps;
    }

    public void setNumSteps(int numSteps) {
        this.numSteps = numSteps;
    }
}
