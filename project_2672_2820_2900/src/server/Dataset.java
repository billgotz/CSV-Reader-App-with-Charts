package server;

import java.util.ArrayList;

public class Dataset {

	private String name;
	private ArrayList<String[]> data;
	
	public Dataset(String name, ArrayList<String[]> data) {
		this.name = name;
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String[]> getData() {
		return data;
	}
	
	public void setData(ArrayList<String[]> data) {
		this.data = data;
	}
	
}
