package dev.dustinb.RESTDemo.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RecipeRepositoryTest {

    /*
        unfinished -- work in progress
     */

    @Autowired
    private RecipeRepository recipeRepository;

    private TestInfo testInfo;
    private TestReporter testReporter;

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        System.out.println("Starting");
        this.testInfo = testInfo;
        this.testReporter = testReporter;
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
                final int chaiSteps = 4;
                assertEquals(chaiSteps, recipeRepository.recipeDetails("chai").get("details").getnumSteps());
            }
        }

    @Test
    void saveRecipe() {
    }

    @Test
    void updateRecipe() {
    }
}