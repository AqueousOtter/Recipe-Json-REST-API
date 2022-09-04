package dev.dustinb.RESTDemo.repository;

import static org.junit.jupiter.api.Assertions.*;
import dev.dustinb.RESTDemo.recipe.Recipe;
import dev.dustinb.RESTDemo.recipe.RecipeDetails;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class RecipeRepositoryTest {

    /*
        unfinished -- work in progress
     */

    @Autowired
    private RecipeRepository recipeRepository;

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        System.out.println("Starting");
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
    }

    @Test
    void findAll() {

        System.out.println(recipeRepository.findAll().get("recipeNames").size());
        assertFalse(recipeRepository.findAll().isEmpty());
    }

        @Nested
        @DisplayName("Testing recipe details ")
        @Tag("recipeDetails")
        class RecipeDetailsTest {

            @Test
            @DisplayName("return is not empty")
            void testHasSize(){
                assertFalse(recipeRepository.recipeDetails("chai").isEmpty());
            }
            @Test
            @DisplayName("test number of steps for 'chai'.")
            void hasCorrectNumSteps(){
                final int chaiSteps = 4; //num from recipe.json
                assertEquals(chaiSteps, recipeRepository.recipeDetails("chai").get("details").getnumSteps());
            }

            @Test
            @DisplayName("test ingredient list values for 'chai'.")
            void hasCorrectIngredients(){
                final String ingredient = "100ml milk"; //from recipe.json
                Map<String, RecipeDetails> chaiDetails = recipeRepository.recipeDetails("chai");
                assertTrue(chaiDetails.get("details").getIngredients().get(1).equalsIgnoreCase(ingredient));
            }
        }

    @Test
    void saveRecipe() {
        List<String> instructions = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();
        instructions.add("Freeze hotdogs");
        instructions.add("Boil ice cream floats");
        instructions.add("Mix and re-freeze");
        ingredients.add("1 hotdog");
        ingredients.add("3 Ice cream floats");
        Recipe newRecipe = new Recipe("Hotdog Ice Cream", ingredients, instructions);
        recipeRepository.saveRecipe(newRecipe);
        assertTrue(recipeRepository.findAll().get("recipeNames").contains("Hotdog Ice Cream"));
    }

    @Test
    void updateRecipe() {
    }
}