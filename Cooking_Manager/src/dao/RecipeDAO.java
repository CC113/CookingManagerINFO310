package dao;

import domain.Recipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author dinjo998
 */
public class RecipeDAO implements RecipeDataInterface{
    
        private static final String USERRECIPEIDPREFIX = "EU-";
        private static final String DEVRECIPEIDPREFIX = "DV-";
        private static AtomicInteger USERRECIPEID = new AtomicInteger(000);
        private static AtomicInteger DEVRECIPEID = new AtomicInteger(000);
    
	private String url = "jdbc:h2:tcp://localhost:9092/project;IFEXISTS=TRUE";
	
	public RecipeDAO(){
	}
	
	public RecipeDAO(String url){
		this.url = url;
	}
	
	@Override
   public void lastUserID(){
            String sql = "select * from recipes order by recipeid";
            
            try (
                    Connection dbCon = JdbcConnection.getConnection(url);
                    PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                        ResultSet rs = stmt.executeQuery();
                        
                        String lastEndUserID = "None";
                        String lastDevUserID = "None";
                        String temp;
                        
                        while (rs.next()){
                            temp = rs.getString("recipeID");
                            if (temp.contains("EU")){
                                lastEndUserID = temp;
                            } else if (temp.contains("DV")){
                                lastDevUserID = temp;
                            }
                        }
                            
                        if (!(lastEndUserID.equals("None"))){
                            String[] tempArray = lastEndUserID.split("-");
                            int lastEntryEUIDNumber = Integer.parseInt(tempArray[1]);
                            
                            USERRECIPEID = new AtomicInteger(lastEntryEUIDNumber);
                        }
                            
                        if (!(lastDevUserID.equals("None"))){
                            String[] tempArray2 = lastDevUserID.split("-");
                            int lastEntryDevIDNumber = Integer.parseInt(tempArray2[1]);
                            
                            DEVRECIPEID = new AtomicInteger(lastEntryDevIDNumber);
                        }
            }
            catch (SQLException ex) {
		throw new DAOException(ex.getMessage(), ex);
            }
        }   
        
	@Override
	public Collection<Recipe> getRecipes(){
		String sql = "select * from recipes order by recipeid";

		try (
				  // get a connection to the database
				  Connection dbCon = JdbcConnection.getConnection(url);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			// execute the query
			ResultSet rs = stmt.executeQuery();

			// Using a List to preserve the order in which the data was returned from the query.
			List<Recipe> recipes = new ArrayList<>();

			// iterate through the query results
			while (rs.next()) {

				// get the data out of the query
				String recipeID = rs.getString("recipeid");
				String name = rs.getString("name");
				String prepTime = rs.getString("prepTime");
				String servingSize = rs.getString("servingSize");
				String gatheredTags = rs.getString("tags");
				/**List<String> allergyTags = new ArrayList<>();
				allergyTags.add(rs.getString ("allergyTags"));**/
				String ingredients = rs.getString("ingredients");
				String description = rs.getString("description");
				String method = rs.getString("method");
                                
                                List<String> tags = new ArrayList<String>();
                                if (gatheredTags.charAt(0) == '1'){
                                    tags.add("Vegetarian");                                    
                                }
                                if (gatheredTags.charAt(1) == '2'){
                                    tags.add("Vegan");                                    
                                }
                                if (gatheredTags.charAt(2) == '3'){
                                    tags.add("Gluten Free");                                    
                                }
                                if (gatheredTags.charAt(3) == '4'){
                                    tags.add("Dairy Free");                                    
                                }
                                if (gatheredTags.charAt(4) == '5'){
                                    tags.add("Paleo");                                    
                                }
				
				// use the data to create a recipe object
				Recipe r = new Recipe(recipeID, name, prepTime, servingSize, tags,
						  /**allergyTags,**/ description, ingredients, method);

				// and put it in the collection
				recipes.add(r);
			}
			
			return recipes;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}
	
	public Recipe searchRecipesOld(String searchTerm) {
		
    String sql = "select * from recipes "
				+ "where CHARINDEX((?), name) > 0"
				+ " or CHARINDEX((?), recipeid) > 0"
				+ " or CHARINDEX((?), description) > 0"
				+ " or CHARINDEX((?), ingredients) > 0"
				+ " or CHARINDEX((?), method) > 0"
				+ " or CHARINDEX((?), servingsize) > 0"
				+ " or CHARINDEX((?), method) > 0";

    try (
        // get connection to database
        Connection connection = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = connection.prepareStatement(sql);
    ) {
        // set the parameter
        stmt.setString(1, searchTerm);
		  stmt.setString(2, searchTerm);
		  stmt.setString(3, searchTerm);
		  stmt.setString(4, searchTerm);
		  stmt.setString(5, searchTerm);
		  stmt.setString(6, searchTerm);
		  stmt.setString(7, searchTerm);

        // execute the query
        ResultSet rs = stmt.executeQuery();

        // query only returns a single result, so use 'if' instead of 'while'
        if (rs.next()) {
            // get the data out of the query
				String recipeID = rs.getString("recipeid");
				String name = rs.getString("name");
				String prepTime = rs.getString("prepTime");
				String servingSize = rs.getString("servingSize");
            String gatheredTags = rs.getString("tags");
				List<String> tags = new ArrayList<>();
				if (gatheredTags.charAt(0) == '1'){
                                    tags.add("Vegetarian");                                    
                                }
                                if (gatheredTags.charAt(1) == '2'){
                                    tags.add("Vegan");                                    
                                }
                                if (gatheredTags.charAt(2) == '3'){
                                    tags.add("Gluten Free");                                    
                                }
                                if (gatheredTags.charAt(3) == '4'){
                                    tags.add("Dairy Free");                                    
                                }
                                if (gatheredTags.charAt(4) == '5'){
                                    tags.add("Paleo");                                    
                                }
				/** List<String> allergyTags = new ArrayList<>();
				allergyTags.add(rs.getString ("allergyTags")); **/
				String ingredients = rs.getString("ingredients");
				//ingredients.add(rs.getString ("ingredients"));
				String description = rs.getString("description");
				String method = rs.getString("method");
				
				// use the data to create and return a recipe object
				return new Recipe(recipeID, name, prepTime, servingSize, tags,
						  /**allergyTags**/ description, ingredients, method);

        } else {
            // no recipe matches given search terms so return null
            return null;
        }

    } catch (SQLException ex) {  // we are forced to catch SQLException
        // don't let the SQLException leak from our DAO encapsulation
        throw new DAOException(ex.getMessage(), ex);
    }
	}
        
   @Override
   public void saveRecipe(Recipe recipe) {
        String sql="merge into recipes (recipeID, name, preptime, servingSize, tags, ingredients, "
                + "description, method) values (?,?,?,?,?,?,?,?)";

		try (
				  // get connection to database
				  Connection dbCon = JdbcConnection.getConnection(url);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			
			//check if recipeID exists or not (editing recipe vs new recipe)
			if (recipe.getRecipeID() == null) {
				// Generate ID if doesn't exist
				stmt.setString(1, USERRECIPEIDPREFIX + USERRECIPEID.incrementAndGet());
			} else {
				stmt.setString(1, recipe.getRecipeID());
			}
			// copy the data from the recipe domain object into the SQL parameters
			stmt.setString(2, recipe.getName());
			stmt.setString(3, recipe.getPrepTime());
			stmt.setString(4, recipe.getServingSize());
			
			/**
			 * From what I remember, we were storing the the tags as a series of
			 * 0's and 1's, a varchar representation of a boolean list.
			 */
			String stringToBeAdded = "";
			List<String> tags = recipe.getTags();
			if (tags.contains("Vegetarian")) {
				stringToBeAdded += "1";
			} else {
				stringToBeAdded += "0";
			}
			if (tags.contains("Vegan")) {
				stringToBeAdded += "2";
			} else {
				stringToBeAdded += "0";
			}
			if (tags.contains("Gluten Free")) {
				stringToBeAdded += "3";
			} else {
				stringToBeAdded += "0";
			}
			if (tags.contains("Dairy Free")) {
				stringToBeAdded += "4";
			} else {
				stringToBeAdded += "0";
			}
			if (tags.contains("Paleo")) {
				stringToBeAdded += "5";
			} else {
				stringToBeAdded += "0";
			}
			stmt.setString(5, stringToBeAdded);
			stmt.setString(6, recipe.getIngredients());
			stmt.setString(7, recipe.getDescription());
			stmt.setString(8, recipe.getMethod());

			stmt.executeUpdate();  // execute the statement

		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		}
	}
	
	@Override
	public Collection<Recipe> filterByTags(String tag) {
		String sql = "select * from recipes where CHARINDEX((?), tags) > 0";

		try (
				  // get a connection to the database
				  Connection dbCon = JdbcConnection.getConnection(url);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			
			stmt.setString(1, tag);
			// execute the query
			ResultSet rs = stmt.executeQuery();
			

			// Using a List to preserve the order in which the data was returned from the query.
			List<Recipe> recipesByTags = new ArrayList<>();

			// iterate through the query results
			while (rs.next()) {

				// get the data out of the query
				String recipeID = rs.getString("recipeid");
				String name = rs.getString("name");
				String prepTime = rs.getString("prepTime");
				String servingSize = rs.getString("servingSize");
				String gatheredTags = rs.getString("tags");
				List<String> tags = new ArrayList<String>();
				if (gatheredTags.charAt(0) == '1') {
					tags.add("Vegetarian");
				}
				if (gatheredTags.charAt(1) == '2') {
					tags.add("Vegan");
				}
				if (gatheredTags.charAt(2) == '3') {
					tags.add("Gluten Free");
				}
				if (gatheredTags.charAt(3) == '4') {
					tags.add("Dairy Free");
				}
				if (gatheredTags.charAt(4) == '5') {
					tags.add("Paleo");
				}
				/** List<String> allergyTags = new ArrayList<>();
				allergyTags.add(rs.getString ("allergyTags")); **/
				String ingredients = rs.getString("ingredients");
				String description = rs.getString("description");
				String method = rs.getString("method");

				// use the data to create a recipe object
				Recipe r = new Recipe(recipeID, name, prepTime, servingSize, tags,
						  /**
						   * allergyTags,*
						   */
						  description, ingredients, method);

				// and put it in the collection
				recipesByTags.add(r);
			}

			return recipesByTags;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}
        
   public Recipe getRecipeByID(String recipeID){
		String sql = "select * from recipes where recipeid = ?";

		try (
				  // get a connection to the database
				  Connection dbCon = JdbcConnection.getConnection(url);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			// execute the query
			stmt.setString(1, recipeID);
			ResultSet rs = stmt.executeQuery();

			// Using a List to preserve the order in which the data was returned from the query.
			List<Recipe> recipes = new ArrayList<>();
			Recipe r = new Recipe();

			// iterate through the query results
			if (rs.next()) {

				// get the data out of the query
				String name = rs.getString("name");
				String prepTime = rs.getString("prepTime");
				String servingSize = rs.getString("servingSize");
				String gatheredTags = rs.getString("tags");
				String ingredients = rs.getString("ingredients");
				String description = rs.getString("description");
				String method = rs.getString("method");

				List<String> tags = new ArrayList<String>();

				if (gatheredTags.charAt(0) == '1') {
					tags.add("Vegetarian");
				}
				if (gatheredTags.charAt(1) == '2') {
					tags.add("Vegan");
				}
				if (gatheredTags.charAt(2) == '3') {
					tags.add("Gluten Free");
				}
				if (gatheredTags.charAt(3) == '4') {
					tags.add("Dairy Free");
				}
				if (gatheredTags.charAt(4) == '5') {
					tags.add("Paleo");
				}

				// use the data to create a recipe object
				r = new Recipe(recipeID, name, prepTime, servingSize, tags,
						  /**
						   * allergyTags,*
						   */
						  description, ingredients, method);

				// and put it in the collection
			}

			return r;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}


    public Recipe suggestSomething(String profileID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void initial() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void closeDB() {
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
