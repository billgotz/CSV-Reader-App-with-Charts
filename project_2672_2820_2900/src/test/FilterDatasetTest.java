package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import server.DatasetManager;
import server.DatasetManagerFactory;

public class FilterDatasetTest {

	private DatasetManagerFactory factory;
	private DatasetManager manager;
	private String path = "C:/Users/User/Desktop/anaptyksi/project_2672_2820_2900/Resources/NBA_team_stats.csv";
	
	@Before
	public void setUp() throws Exception {
		factory = new DatasetManagerFactory();
		manager = (DatasetManager)factory.create("DatasetManager");
		manager.registerDataset("NBA", path);
		assertNotNull("manager not null", manager);

	}

	@Test
	public final void testFilterDataset() {
		assertEquals(0,manager.filterDataset("NBA", "NBA2", "Team", "Boston Celtics")); 
	}

}
