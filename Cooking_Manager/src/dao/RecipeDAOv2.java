package dao;

import dao.RecipeDataInterface;
import dao.PlaylistDAOv2;
import domain.Recipe;
import gui.Help;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class RecipeDAOv2 implements RecipeDataInterface {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "org.h2.Driver";
    //static final String DB_URL = "jdbc:h2:tcp://localhost:9092/project;IFEXISTS=TRUE";
    static final String DB_URL = "jdbc:h2:~/test2";
    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    //for recipe ID
    private static final String USERRECIPEIDPREFIX = "EU-";
    private static final String DEVRECIPEIDPREFIX = "DV-";
    private static AtomicInteger USERRECIPEID = new AtomicInteger(000);
    private static AtomicInteger DEVRECIPEID = new AtomicInteger(000);

   public static void main(String[] args) {
		clearDB();
		checkTables();
		createDB();
	}
	
    @Override
    public Collection<Recipe> getRecipes() {
        String sql = "select * from recipes order by recipeid";
        List<Recipe> recipes = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            Class.forName(JDBC_DRIVER);
            ResultSet rs = stmt.executeQuery();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String recipeID = rs.getString("recipeid");
                String name = rs.getString("name");
                String prepTime = rs.getString("prepTime");
                String servingSize = rs.getString("servingSize");
                String gatheredTags = rs.getString("tags");
                /**
                 * List<String> allergyTags = new ArrayList<>();
                 * allergyTags.add(rs.getString ("allergyTags"));*
                 */
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
                Recipe r = new Recipe(recipeID, name, prepTime, servingSize, tags,
                        /**
                         * allergyTags,*
                         */
                        description, ingredients, method);

                // and put it in the collection
                recipes.add(r);
            }

            return recipes;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Recipe> filterByTags(String tag) {
        String sql = "select * from recipes where CHARINDEX((?), tags) > 0";

        // Using a List to preserve the order in which the data was returned from the query.
        List<Recipe> recipesByTags = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            Class.forName(JDBC_DRIVER);
            stmt.setString(1, tag);

            // execute the query
            ResultSet rs = stmt.executeQuery();

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
                /**
                 * List<String> allergyTags = new ArrayList<>();
                 * allergyTags.add(rs.getString ("allergyTags")); *
                 */
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
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        String sql = "merge into recipes (recipeID, name, preptime, servingSize, tags, ingredients, "
                + "description, method) values (?,?,?,?,?,?,?,?)";

        List<Recipe> recipes = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            Class.forName(JDBC_DRIVER);

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
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteRecipe(String recipeID) {
        String sql = "delete from recipes where recipeid = ?";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            Class.forName(JDBC_DRIVER);
            
            stmt.setString(1, recipeID);
            
            stmt.execute();

  
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    
    

    @Override
    public Collection<Recipe> searchRecipes(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        String sql = "select * from recipes "
                + "where CHARINDEX((?), lower(name)) > 0"
                + " or CHARINDEX((?), lower(recipeid)) > 0"
                + " or CHARINDEX((?), lower(description)) > 0"
                + " or CHARINDEX((?), lower(ingredients)) > 0"
                + " or CHARINDEX((?), lower(method)) > 0"
                + " or CHARINDEX((?), lower(servingsize)) > 0"
                + " or CHARINDEX((?), lower(prepTime)) > 0";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            Class.forName(JDBC_DRIVER);

            stmt.setString(1, searchTerm);
            stmt.setString(2, searchTerm);
            stmt.setString(3, searchTerm);
            stmt.setString(4, searchTerm);
            stmt.setString(5, searchTerm);
            stmt.setString(6, searchTerm);
            stmt.setString(7, searchTerm);

            //execute the query
            ResultSet rs = stmt.executeQuery();
				
				List<Recipe> recipeList = new ArrayList<>();

            // query only returns a single result, so use 'if' instead of 'while'
            while (rs.next()) {
                // get the data out of the query
                String recipeID = rs.getString("recipeid");
                String name = rs.getString("name");
                String prepTime = rs.getString("prepTime");
                String servingSize = rs.getString("servingSize");
                String gatheredTags = rs.getString("tags");
                List<String> tags = new ArrayList<>();
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
                /**
                 * List<String> allergyTags = new ArrayList<>();
                 * allergyTags.add(rs.getString ("allergyTags")); *
                 */
                String ingredients = rs.getString("ingredients");
                //ingredients.add(rs.getString ("ingredients"));
                String description = rs.getString("description");
                String method = rs.getString("method");

                // use the data to create and return a recipe object
                recipeList.add(new Recipe(recipeID, name, prepTime, servingSize, tags,
                        /**
                         * allergyTags*
                         */
                        description, ingredients, method));
            }
				return recipeList;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }
    
    

    @Override
    public void lastUserID() {
        String sql = "select * from recipes order by recipeid";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            Class.forName(JDBC_DRIVER);
            ResultSet rs = stmt.executeQuery();

            String lastEndUserID = "None";
            String lastDevUserID = "None";
            String temp;

            while (rs.next()) {
                temp = rs.getString("recipeID");
                if (temp.contains("EU")) {
                    lastEndUserID = temp;
                } else if (temp.contains("DV")) {
                    lastDevUserID = temp;
                }
            }

            if (!(lastEndUserID.equals("None"))) {
                String[] tempArray = lastEndUserID.split("-");
                int lastEntryEUIDNumber = Integer.parseInt(tempArray[1]);

                USERRECIPEID = new AtomicInteger(lastEntryEUIDNumber);
            }

            if (!(lastDevUserID.equals("None"))) {
                String[] tempArray2 = lastDevUserID.split("-");
                int lastEntryDevIDNumber = Integer.parseInt(tempArray2[1]);

                DEVRECIPEID = new AtomicInteger(lastEntryDevIDNumber);
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    //creates the sql tables in the database.
    public static void createDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            Scanner in = new Scanner(new FileReader("<default package>/schema.sql"));
            StringBuilder sb = new StringBuilder();
            while (in.hasNext()) {
                sb.append(in.next() + " ");
            }
            in.close();
            String sql = sb.toString();

            stmt.executeUpdate(sql);
            System.out.println("Created tables in given database...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
        System.out.println("Goodbye!");
    }

    //to check if there are tables in the database
    public static boolean checkTables() {
        Connection conn = null;
        Statement stmt = null;
        Boolean check = true;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            DatabaseMetaData dbm = conn.getMetaData();
            // check if "recipe" table is there
            ResultSet tables = dbm.getTables(null, null, "RECIPES", null);
            if (tables.next()) {
                // Table exists
                System.out.println("Recipes table exists in the database");
            } else {
                // Table does not exist
                System.out.println("Recipes table does not exist in the database");
                check = false;
            }
            // check if "profile" table is there
            tables = dbm.getTables(null, null, "PROFILE", null);
            if (tables.next()) {
                // Table exists
                System.out.println("Profile table exists in the database");
            } else {
                // Table does not exist
                System.out.println("Profile table does not exist in the database");
                check = false;
            }
            // check if "playlists" table is there
            tables = dbm.getTables(null, null, "PLAYLISTS", null);
            if (tables.next()) {
                // Table exists
                System.out.println("Playlists table exists in the database");
            } else {
                // Table does not exist
                System.out.println("Playlists table does not exist in the database");
                check = false;
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
        return check;
    }

    //drops the tables in the database.
    public static void clearDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Deleting tables in given database...");
            stmt = conn.createStatement();
            String sql = "drop table recipes";
            stmt.executeUpdate(sql);
            System.out.println("Recipes Gone");
            sql = "drop table profile";
            stmt.executeUpdate(sql);
            System.out.println("Profile Gone");
            sql = "drop table playlists";
            stmt.executeUpdate(sql);
            System.out.println("Playlists Gone");

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
    }

    //initial set up if tables not created in database
    @Override
    public void initial() {
        if (!checkTables()) {
            try {
                createDB();
                sampleRecipes();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RecipeDAOv2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //closes the connection to the database when the app is closed.
    public void closeDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "select * from recipes";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Recipes are in the Database.");
            }

            // STEP 5: Clean-up environment
            rs.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
//		} finally {
//			// finally block used to close resources
//			try {
//				if (stmt != null) {
//					stmt.close();
//				}
//			} catch (SQLException se2) {
//			} // nothing we can do
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException se) {
//				se.printStackTrace();
//			} // end finally try
//		} // end try
        }
        try {
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecipeDAOv2.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Disconnected from Database");
    }

    public void sampleRecipes() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("<default package>/sampleRecipes"));
        String sb;
        while (scanner.hasNext()) {
            sb = scanner.nextLine();
            String[] splitSb = sb.split(":");
            // get the data out of the query
            String recipeID = null;
            String name = splitSb[0];
            String prepTime = splitSb[1];
            String servingSize = splitSb[2];
            String gatheredTags = splitSb[3];
            List<String> tags = new ArrayList<>();
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
            /**
             * List<String> allergyTags = new ArrayList<>();
             * allergyTags.add(rs.getString ("allergyTags")); *
             */
            String description = splitSb[4];
            String ingredients = splitSb[5];
            String method = splitSb[6];

            saveRecipe(new Recipe(recipeID, name, prepTime, servingSize, tags,
                    /**
                     * allergyTags*
                     */
                    description, ingredients, method));
        }
        scanner.close();

        System.out.println("Successfully populated database with sample data.");
    }

    @Override
    public Recipe suggestSomething(String profileID) {
        PlaylistDAOv2 playlist = new PlaylistDAOv2();
        Random rand = new Random();
        Recipe suggestion = new Recipe();
        List<Recipe> suggestionPool = new ArrayList<>();
        List<String> historyList = new ArrayList<>();

        historyList = (List) playlist.getHistory(profileID);

        // System.out.println(historyList);
        suggestionPool = (List) getRecipes();
        //suggestionPool = (List)getRecipes();
        for (String h : historyList){
            for (Recipe r : suggestionPool){
                if (r.getName().equals(h)){
                    suggestionPool.remove(r);
                    break;
                }
            }
        }
        
//        for (Recipe r : tempList) {
//            //if(r != historyList.get()){
//            //}
//            for (String h : historyList) {
//                if (!r.getRecipeID().equals(h)) {
//                    suggestionPool.add(r);
//                }
//            }
//        }

        Integer randInt = rand.nextInt(suggestionPool.size());
        suggestion = suggestionPool.get(randInt);

        return suggestion;
    }

    @Override
    public Recipe getRecipeByID(String recipeID) {
        String sql = "select * from recipes where recipeid = ?";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            Class.forName(JDBC_DRIVER);
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
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }
}
