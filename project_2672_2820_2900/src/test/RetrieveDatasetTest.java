package test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import server.DatasetManager;
import server.DatasetManagerFactory;

public class RetrieveDatasetTest {
	
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
	public final void testRetrieveDataset() {
		ArrayList<String[]> data = new ArrayList<String[]>();
		String[] header = manager.retrieveDataset("NBA", data);
		assertNotNull("Header is null", header);
	}

}
