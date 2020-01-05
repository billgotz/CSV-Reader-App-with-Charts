package server;

public class DatasetManagerFactory {
	
	DatasetManager dManager = new DatasetManager();
	
	public DatasetManagerFactory() {
		;
	}

	/*
	 * TODO FIX THIS!!! Cannot return null!
	 */
	public IDatasetManager create(String className) {
		switch(className) {
			case "DatasetManager": return dManager;
			default: return null;
		}

	}
}
