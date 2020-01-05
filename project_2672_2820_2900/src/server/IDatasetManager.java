package server;

import java.util.ArrayList;

/**
 * The main interface, via which the client software communicates with the server
 * ALL the data-retrieval work needs to pass from calls of IDatasetManager
 *   
 * @author pvassil
 *
 */
public interface IDatasetManager {

	/**
	 * Requires that the first thing to do is to register a dataset with its name and (optionally) path
	 * 
	 * If the dataset name is null, or already exists returns negative value
	 * @param datasetName A name given to represent the dataset
	 * @param canonicalPath the canonical path of the dataset
	 * @return 0 if all OK, -1 if there is no name, -10 if the name already exists
	 */
	public int registerDataset(String datasetName, String canonicalPath);

	
	/**
	 * This is a method that retrieves a specified dataset via its name (which is passed as parameter), and:
	 * (a) it feeds the input parameter data with the contents of the registered data set, and,
	 * (b) returns the header of the data set as an array of strings
	 * 
	 * @param datasetName A String with the name of the registered dataset that we work with 
	 * @param data   An ArrayList<String []> with the data of the dataset that we pass as a parameter 
	 * @return    An Array of Strings with the names of the attributes of the columns of the dataset, or null if the dataset does not exist
	 */
	public String [] retrieveDataset(String datasetName, ArrayList<String []> data);

	
	/**
	 * Creates a new dataset over an existing one, by applying a selection of the rows containing the value filterValue in column with name filterColumnName.
	 * 
	 * The new data set is registered too.
	 * 
	 * @param originalDatasetName A String with the name of the original data set
	 * @param newDatasetName   A String with the name of the new data set
	 * @param filterColumnName A String with the name of the column of the original data set that is used for the filtering
	 * @param filterValue A String with the value that is applied to filterColumnName 
	 * @return 0 if all OK, -1 if sth goes wrong
	 */
	public int filterDataset(String originalDatasetName,String newDatasetName, String filterColumnName, String filterValue);

	
	/**
	 * A method to receive only a subset of the columns -i.e., a projection- of the designated data set
	 *  
	 * @param datasetName A String with the name of the registered dataset that we work with
	 * @param attributeNames An ArrayList of Strings with the names of the attributes over which the dataset is projected
	 * @return null if sth goes wrong, a populated ArrayList of String [], where each row is a member of the list and each of its attributes populates the respective cell of the String array
	 */
	public ArrayList<String[]> getDatasetProjection(String datasetName, ArrayList<String> attributeNames);
	
}//end i-face