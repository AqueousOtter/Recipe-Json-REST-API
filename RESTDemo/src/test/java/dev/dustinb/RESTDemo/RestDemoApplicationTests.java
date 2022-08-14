package dev.dustinb.RESTDemo;

import dev.dustinb.RESTDemo.controller.RecipeController;
import dev.dustinb.RESTDemo.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestDemoApplicationTests {
	RecipeController recipeController;
	RecipeService recipeService;


	@BeforeEach
	void init(){



	}

	@Test
	void contextLoads() {
	}

	@Test
	void getAllRecipes(){

	}

}
