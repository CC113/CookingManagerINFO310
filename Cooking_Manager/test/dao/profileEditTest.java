package dao;

import domain.Profile;
import dao.ProfileDAOv2;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dinjo998
 */
public class profileEditTest {
//	private final ProductDataInterface dao = new ProductDatabase("jdbc:h2:tcp://localhost:9088/project-testing");
	private final ProfileDAOv2 dao = new ProfileDAOv2();
	List<String> playlists = new ArrayList<>();
	private Profile proTwo;
	private Profile proThree;
	private Profile proTwoCopy;
	public profileEditTest() {
	}
	
	@Test
	public void testSave() {
// save the product using DAO
		dao.save(proThree);
// retrieve the same product via DAO
		Profile retrieved = dao.getProfileByID("3");
// ensure that the product we saved is the one we got back
		assertEquals("Retrieved Profile should be the same",
				  proThree, retrieved);
	}
	
	@Test
	public void testEdit() {
		this.proThree.setName("differentName");
		this.proThree.setUsername("differentUsername");
		dao.edit(proThree);
		Profile retrieved = dao.getProfileByID("3");
		assertEquals("Retrieved product should be the same",
				  "name10", retrieved.getName());
	}
	
	@Before
	public void setUp() {
		this.proThree  = new Profile("3", "Pro3", "3pro", "333", true, true, true, true, true, playlists);
		this.proTwo = new Profile ("2", "Pro2", "2pro", "22", true, true, true, true, true, playlists);
		this.proTwoCopy = new Profile ("22", "Pro2", "2pro", true, false, false, false, false, playlists);
				  
// save the products
		dao.save(proThree);
		dao.save(proTwo);
// Note: Intentionally not saving prodThree
	}
	
	@After
	public void tearDown() {
	}
	
}
