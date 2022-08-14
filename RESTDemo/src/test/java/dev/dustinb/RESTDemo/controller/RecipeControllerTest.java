package dev.dustinb.RESTDemo.controller;

import dev.dustinb.RESTDemo.recipe.RecipeDetails;
import dev.dustinb.RESTDemo.repository.RecipeRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MockMvc mvc;


    @Test
    @DisplayName("Find all recipe names")
    void getAllRecipes() {
        try{
            RequestBuilder request = get("/recipes/all");
            MvcResult result = mvc.perform(request).andReturn();
            Map<String, List<String>> lExamples = recipeRepository.findAll();
            JSONObject json = new JSONObject(lExamples);

            assertEquals(json.toString(), result.getResponse().getContentAsString());
        } catch (Exception e){
            fail();
        }

    }

    @Test
    @DisplayName("Find details for given recipe name")
    void recipeDetails() {
        try{
//            String recipeName = recipeRepository.findAll().get("recipeNames").get(1); //pulls name from map of recipenames
//            System.out.println(recipeName);
//            String getRequest = "recipes/details/scrambledEggs";
            RequestBuilder request = get("/recipes/details/scrambledEggs").requestAttr("recipeName", "scrambledEggs" );
            MvcResult result = mvc.perform(request).andReturn();
            System.out.println(recipeRepository.recipeDetails("scrambledEggs"));


            //TODO: expected is wrong


            Map<String, RecipeDetails> lExamples = recipeRepository.recipeDetails("scrambledEggs");
            JSONObject json = new JSONObject(lExamples);
            System.out.println(json);
            assertEquals(json.toString(), result.getResponse().getContentAsString());
        } catch (Exception e){
            fail();
        }
    }

    @Test
    void saveRecipe() {
    }

    @Test
    void updateRecipe() {
    }
}