package dao;

import domain.Recipe;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author dinjo998
 */
public interface RecipeDataInterface {

	Collection<Recipe> filterByTags(String tag);

	Collection<Recipe> getRecipes();

	void saveRecipe(Recipe recipe);

	Collection<Recipe> searchRecipes(String searchTerm);

	Recipe suggestSomething(String profileID);

	void lastUserID();

	void initial();

	void closeDB();

	Recipe getRecipeByID(String recipeID);

	void deleteRecipe(String recipeID);
}
