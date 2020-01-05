package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import server.DatasetManager;
import server.DatasetManagerFactory;

public class GetDatasetProjectionTest {

	private DatasetManagerFactory factory;
	private DatasetManager manager;
	private String path = "C:/Users/User/Desktop/anaptyksi/project_2672_2820_2900/Resources/NBA_team_stats.csv";
	private ArrayList<String> attributeNames;
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
		manager.registerDataset("NBA", path);
		assertNotNull("manager not null", manager);
		attributeNames = new ArrayList<String>();
		attributeNames.add("W");
		attributeNames.add("PTS");
	}

	@Test
	public final void testGetDatasetProjection() {
		ArrayList<String[]> datasetProjection = manager.getDatasetProjection("NBA", attributeNames);
		
		assertNotNull("projection null", datasetProjection); // TODO
	}

}
