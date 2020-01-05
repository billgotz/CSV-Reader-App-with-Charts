package server;

import java.util.ArrayList;

import utils.SimpleCSVReader;

public class DatasetManager implements IDatasetManager {
	
	
	public ArrayList<Dataset> dataset = new ArrayList<Dataset>();
	SimpleCSVReader reader = new SimpleCSVReader();

	@Override
	public int registerDataset(String datasetName, String canonicalPath) {
		if(datasetName == null) {
			System.out.println(-1);
			return -1;
		}
		for(Dataset d : dataset) {
			if (d.getName().equals(datasetName)) {
				System.out.println(-10);
				return -10;
			}
		}
		Dataset d = new Dataset(datasetName, reader.load(canonicalPath));
		dataset.add(d);
		return 0;
	}
	

	@Override
	public String[] retrieveDataset(String datasetName, ArrayList<String[]> data) {
		for(Dataset d : dataset) {
			if (d.getName().equals(datasetName)) {
				for(String[] s : d.getData()) {
					data.add(s);
				}
				return data.remove(0);
			}
		}
		return null;
	}

	@Override
	public int filterDataset(String originalDatasetName, String newDatasetName, String filterColumnName, String filterValue) {
				
		ArrayList<String[]> originalData = new ArrayList<String[]>();
		ArrayList<String[]> newData = new ArrayList<String[]>();
		
		String[] header = retrieveDataset(originalDatasetName, originalData);
		
		if(header == null) {
			System.out.println(-1);
			return -1;
		}
		
		newData.add(header);
		boolean found = false;
		for(int i =0 ; i<=header.length -1; i++) {
			if (header[i].equals(filterColumnName)) {
				for(String[] s : originalData) {
					found = true;
					if(s[i].equals(filterValue) ) {
						newData.add(s);
					}
				}
			}
		}
		if (found == false) {
			System.out.println(-2);
			return -2;
		}
		Dataset d = new Dataset(newDatasetName, newData);
		dataset.add(d);
		return 0;

	}


	@Override
	public ArrayList<String[]> getDatasetProjection(String datasetName, ArrayList<String> attributeNames) {
		ArrayList<String[]> dataToReturn = new ArrayList<String[]>();
		int xPos = 0;
		int yPos = 0;
		
		ArrayList<String[]> originalData = new ArrayList<String[]>();
		
		String[] header = retrieveDataset(datasetName, originalData);
		
		if(header == null) {
			return null;
		}
		
		boolean foundX = false;
		boolean foundY = false;
		
		for(int i=0; i <header.length; i++) {
			if(header[i].equals(attributeNames.get(0))) {
				xPos = i;
				foundX = true;
			} 
			if(header[i].equals(attributeNames.get(1))) {
				yPos = i;
				foundY = true;
			}
		}
		
		if (foundX == false || foundY == false) {
			return null;
		}
		
		for (String[] s : originalData) {
			String[] XandYaxis = new String[2];
			XandYaxis[0] = s[xPos];
			XandYaxis[1] = s[yPos];
			dataToReturn.add(XandYaxis);
		}
		
		return dataToReturn;
	}

}
