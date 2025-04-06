package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;

@DisplayName("Recipe Book Test")
class RecipeBookTest {
	private RecipeBook recipeBook;
	private Recipe recipe1;

	@BeforeEach
	private void setUp() {
		System.out.println("Setting up Recipe Book Test");
		recipeBook=  new RecipeBook();
		recipe1=new Recipe();
		recipe1.setName("Tea");
	}
	
	@Test
	@DisplayName(" adding a recipe")
	void testAddingRecipe() {
		assertTrue(recipeBook.addRecipe(recipe1));
		assertFalse(recipeBook.addRecipe(recipe1));	
	}
	@Test 
	@DisplayName("Deleting a recipe")
	void testDeleteRicepe() {
		recipeBook.addRecipe(recipe1);
		assertEquals("Tea", recipeBook.deleteRecipe(0));

	}
	@Test 
	@DisplayName("Edit a recipe")
	void testEditRecipe() {
		recipeBook.addRecipe(recipe1);
		assertEquals("Tea", recipeBook.editRecipe(0, recipe1));
		assertEquals("", recipeBook.getRecipes()[0].getName());

	}
	
	
	@Test
	@DisplayName("Test adding duplicate ")
	@Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
	void testAddingDuplicate() {
		for(int i = 0; i < 4; i++) {
			  assertTrue(recipeBook.addRecipe(new Recipe()));
		}
	}
	
	@Test
	@DisplayName("Test adding limit ")
	@Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
	void testAddingLimit() {
		for(int i = 0; i < 4; i++) {
			Recipe recipe=new Recipe();
			recipe.setName("Recipe"+i);
			assertTrue(recipeBook.addRecipe(recipe));
		}
		assertFalse(recipeBook.addRecipe(new Recipe()));
	}
	
	
	
	
	@ParameterizedTest
	@DisplayName("adding recipes with different names")
	@ValueSource(strings = {"Espresso","Macchiato","Affogato"})
	void testAddRecipe(String recipeName) {
		Recipe recipe=new Recipe();
		recipe.setName(recipeName);
		assertTrue(recipeBook.addRecipe(recipe));
	}
	
	@Test 
	@DisplayName("Intentionally failing test ")
	void testFailingAddRecipe() {
		Recipe recipe= new Recipe();
		assertThrows(RecipeException.class, ()->recipe.setPrice("name"));
		assertThrows(RecipeException.class, ()->recipe.setAmtCoffee("-50"));
		assertThrows(RecipeException.class, ()->recipe.setAmtCoffee("50.5"));
		assertThrows(RecipeException.class, ()->recipe.setAmtChocolate("50.5"));
		assertThrows(RecipeException.class, ()->recipe.setAmtChocolate("-15"));
		assertThrows(RecipeException.class, ()->recipe.setAmtSugar("15.5"));
		assertThrows(RecipeException.class, ()->recipe.setAmtSugar("-15"));
		assertThrows(RecipeException.class, ()->recipe.setAmtMilk("-15"));
		assertThrows(RecipeException.class, ()->recipe.setAmtMilk("15.7"));
		assertThrows(RecipeException.class, ()->recipe.setPrice("-10"));
	}
	@Test
	@DisplayName("Recipe Hash code")
	void testHashCode() {
		Recipe recipe2= new Recipe();
		recipe2.setName("Tea");
		assertEquals(recipe1.hashCode(), recipe2.hashCode());
	}
}
