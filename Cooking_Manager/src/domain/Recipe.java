package domain;

import java.util.List;

/**
 *
 * @author dinjo998
 */
public class Recipe {
	private String recipeID;
	private String name;
	private String prepTime;
	private String servingSize;
	private List<String> tags;
	// private List<String> allergyTags;
	private String description;
	private String ingredients;
	private String method;

	public Recipe(String recipeID, String name, String prepTime, String servingSize, List<String> tags, 
			  /** List<String> allergyTags **/ String description, String ingredients, String method) {
		this.recipeID = recipeID;
		this.name = name;
		this.prepTime = prepTime;
		this.servingSize = servingSize;
		this.tags = tags;
		// this.allergyTags = allergyTags;
		this.description = description;
		this.ingredients = ingredients;
		this.method = method;
	}

	public Recipe() {
	}
	
	public String getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(String recipeID) {
		this.recipeID = recipeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}

	public String getServingSize() {
		return servingSize;
	}

	public void setServingSize(String servingSize) {
		this.servingSize = servingSize;
	}

	public List getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

        /**
	public List<String> getAllergyTags() {
		return allergyTags;
	}

	public void setAllergyTags(List<String> allergyTags) {
		this.allergyTags = allergyTags;
	}
**/
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return name;
        }
	
	
}
