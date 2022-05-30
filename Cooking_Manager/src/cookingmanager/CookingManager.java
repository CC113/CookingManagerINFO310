package cookingmanager;

import dao.PlaylistDAOv2;
import dao.PlaylistDataInterface;
import dao.ProfileDAOv2;
import dao.ProfileDataInterface;
import dao.RecipeDAOv2;
import dao.RecipeDataInterface;
import gui.MainMenu;

/**
 *
 * @author joyth619
 */
public class CookingManager {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		RecipeDataInterface recdao;
		ProfileDataInterface prodao;
		PlaylistDataInterface playdao;
		prodao = new ProfileDAOv2();
		recdao = new RecipeDAOv2();
		recdao.initial();
		recdao.lastUserID();
		playdao = new PlaylistDAOv2();
		
		MainMenu menu = new MainMenu(recdao, prodao, playdao);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}
	
}
