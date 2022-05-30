package dao;

import domain.Profile;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author joyth619
 */
public class ProfileDAO implements ProfileDataInterface{
	private String url = "jdbc:h2:tcp://localhost:9092/project;IFEXISTS=TRUE";
        // private static AtomicInteger PROFILEID = new AtomicInteger(000);
	
	public ProfileDAO(){
	}
	
	public ProfileDAO(String url){
		this.url = url;
	}
        
        /** I think this profile method should no longer be necessary.
	 * @return  **/
	@Override
        public String profileExists(){
            
            String sql = "select * from profile order by name";
            
            try (
                    Connection dbCon = JdbcConnection.getConnection(url);
                    PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                        ResultSet rs = stmt.executeQuery();
                        
                        String lastProfileName = "None";
                        
                        while (rs.next()){
                                lastProfileName = rs.getString("name");
                        }
                        return lastProfileName;
            }
            catch (SQLException ex) {
		throw new DAOException(ex.getMessage(), ex);
            }
        } 
        
        /** I feel like this should be used at some point, but currently the
         * system we have for generating is enough
        public void lastProfileID(){
            String sql = "select * from recipes order by recipeid";
            
            try (
                    Connection dbCon = JdbcConnection.getConnection(url);
                    PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                        ResultSet rs = stmt.executeQuery();
                        
                        String lastProfileID = "None";
                        
                        while (rs.next()){
                            if (rs.last()){
                                lastProfileID = rs.getString("userID");
                            }
                        }
                            
                        if (!(lastProfileID.equals("None"))){
                            int lastEntryEUIDNumber = Integer.parseInt(lastProfileID);
                            PROFILEID = new AtomicInteger(lastEntryEUIDNumber);
                        }
            }
            catch (SQLException ex) {
		throw new DAOException(ex.getMessage(), ex);
            }
        } **/
        

	@Override
	public void save(Profile profile) {
            String sql="merge into profile (userID, name, gluten_free, dairy_free, vegan, vegetarian, paleo, playlists) values (?,?,?,?,?,?,?,?)";
	 

            try (
                // get connection to database
                Connection dbCon = JdbcConnection.getConnection(url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
                    // copy the data from the student domain object into the SQL parameters
                    stmt.setString(1, profile.getUserID());
                    stmt.setString(2, profile.getName());
                    stmt.setBoolean(3, profile.isGlutenFree());
                    stmt.setBoolean(4, profile.isDairyFree());
                    stmt.setBoolean(5, profile.isVegan());
                    stmt.setBoolean(6, profile.isVegetarian());
                    stmt.setBoolean(7, profile.isPaleo());
                    
                    List<String> playlistsDataList = profile.getPlaylists();
                    String[] playlistArrayData = playlistsDataList.toArray(new String[playlistsDataList.size()]);
                    /** Not certain what the string should be in this constructor.
                     * It's meant to be typeName, as in type of the elements. So 
                     * I'm assuming it's meant to be varchar, but could be wrong.
                     */
                    java.sql.Array sqlPlaylistArray = dbCon.createArrayOf("varchar", playlistArrayData);
                    stmt.setArray(8, sqlPlaylistArray);

                    stmt.executeUpdate();  // execute the statement

                } catch (SQLException ex) {  // we are forced to catch SQLException
                    // don't let the SQLException leak from our DAO encapsulation
                    throw new DAOException(ex.getMessage(), ex);
            }
    }

	@Override
    public Profile getProfile(String profileName) {
			
    String sql = "select * from profile where name = ?";

    try (
        // get connection to database
        Connection connection = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            // set the parameter
            stmt.setString(1, profileName);

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

                //need to fuck with playlists later
                List<String> playlists = new ArrayList<>();
                Array receivedPlaylists = rs.getArray("playlists");
                Object[] r2p = (Object[])receivedPlaylists.getArray();
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
        }
    }
       
	@Override
    public Collection<Profile> getAllProfiles() {
			
    String sql = "select * from profile";

    try (
        // get connection to database
        Connection connection = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
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

                //need to fuck with playlists later
                List<String> playlists = new ArrayList<>();
                Array receivedPlaylists = rs.getArray("playlists");
                Object[] r2p = (Object[])receivedPlaylists.getArray();
                String[] stringr2p = Arrays.copyOf(r2p, r2p.length, String[].class);
                playlists.addAll(Arrays.asList(stringr2p));


                // use the data to create a profile object
                Profile prof = new Profile(userID, name, username, gluten_free, dairy_free, vegan, vegetarian, 
                                  paleo, playlists);
                
                allProfiles.add(prof);
            }
            return allProfiles;

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
	@Override
    public Profile getProfileByID(String userID) {
			
    String sql = "select * from profile where userid = ?";

    try (
        // get connection to database
        Connection connection = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
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
                Object[] r2p = (Object[])receivedPlaylists.getArray();
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
        }
    }
    
    public void updateAProfilesPlaylists(Profile profile) {
			
    String sql = "merge into profile (userID, name, gluten_free, dairy_free, vegan, vegetarian, paleo, playlists) values (?,?,?,?,?,?,?,?)";

    try (
        // get connection to database
        Connection connection = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            // set the parameter
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
                /** Not certain what the string should be in this constructor.
                 * It's meant to be typeName, as in type of the elements. So 
                 * I'm assuming it's meant to be varchar, but could be wrong.
                 */
                java.sql.Array sqlPlaylistArray = connection.createArrayOf("varchar", playlistArrayData);
                stmt.setArray(8, sqlPlaylistArray);
                
                stmt.executeUpdate();

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

	@Override
	public boolean signIn(String username, String password) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Profile getProfileByUsername(String username) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void edit(Profile profile) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

    @Override
    public void changePassword(Profile profile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void deleteProfile(String username) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
