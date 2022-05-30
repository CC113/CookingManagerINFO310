package dao;

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
import java.util.UUID;

/**
 *
 * @author clach124
 */
public class PlaylistDAOv2 implements PlaylistDataInterface{
	// JDBC driver name and database URL 

	static final String JDBC_DRIVER = "org.h2.Driver";
	//static final String DB_URL = "jdbc:h2:tcp://localhost:9092/project;IFEXISTS=TRUE";  
	static final String DB_URL = "jdbc:h2:~/test2";
	//  Database credentials 
	static final String USER = "sa";
	static final String PASS = "";

	public PlaylistDAOv2() {
	}

	@Override
	public String initialFavouriteSetup(String userID) {
		String sql = "merge into playlists (userID, playlistID, playlistName, contents) values (?,?,?,?)";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);

			stmt.setString(1, userID);
			String playlistID = UUID.randomUUID().toString();
			stmt.setString(2, playlistID);
			stmt.setString(3, "Favourites");
			String[] javaArray = new String[0];
			java.sql.Array sqlArray = conn.createArrayOf("varchar", javaArray);
			stmt.setArray(4, sqlArray);

			stmt.executeUpdate();  // execute the statement

			return playlistID;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String initialHistorySetup(String userID) {
		String sql = "merge into playlists (userID, playlistID, playlistName, contents)"
				  + " values (?,?,?,?)";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, userID);
			String historyID = UUID.randomUUID().toString();
			stmt.setString(2, historyID);
			stmt.setString(3, "History");
			String[] javaArray = new String[0];
			java.sql.Array sqlArray = conn.createArrayOf("varchar", javaArray);
			stmt.setArray(4, sqlArray);

			stmt.executeUpdate();  // execute the statement

			return historyID;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<String> getAllPlaylists(String userID) {
		String sql = "select * from playlists where userID = ?";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, userID);
			ResultSet rs = stmt.executeQuery();

			List<String> playlistNames = new ArrayList<>();

			while (rs.next()) {
				playlistNames.add(rs.getString("playlistname"));
			}
			return playlistNames;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
		return null;
	}
        
        @Override
	public Collection<String> getPlaylistByName(String userID, String playlistName) {
		String sql = "select * from playlists where userID = ? and playlistname = ?";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, userID);
                        stmt.setString(2, playlistName);
			ResultSet rs = stmt.executeQuery();
                        List<String> contents = new ArrayList<>();

			if (rs.next()) {
				Array sqlArray = rs.getArray("contents");
				Object[] javaObjectArray = (Object[]) sqlArray.getArray();
				String[] javaStringArray = Arrays.copyOf(javaObjectArray,
						  javaObjectArray.length, String[].class);
				contents = new ArrayList<String>(Arrays.asList(javaStringArray));
                                return contents;
			}


		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<String> getHistory(String userID) {
		String sql = "select * from playlists where userID = ? and playlistname = 'History'";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, userID);
			ResultSet rs = stmt.executeQuery();

			List<String> historyList = new ArrayList<>();

			if (rs.next()) {
				Array sqlArray = rs.getArray("contents");
				Object[] javaObjectArray = (Object[]) sqlArray.getArray();
				String[] javaStringArray = Arrays.copyOf(javaObjectArray,
						  javaObjectArray.length, String[].class);
				historyList = new ArrayList<String>(Arrays.asList(javaStringArray));
			}
			return historyList;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveToHistory(String userID, List<String> historyList) {
		String sql = "update playlists set contents = (?)"
				  + " where userID = ? and playlistname = ?";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			String[] javaArray = historyList.toArray(new String[historyList.size()]);
			java.sql.Array sqlArray = conn.createArrayOf("varchar", javaArray);
			stmt.setArray(1, sqlArray);
			stmt.setString(2, userID);
			stmt.setString(3, "History");

			stmt.executeUpdate();

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
	}

	@Override
	public Collection<String> getRecipesFromPlaylist(String userID, String playlistName) {
		String sql = "select * from playlists where userID = ? and playlistname = ?";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, userID);
			stmt.setString(2, playlistName);
			ResultSet rs = stmt.executeQuery();
			List<String> playlistIDs = new ArrayList<>();

			/**
			 * This is all for handling sql arrays *
			 */
			if (rs.next()) {
				java.sql.Array sqlArray = rs.getArray("contents");
				Object[] javaObjectArray = (Object[]) sqlArray.getArray();
				String[] javaStringArray = Arrays.copyOf(javaObjectArray,
						  javaObjectArray.length, String[].class);
				playlistIDs = new ArrayList<String>(Arrays.asList(javaStringArray));
			}
			return playlistIDs;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String newPlaylist(String userID, String playlistName) {
//		String sql = "update playlists (userID, playlistID, playlistName, contents)"
//				  + " values (?,?,?,?)";
		String sql = "merge into playlists (userID, playlistID, playlistName, contents)"
				  + " values (?,?,?,?)";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, userID);
			String playlistID = UUID.randomUUID().toString();
			stmt.setString(2, playlistID);
			stmt.setString(3, playlistName);
			String[] javaArray = new String[0];
			java.sql.Array sqlArray = conn.createArrayOf("varchar", javaArray);
			stmt.setArray(4, sqlArray);

			stmt.executeUpdate();  // execute the statement

			return playlistID;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addToPlaylist(String userID, String playlistName, List<String> newContents) {
		String sql = "update playlists set contents = (?)"
				  + " where userID = ? and playlistname = ?";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			String[] javaArray = newContents.toArray(new String[newContents.size()]);
			java.sql.Array sqlArray = conn.createArrayOf("varchar", javaArray);
			stmt.setArray(1, sqlArray);
			stmt.setString(2, userID);
			stmt.setString(3, playlistName);

			stmt.executeUpdate();

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
	}

	@Override
	public void dropPlaylist(String userID, String playlistName) {
		String sql = "delete from playlists"
				  + " where userID = ? and playlistname = ?";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, userID);
			stmt.setString(2, playlistName);

			stmt.executeUpdate();

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
	}

	@Override
	public void removeRecipeFromAllPlaylists(String recipeID) {
		String sql = "select * from playlists";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);

			ResultSet rs = stmt.executeQuery();
			List<String> contents = new ArrayList<>();

			while (rs.next()) {
				String name = rs.getString("playlistname");
				String userID = rs.getString("userid");
				Array sqlArray = rs.getArray("contents");
				Object[] javaObjectArray = (Object[]) sqlArray.getArray();
				String[] javaStringArray = Arrays.copyOf(javaObjectArray,
						  javaObjectArray.length, String[].class);
				contents = new ArrayList<String>(Arrays.asList(javaStringArray));
				if (contents.contains(recipeID)) {
					contents.remove(recipeID);
					addToPlaylist(userID, name, contents);
				}
			}

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
	}
	
	@Override
	public void dropAllPlaylistsByUserID(String userID) {
		String sql = "delete from playlists where userID = ?";

		try (
				  Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				  PreparedStatement stmt = conn.prepareStatement(sql);) {
			Class.forName(JDBC_DRIVER);
			stmt.setString(1, userID);

			stmt.executeUpdate();

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		} catch (Exception e) {
			// Handle errors for Class.forName 
			e.printStackTrace();
		}
	}
        
}
