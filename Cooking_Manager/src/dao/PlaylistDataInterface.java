package dao;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author dinjo998
 */
public interface PlaylistDataInterface {
    
    String initialFavouriteSetup(String userID);
    
    String initialHistorySetup(String userID);
	 
	 Collection<String> getAllPlaylists(String userID);
	 
	 Collection<String> getHistory(String userID);
	 
	 void saveToHistory(String userID, List<String> historyList);
	 
	 Collection<String> getRecipesFromPlaylist(String userID, String playlistName);
	 
	 String newPlaylist(String userID, String playlistName);
	 
	 void addToPlaylist(String userID, String playlistName, List<String> newContents);
         
         Collection<String> getPlaylistByName(String userID, String playlistName);
         
         void dropPlaylist(String userID, String playlistName);
         
         void removeRecipeFromAllPlaylists(String recipeID);
			
			void dropAllPlaylistsByUserID(String userID);
	 
	 
	
}
