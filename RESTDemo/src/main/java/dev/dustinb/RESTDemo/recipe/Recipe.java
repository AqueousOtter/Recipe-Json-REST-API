package dev.dustinb.RESTDemo.recipe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class Recipe {

    private String name;
    private List<String> ingredients;
    private List<String> instructions;

    public Recipe() {
    }

    @JsonCreator
    public Recipe( @JsonProperty("name") String name, @JsonProperty("ingredients") List<String> ingredients, @JsonProperty("instructions") List<String> instructions) {

        this.name = name;     
        this.ingredients = ingredients;
        this.instructions = instructions;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("ingredients")
    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    @JsonGetter("instructions")
    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
