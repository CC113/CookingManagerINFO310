package dao;

import java.sql.Array;
import java.sql.Connection;
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
public class PlaylistDAO implements PlaylistDataInterface{
    private String url = "jdbc:h2:tcp://localhost:9088/project;IFEXISTS=TRUE";
    // private static AtomicInteger PROFILEID = new AtomicInteger(000);

    public PlaylistDAO(){
    }

    public PlaylistDAO(String url){
            this.url = url;
    }

	 @Override
    public String initialFavouriteSetup(String userID){
        String sql="merge into playlists (userID, playlistID, playlistName, contents) values (?,?,?,?)";

        try (
            // get connection to database
            Connection dbCon = JdbcConnection.getConnection(url);
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                // copy the data from the student domain object into the SQL parameters
                stmt.setString(1, userID);
                String playlistID = UUID.randomUUID().toString();
                stmt.setString(2, playlistID);
                stmt.setString(3, "Favourites");
                String[] javaArray = new String[0];
                java.sql.Array sqlArray = dbCon.createArrayOf("varchar", javaArray);
                stmt.setArray(4,sqlArray);

                stmt.executeUpdate();  // execute the statement

                return playlistID;

            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
    }
    
	 @Override
    public String initialHistorySetup(String userID){
        String sql="merge into playlists (userID, playlistID, playlistName, contents)"
                + " values (?,?,?,?)";

        try (
            // get connection to database
            Connection dbCon = JdbcConnection.getConnection(url);
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                // copy the data from the student domain object into the SQL parameters
                stmt.setString(1, userID);
                String historyID = UUID.randomUUID().toString();
                stmt.setString(2, historyID);
                stmt.setString(3, "History");
                String[] javaArray = new String[0];
                java.sql.Array sqlArray = dbCon.createArrayOf("varchar", javaArray);
                stmt.setArray(4,sqlArray);

                stmt.executeUpdate();  // execute the statement

                return historyID;

            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
    }
    
	 @Override
    public Collection<String> getAllPlaylists(String userID){
         String sql="select * from playlists where userID = ?";

        try (
            // get connection to database
            Connection dbCon = JdbcConnection.getConnection(url);
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                // copy the data from the student domain object into the SQL parameters
                stmt.setString(1, userID);
                ResultSet rs = stmt.executeQuery();
                
                List<String> playlistNames = new ArrayList<>();
                
                while(rs.next()){
                    playlistNames.add(rs.getString("playlistname"));
                }
                return playlistNames;

            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
    }
    
	 @Override
    public Collection<String> getHistory(String userID){
         String sql= "select * from playlists where userID = ? and playlistname = 'History'";

        try (
            // get connection to database
            Connection dbCon = JdbcConnection.getConnection(url);
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                // copy the data from the student domain object into the SQL parameters
                stmt.setString(1, userID);
                ResultSet rs = stmt.executeQuery();
                
                List<String> historyList = new ArrayList<>();
                
                if(rs.next()){
                    Array sqlArray = rs.getArray("contents");
                    Object[] javaObjectArray = (Object[])sqlArray.getArray();
                    String[] javaStringArray = Arrays.copyOf(javaObjectArray,
                            javaObjectArray.length, String[].class);
                    historyList = new ArrayList<String>(Arrays.asList(javaStringArray));
                }
                return historyList;

            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
    }
    
	 @Override
    public void saveToHistory(String userID, List<String> historyList){
         String sql= "update playlists set contents = (?)"
                 + " where userID = ? and playlistname = ?";

        try (
            // get connection to database
            Connection dbCon = JdbcConnection.getConnection(url);
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // copy the data from the student domain object into the SQL parameters

            String[] javaArray = historyList.toArray(new String[historyList.size()]);
            java.sql.Array sqlArray = dbCon.createArrayOf("varchar", javaArray);
            stmt.setArray(1, sqlArray);
            stmt.setString(2, userID);
            stmt.setString(3, "History");

            stmt.executeUpdate();


            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
    }
    
	 @Override
    public Collection<String> getRecipesFromPlaylist(String userID, String playlistName){
         String sql="select * from playlists where userID = ? and playlistname = ?";

        try (
            // get connection to database
            Connection dbCon = JdbcConnection.getConnection(url);
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                // copy the data from the student domain object into the SQL parameters
                stmt.setString(1, userID);
                stmt.setString(2, playlistName);
                ResultSet rs = stmt.executeQuery();
                List<String> playlistIDs = new ArrayList<>();
                
                /** This is all for handling sql arrays **/
                if(rs.next()){
                    java.sql.Array sqlArray = rs.getArray("contents");
                    Object[] javaObjectArray = (Object[])sqlArray.getArray();
                    String[] javaStringArray = Arrays.copyOf(javaObjectArray,
                            javaObjectArray.length, String[].class);
                    playlistIDs = new ArrayList<String>(Arrays.asList(javaStringArray));
                }
                return playlistIDs;

            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
    }
    
	 @Override
    public String newPlaylist(String userID, String playlistName){
        String sql="update into playlists (userID, playlistID, playlistName, contents)"
                + " values (?,?,?,?)";

        try (
            // get connection to database
            Connection dbCon = JdbcConnection.getConnection(url);
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                // copy the data from the student domain object into the SQL parameters
                stmt.setString(1, userID);
                String playlistID = UUID.randomUUID().toString();
                stmt.setString(2, playlistID);
                stmt.setString(3, playlistName);
                String[] javaArray = new String[0];
                java.sql.Array sqlArray = dbCon.createArrayOf("varchar", javaArray);
                stmt.setArray(4,sqlArray);

                stmt.executeUpdate();  // execute the statement
                
                return playlistID;

            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
    }
    
	 @Override
    public void addToPlaylist(String userID, String playlistName, List<String> newContents){
        String sql= "update playlists set contents = (?)"
                 + " where userID = ? and playlistname = ?";

        try (
            // get connection to database
            Connection dbCon = JdbcConnection.getConnection(url);
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // copy the data from the student domain object into the SQL parameters

            String[] javaArray = newContents.toArray(new String[newContents.size()]);
            java.sql.Array sqlArray = dbCon.createArrayOf("varchar", javaArray);
            stmt.setArray(1, sqlArray);
            stmt.setString(2, userID);
            stmt.setString(3, playlistName);

            stmt.executeUpdate();


            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
    }

    @Override
    public Collection<String> getPlaylistByName(String userID, String playlistName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dropPlaylist(String userID, String playlistName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeRecipeFromAllPlaylists(String recipeID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void dropAllPlaylistsByUserID(String userID) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
    
}




