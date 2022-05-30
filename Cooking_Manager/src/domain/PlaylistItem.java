package domain;

/**
 *
 * @author dinjo998
 */
public class PlaylistItem {
	private String recipeID;
	private String playlistID;

	public PlaylistItem() {
	}

	public PlaylistItem(String recipeID, String playlistID) {
		this.recipeID = recipeID;
		this.playlistID = playlistID;
	}
	
	public String getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(String recipeID) {
		this.recipeID = recipeID;
	}

	public String getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(String playlistID) {
		this.playlistID = playlistID;
	}

	@Override
	public String toString() {
		return "PlaylistItem{" + "recipeID=" + recipeID + ", playlistID=" + playlistID + '}';
	}
}
