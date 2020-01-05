/**
 * 
 */
package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import server.DatasetManager;
import server.DatasetManagerFactory;

/**
 * @author User
 *
 */
public class RegisterDatasetTest {

	private DatasetManagerFactory factory;
	private DatasetManager manager;
	private String path = "C:/Users/User/Desktop/anaptyksi/project_2672_2820_2900/Resources/NBA_team_stats.csv";
	/**
	 * @throws java.lang.Exception
	 */
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		factory = new DatasetManagerFactory();
		manager = (DatasetManager)factory.create("DatasetManager");
		assertNotNull("manager not null", manager);
	}
	
	
	@Test
	public final void testRegisterDataset() {
		
		assertEquals(0,manager.registerDataset("NBA", path));
	
	}
}
