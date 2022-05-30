package dao;

import domain.Profile;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import password.PasswordHash;

/**
 *
 * @author joyth619
 */
public class ProfileDAOv2 implements ProfileDataInterface {

	// JDBC driver name and database URL 
	static final String JDBC_DRIVER = "org.h2.Driver";
	//static final String DB_URL = "jdbc:h2:tcp://localhost:9092/project;IFEXISTS=TRUE";  
	static final String DB_URL = "jdbc:h2:~/test2";
	//  Database credentials 
	static final String USER = "sa";
	static final String PASS = "";

	// private static AtomicInteger PROFILEID = new AtomicInteger(000);
	public ProfileDAOv2() {
	}

	/**
	 * I think this profile method should no longer be necessary. *
	 */
	@Override
	public String profileExists() {

		String sql = "select * from profile order by name";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			ResultSet rs = stmt.executeQuery();

			String lastProfileName = "None";

			while (rs.next()) {
				lastProfileName = rs.getString("name");
			}
			return lastProfileName;
		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * I feel like this should be used at some point, but currently the system we
	 * have for generating is enough public void lastProfileID(){ String sql =
	 * "select * from recipes order by recipeid";
	 *
	 * try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	 * PreparedStatement stmt = conn.prepareStatement(sql);) { ResultSet rs =
	 * stmt.executeQuery();
	 *
	 * String lastProfileID = "None";
	 *
	 * while (rs.next()){ if (rs.last()){ lastProfileID = rs.getString("userID");
	 * } }
	 *
	 * if (!(lastProfileID.equals("None"))){ int lastEntryEUIDNumber =
	 * Integer.parseInt(lastProfileID); PROFILEID = new
	 * AtomicInteger(lastEntryEUIDNumber); } } catch (SQLException ex) { throw
	 * new DAOException(ex.getMessage(), ex); } catch (Exception e) { // Handle
	 * errors for Class.forName e.printStackTrace(); } } *
	 */
	@Override
	public void save(Profile profile) {
		String sql = "merge into profile (userID, name, gluten_free, dairy_free, vegan, vegetarian, paleo, playlists, username, password) values (?,?,?,?,?,?,?,?,?,?)";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, profile.getUserID());
			stmt.setString(2, profile.getName());
			stmt.setBoolean(3, profile.isGlutenFree());
			stmt.setBoolean(4, profile.isDairyFree());
			stmt.setBoolean(5, profile.isVegan());
			stmt.setBoolean(6, profile.isVegetarian());
			stmt.setBoolean(7, profile.isPaleo());
//                    stmt.setString(8, profile.getFavourites());
//                    List<String> historyDataList = profile.getHistory();
//                    String[] historyArrayData = historyDataList.toArray(new String[historyDataList.size()]);
			/**
			 * Not certain what the string should be in this constructor. It's
			 * meant to be typeName, as in type of the elements. So I'm assuming
			 * it's meant to be varchar, but could be wrong.
			 */
//                    java.sql.Array sqlHistoryArray = conn.createArrayOf("varchar", historyArrayData);
//                    stmt.setArray(9, sqlHistoryArray);
			List<String> playlistsDataList = profile.getPlaylists();
			String[] playlistArrayData = playlistsDataList.toArray(new String[playlistsDataList.size()]);
//                    /** Not certain what the string should be in this constructor.
//                     * It's meant to be typeName, as in type of the elements. So 
//                     * I'm assuming it's meant to be varchar, but could be wrong.
//                     */
			java.sql.Array sqlPlaylistArray = conn.createArrayOf("varchar", playlistArrayData);
			stmt.setArray(8, sqlPlaylistArray);
			stmt.setString(9, profile.getUsername());
			stmt.setString(10, profile.getPassword());

			stmt.executeUpdate();  // execute the statement

		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
	}

	@Override
	public Profile getProfile(String userName) {

		String sql = "select * from profile where username = ?";

		try (
				  // get connection to database
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			// set the parameter
			stmt.setString(1, userName);

			// execute the query
			ResultSet rs = stmt.executeQuery();

			// query only returns a single result, so use 'if' instead of 'while'
			if (rs.next()) {
				// get the data out of the query
				String userID = rs.getString("userID");
				String name = rs.getString("name");
				String username = rs.getString("username");
				Boolean gluten_free = rs.getBoolean("gluten_free");
				Boolean dairy_free = rs.getBoolean("dairy_free");
				Boolean vegan = rs.getBoolean("vegan");
				Boolean vegetarian = rs.getBoolean("vegetarian");
				Boolean paleo = rs.getBoolean("paleo");
				String favourites = rs.getString("favourites");

				List<String> history = new ArrayList<>();
				Array receivedHistory = rs.getArray("history");

				Object[] r2h = (Object[]) receivedHistory.getArray();
				String[] stringr2h = Arrays.copyOf(r2h, r2h.length, String[].class);

				history.addAll(Arrays.asList(stringr2h));

				//need to fuck with playlists later
				List<String> playlists = new ArrayList<>();
				Array receivedPlaylists = rs.getArray("playlists");
				Object[] r2p = (Object[]) receivedHistory.getArray();
				String[] stringr2p = Arrays.copyOf(r2p, r2p.length, String[].class);
				playlists.addAll(Arrays.asList(stringr2p));

				// use the data to create a profile object
				return new Profile(userID, name, username, gluten_free, dairy_free, vegan, vegetarian,
						  paleo, playlists);

			} else {
				// no profile matches given name so return null
				return null;
			}
		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<Profile> getAllProfiles() {

		String sql = "select * from profile";

		try (
				  // get connection to database
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  // create the statement
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			// execute the query
			ResultSet rs = stmt.executeQuery();

			List<Profile> allProfiles = new ArrayList<>();

			// query returns multiple profiles, so need while
			while (rs.next()) {
				// get the data out of the query
				String userID = rs.getString("userID");
				String name = rs.getString("name");
				String username = rs.getString("username");
				Boolean gluten_free = rs.getBoolean("gluten_free");
				Boolean dairy_free = rs.getBoolean("dairy_free");
				Boolean vegan = rs.getBoolean("vegan");
				Boolean vegetarian = rs.getBoolean("vegetarian");
				Boolean paleo = rs.getBoolean("paleo");
//                String favourites = rs.getString("favourites");
//                List<String> history = new ArrayList<>();
//                Array receivedHistory = rs.getArray("history");
//                Object[] r2h = (Object[])receivedHistory.getArray();
//                String[] stringr2h = Arrays.copyOf(r2h, r2h.length, String[].class);
//                history.addAll(Arrays.asList(stringr2h));

				//need to fuck with playlists later
				List<String> playlists = new ArrayList<>();
				Array receivedPlaylists = rs.getArray("playlists");
//                Object[] r2p = (Object[])receivedHistory.getArray();
//                String[] stringr2p = Arrays.copyOf(r2p, r2p.length, String[].class);
//                playlists.addAll(Arrays.asList(stringr2p));

				// use the data to create a profile object
				Profile prof = new Profile(userID, name, username, gluten_free, dairy_free, vegan, vegetarian,
						  paleo, playlists);

				allProfiles.add(prof);
			}
			return allProfiles;

		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ProfileDAOv2.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public Profile getProfileByID(String userID) {
		String sql = "select * from profile where userid = ?";

		try (
				  // get connection to database
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  // create the statement
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			// set the parameter
			stmt.setString(1, userID);

			// execute the query
			ResultSet rs = stmt.executeQuery();

			// query only returns a single result, so use 'if' instead of 'while'
			if (rs.next()) {
				// get the data out of the query
				String name = rs.getString("name");
				String username = rs.getString("username");
				Boolean gluten_free = rs.getBoolean("gluten_free");
				Boolean dairy_free = rs.getBoolean("dairy_free");
				Boolean vegan = rs.getBoolean("vegan");
				Boolean vegetarian = rs.getBoolean("vegetarian");
				Boolean paleo = rs.getBoolean("paleo");

				//need to fuck with playlists later
				List<String> playlists = new ArrayList<>();
				Array receivedPlaylists = rs.getArray("playlists");
				Object[] r2p = (Object[]) receivedPlaylists.getArray();
				String[] stringr2p = Arrays.copyOf(r2p, r2p.length, String[].class);
				playlists.addAll(Arrays.asList(stringr2p));

				// use the data to create a profile object
				return new Profile(userID, name, username, gluten_free, dairy_free, vegan, vegetarian,
						  paleo, playlists);

			} else {
				// no profile matches given name so return null
				return null;
			}
		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ProfileDAOv2.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public void updateAProfilesPlaylists(Profile profile) {
		String sql = "merge into profile (userID, name, gluten_free, dairy_free, vegan, vegetarian, paleo, playlists) values (?,?,?,?,?,?,?,?)";

		try (
				  // get connection to database
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  // create the statement
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			// get the data out of the query
			stmt.setString(1, profile.getUserID());
			stmt.setString(2, profile.getName());
			stmt.setBoolean(3, profile.isGlutenFree());
			stmt.setBoolean(4, profile.isDairyFree());
			stmt.setBoolean(5, profile.isVegan());
			stmt.setBoolean(6, profile.isVegetarian());
			stmt.setBoolean(7, profile.isPaleo());

			List<String> playlistsDataList = profile.getPlaylists();
			String[] playlistArrayData = playlistsDataList.toArray(new String[playlistsDataList.size()]);
			/**
			 * Not certain what the string should be in this constructor. It's
			 * meant to be typeName, as in type of the elements. So I'm assuming
			 * it's meant to be varchar, but could be wrong.
			 */
			java.sql.Array sqlPlaylistArray = conn.createArrayOf("varchar", playlistArrayData);
			stmt.setArray(8, sqlPlaylistArray);

			stmt.executeUpdate();
		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ProfileDAOv2.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
		@Override
		public void changePassword(Profile profile) {
		String sql = "UPDATE profile SET password = ? WHERE userID = ?";

		try (
				  // get connection to database
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  // create the statement
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			// set the parameter
			// get the data out of the query
			stmt.setString(1, profile.getPassword());
			stmt.setString(2, profile.getUserID());
			stmt.executeUpdate();
		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		}
	}
	
	@Override
	public void edit(Profile profile) {

		//String sql = "update profile set name=?, gluten_free = ?, dairy_free = ?, vegan = ?, vegetarian = ?, paleo = ?, where userID = ?";
		String sql = "merge into profile (userid, name, gluten_free, dairy_free, vegan, vegetarian, paleo, username, password) values (?,?,?,?,?,?,?,?,?)";
		
		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, profile.getUserID());
			stmt.setString(2, profile.getName());
			stmt.setBoolean(3, profile.isGlutenFree());
			stmt.setBoolean (4, profile.isDairyFree());
			stmt.setBoolean(5, profile.isVegan());
			stmt.setBoolean(6, profile.isVegetarian());
			stmt.setBoolean(7, profile.isPaleo());
			stmt.setString(8, profile.getUsername());
			stmt.setString(9, profile.getPassword());
			stmt.executeUpdate();  // execute the statement

		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
	}
	
	public boolean signIn(String username, String password) {
		String sql = "select * from profile where username = ?";
		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			String passwordInDB = "";

			while (rs.next()) {
				passwordInDB = rs.getString("password");
			}
			return PasswordHash.checkPassword(password, passwordInDB);
		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
		return false;
	}
	
@Override
	public Profile getProfileByUsername(String username) {
		String sql = "select * from profile where username = ?";

		try (
				  // get connection to database
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  // create the statement
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			// set the parameter
			stmt.setString(1, username);

			// execute the query
			ResultSet rs = stmt.executeQuery();

			// query only returns a single result, so use 'if' instead of 'while'
			if (rs.next()) {
				// get the data out of the query
				String userID = rs.getString("userID");
				String name = rs.getString("name");
				Boolean gluten_free = rs.getBoolean("gluten_free");
				Boolean dairy_free = rs.getBoolean("dairy_free");
				Boolean vegan = rs.getBoolean("vegan");
				Boolean vegetarian = rs.getBoolean("vegetarian");
				Boolean paleo = rs.getBoolean("paleo");

				//need to fuck with playlists later
				List<String> playlists = new ArrayList<>();
				Array receivedPlaylists = rs.getArray("playlists");
				Object[] r2p = (Object[]) receivedPlaylists.getArray();
				String[] stringr2p = Arrays.copyOf(r2p, r2p.length, String[].class);
				playlists.addAll(Arrays.asList(stringr2p));

				// use the data to create a profile object
				return new Profile(userID, name, username, gluten_free, dairy_free, vegan, vegetarian,
						  paleo, playlists);

			} else {
				// no profile matches given name so return null
				return null;
			}
		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ProfileDAOv2.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public void deleteProfile(String userID) {
        String sql = "delete from profile where userID = ?";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            Class.forName(JDBC_DRIVER);
            
            stmt.setString(1, userID);
            
            stmt.execute();

  
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }
}
