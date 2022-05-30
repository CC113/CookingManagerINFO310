package domain;

/**
 *
 * @author dinjo998
 */
public class PlaylistHeader {
	private String playlistID;
	private String name;

	public PlaylistHeader() {
	}

	public PlaylistHeader(String playlistID, String name) {
		this.playlistID = playlistID;
		this.name = name;
	}

	public String getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(String playlistID) {
		this.playlistID = playlistID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PlaylistHeader{" + "playlistID=" + playlistID + ", name=" + name + '}';
	}
	
	
}
