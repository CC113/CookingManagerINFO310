package dao;

import domain.Recipe;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author dinjo998
 */
public class RecipeStore implements RecipeDataInterface{
//	private static Collection<Recipe> recipes = new TreeSet();
	private static Map<String, Recipe> recipesByID = new HashMap<>();
	private static Map<String, Set<Recipe>> recipesInTags = new HashMap<>();
	
	static {
		if (recipesByID.isEmpty()) {
			Recipe one = new Recipe();
			one.setName("one");
			one.setDescription("This is number one");
			Recipe two = new Recipe();
			two.setName("two");
			two.setDescription("This is number two");
			recipesByID.put("1", one);
			recipesByID.put("2", two);
		}
	}
	
	@Override
	public void saveRecipe(Recipe recipe){
		//recipes.add(recipe);
		recipesByID.put(recipe.getRecipeID(), recipe);
		/*String filter = recipe.getCategory();
		if(recipesInTags.containsKey(filter)){
			(recipesInTags.get(filter)).add(recipe);
		} else{
			Set<Recipe> newSet = new HashSet<>();
			newSet.add(recipe);
			recipesInTags.put(filter, newSet);
		}*/
	}

	
	public Recipe searchRecipesSingle(String recipeID){
		if(recipesByID.containsKey(recipeID)){
			return recipesByID.get(recipeID);
		}else{
			return null;
		}
	}
	
	@Override
	public Collection<Recipe> filterByTags(String category){
		return recipesInTags.get(category);
	}
		
	@Override
	public Collection<Recipe> getRecipes(){
		return recipesByID.values();
	}

	@Override
	public void lastUserID() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public void initial() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void closeDB() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

    @Override
    public Recipe suggestSomething(String profileID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public Recipe getRecipeByID(String recipeID) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

    @Override
    public void deleteRecipe(String recipeID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public List<Recipe> searchRecipes(String searchTerm) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
