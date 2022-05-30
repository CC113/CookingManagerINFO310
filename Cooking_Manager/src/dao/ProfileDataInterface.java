package dao;

import domain.Profile;
import domain.Recipe;
import java.util.Collection;

/**
 *
 * @author dinjo998
 */
public interface ProfileDataInterface {
    
    String profileExists();
    
    void save(Profile profile);
    
    Profile getProfile(String profileName);
    
    Collection<Profile> getAllProfiles();
	 
	 Profile getProfileByID(String userID);
	 
	 void updateAProfilesPlaylists(Profile profile);
	 
	 boolean signIn(String username, String password);
	 
	 Profile getProfileByUsername(String username);
	
	 void edit(Profile profile);
	 
	 void changePassword(Profile profile);

	public void deleteProfile(String username);
}
