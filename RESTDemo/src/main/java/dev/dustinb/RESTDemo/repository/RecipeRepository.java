package dev.dustinb.RESTDemo.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.dustinb.RESTDemo.exception.RecipesException;
import dev.dustinb.RESTDemo.recipe.Recipe;
import dev.dustinb.RESTDemo.recipe.RecipeDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class RecipeRepository {

    @Value("classpath:data.json")
    private Resource jsonResource;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RecipeRepository(){

    }



    //return all recipe names
    public Map<String, List<String>> findAll(){
        HashMap<String, List<String>> findAllMap = new HashMap<>();
        List<String> recipeNames = new ArrayList<>();
        List<Recipe> recipes = readJsonRecipes();
        for (Recipe recipe : recipes) {
            recipeNames.add(recipe.getName());
        }
        findAllMap.put("recipeNames",recipeNames);
        return findAllMap;
    }

    //return details for given recipeName, null if not listed
    public Map<String, RecipeDetails> recipeDetails(String recipeName){
        List<Recipe> theRecipe = readJsonRecipes().stream().filter(recipe -> recipe.getName().equalsIgnoreCase(recipeName)).toList();
        if(theRecipe.isEmpty()){
            return null;
        }
        Map<String, RecipeDetails> recipeDetailsHashMap = new HashMap<>();
        RecipeDetails theDetails = new RecipeDetails(theRecipe.get(0).getIngredients(), theRecipe.get(0).getInstructions().size());
       // theRecipeDetails.add(new RecipeDetails(theRecipe.get(0).getIngredients(), theRecipe.get(0).getInstructions().size()));
        recipeDetailsHashMap.put("details", theDetails);
        return recipeDetailsHashMap;

    }

    //save a recipe if not currently in json file
    public void saveRecipe(Recipe theRecipe){
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Recipe> recipes = readJsonRecipes(); //read current json file list of recipes
        for (Recipe recipe : recipes) { //checks for duplicate
            if (recipe.getName().equalsIgnoreCase(theRecipe.getName())) {
                throw new RecipesException("400", "Recipe already saved");
            }
        }
        recipes.add(theRecipe);
        Map<String, List<Recipe>> recipeMap = new HashMap<>();
        recipeMap.put("recipes", recipes); // prepare recipes for saving to updated json file
        saveJsonRecipes(recipeMap);
    }

    //remove recipe from json file given string name
    public void deleteRecipe(String recipeName){
        List<Recipe> recipes = readJsonRecipes();

        for (Recipe recipe : recipes) { //loops through and removes if there is a match
            if (recipe.getName().equalsIgnoreCase(recipeName)) {
                recipes.remove(recipe);
                break;
            }
        }
        Map<String, List<Recipe>> recipeMap = new HashMap<>();
        recipeMap.put("recipes", recipes); // prepare recipes for saving to updated json file
        saveJsonRecipes(recipeMap);

    }

    public void updateRecipe(Recipe theRecipe) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Recipe> recipes = readJsonRecipes(); //read current json file list of recipes
        boolean isChanged = false;
        for (Recipe recipe : recipes) { //checks for matching recipe
            if (recipe.getName().equalsIgnoreCase(theRecipe.getName())) {
                recipe.setIngredients(theRecipe.getIngredients());
                recipe.setInstructions(theRecipe.getInstructions());
                isChanged = true;
                break;
            }
        }
        if(!isChanged) {
            throw new RecipesException("404", "Recipe does not exist");
        }
        Map<String, List<Recipe>> recipeMap = new HashMap<>();
        recipeMap.put("recipes", recipes); // prepare recipes for saving to updated json file
        saveJsonRecipes(recipeMap);
    }

    //class methods
    private List<Recipe> readJsonRecipes(){     //reads json file and populates list of recipes
        List<Recipe> allRecipes = new ArrayList<>();
        try{
            ObjectReader reader = objectMapper.reader().withRootName("recipes").forType(Recipe[].class); //reads after "recipes" in json file
            Recipe[] jsonRecipes = reader.readValue(jsonResource.getFile());
            for (Recipe jsonRecipe : jsonRecipes) { //populate a recipe list from array of recipes read
                allRecipes.add(jsonRecipe);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allRecipes;
    }

    //saves map of recipes to json file.
    private void saveJsonRecipes(Map<String, List<Recipe>> recipes){
        try{
            String jsonRecipes = objectMapper.writeValueAsString(recipes);
            BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter(jsonResource.getFile()));
            bufferedWriter.write(jsonRecipes);
            bufferedWriter.flush();
            bufferedWriter.close();
            System.out.println("save successful");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
