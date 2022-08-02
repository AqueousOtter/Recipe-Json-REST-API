package dev.dustinb.RESTDemo.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.dustinb.RESTDemo.recipe.Recipe;
import dev.dustinb.RESTDemo.recipe.RecipeDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveAction;

@Repository
public class RecipeRepository {
    @Value("classpath:data.json")
    private Resource jsonResource;

    private final ObjectMapper objectMapper = new ObjectMapper();


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

    //return details for given recipename, null if not listed
    public Map<String, RecipeDetails> recipeDetails(String recipeName){
        List<Recipe> theRecipe = readJsonRecipes().stream().filter(recipe -> recipe.getName().equalsIgnoreCase(recipeName)).toList();
        if(!theRecipe.isEmpty()){
            Map<String, RecipeDetails> recipeDetailsHashMap = new HashMap<>();
            recipeDetailsHashMap.put("details", new  RecipeDetails(theRecipe.get(0).getIngredients(), theRecipe.get(0).getInstructions().size()));
            return recipeDetailsHashMap;
        }
        return null;
    }

    //save a recipe
    public void  saveRecipe(Recipe theRecipe){
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("saving");
        Recipe saveRecipe = theRecipe;
        System.out.println(theRecipe.getName());
        List<Recipe> recipes = readJsonRecipes();

        recipes.add(saveRecipe);
        try{
            //convert to json
            String jsonRecipes = objectMapper.writeValueAsString(recipes);
            File afile= new File(jsonResource.getFile().getAbsolutePath());
            afile.setWritable(true);
            try{
                FileWriter fileWriter = new FileWriter(afile, false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(jsonRecipes);
                System.out.println("DONE");
                bufferedWriter.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }



            System.out.println("finished");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("saved");

    }


    //class methods
    private List<Recipe> readJsonRecipes(){     //reads json file and populates list of recipes
        List<Recipe> allRecipes = new ArrayList<>();
        try{
            ObjectReader reader = objectMapper.reader().withRootName("recipes").forType(Recipe[].class); //reads after "recipes" in json file
            Recipe[] jsonRecipes = reader.readValue(jsonResource.getFile());
            for (Recipe jsonRecipe : jsonRecipes) { //populate a recipelist from array of recipes read
                allRecipes.add(jsonRecipe);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allRecipes;
    }
}
