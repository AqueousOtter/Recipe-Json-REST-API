package dev.dustinb.RESTDemo.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void findAll() {
        System.out.println(recipeRepository.findAll().get("recipeNames").size());
        assertTrue(recipeRepository.findAll().size() > 0);
    }

    @Test
    void recipeDetails() {
    }

    @Test
    void saveRecipe() {
    }

    @Test
    void updateRecipe() {
    }
}