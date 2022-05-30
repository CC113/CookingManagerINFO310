package domain;

import java.sql.Array;
import java.util.List;

/**
 *
 * @author dobbe287 Creating Profile domain class
 */
public class Profile {

	private String userID; //need to create condition to prevent duplications, primary key	
	private String name; //
	private boolean gluten_free, dairy_free, vegan, vegetarian, paleo;
	private List<String> playlists;
	private String username;
	private String password;

	public Profile() {
	}
	//constructor

	public Profile(String userID, String name, String username, boolean gluten_free, boolean dairy_free, boolean vegan, boolean vegetarian, boolean paleo, List playlists) {
		this.name = name;
		this.userID = userID;
		this.username = username;
		this.gluten_free = gluten_free;
		this.dairy_free = dairy_free;
		this.vegan = vegan;
		this.vegetarian = vegetarian;
		this.paleo = paleo;
		this.playlists = playlists;
	}

	public Profile(String userID, String name, String username, String password, boolean gluten_free, boolean dairy_free, boolean vegan, boolean vegetarian, boolean paleo, List playlists) {
		this.name = name;
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.gluten_free = gluten_free;
		this.dairy_free = dairy_free;
		this.vegan = vegan;
		this.vegetarian = vegetarian;
		this.paleo = paleo;
		this.playlists = playlists;
	}

	//set methods
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setDairyFree(boolean dairy_free) {
		this.dairy_free = dairy_free;
	}

	public void setGlutenFree(boolean gluten_free) {
		this.gluten_free = gluten_free;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPaleo(boolean paleo) {
		this.paleo = paleo;
	}

	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}

	public void setPlaylists(List playlists) {
		this.playlists = playlists;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public void addToPlaylist(String itemToAdd) {
		this.playlists.add(itemToAdd);
	}
	//end of set methods

	//get methods
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUserID() {
		return userID;
	}

	public String getName() {
		return name;
	}

	public List<String> getPlaylists() {
		return playlists;
	}

	public boolean isDairyFree() {
		return dairy_free;
	}

	public boolean isGlutenFree() {
		return gluten_free;
	}

	public boolean isPaleo() {
		return paleo;
	}

	public boolean isVegan() {
		return vegan;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}
	//end of get methods

	@Override
	public String toString() {
		return "Name: " + name;
	}

}
