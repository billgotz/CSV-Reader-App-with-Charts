package client.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import javafx.scene.control.ButtonType;

import server.IDatasetManager;
import server.DatasetManagerFactory;
import utils.SimpleCSVReader;

/**
 * This is the class that responds to the calls made by the parts of the GUI for MainApp.fxml
 * 
 * For example: the exit menu item (in the fxml file, this is the menuItem "exitMI") is related to the "#handleExit" action.
 * This means that whenever the exit menu item is pressed, then the handleEXit() method will be executed.
 * 
 * You also need to set:
 *   (1)     fx:controller="client.controllers.MainAppController" 
 *       at the top container of the fxml file, such that the fxml knows that when launched it will hook to the "client.controllers.MainAppController" (i.e., this one) class
 *       (otherwise, the fxml does not know which Java class provides the code for the prescribed methods -- note: you also give the package name before the class name)
 *   (2) the annotation of the respective methods that handle the calls with @FXML
 *    
 * @author pvassil
 *
 */
public class MainAppController {

	/**
	 * Default constructor for the class
	 */
	public MainAppController() {
		DatasetManagerFactory factory = new DatasetManagerFactory();
		datasetManager = factory.create("DatasetManager");

		if (datasetManager == null) {
			System.out.println("Cannot initiate a dataset manager. Exiting!");
			System.exit(-1);
		}
	}//end constructor

	/**
	 * Exits the Platform and the System
	 */
	@FXML
	private void handleExit() {
		Platform.exit();
		System.exit(0);
	}//end handleExit

	/**
	 * A simple info alert is shown to the user
	 */
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("A simple Stats Application");
		alert.setContentText("For the course SW Development in Univ. Ioannina.");
		alert.showAndWait();
		//    	//In case you wanna do sth after the button is pressed...
		//    	alert.showAndWait().ifPresent(rs -> {
		//    	    if (rs == ButtonType.OK) {
		//    	        System.out.println("Pressed OK.");
		//    	    }
		//    	});
	}//end handleAbout

	/**
	 * Facilitates the registration of the data set to the server.
	 * 
	 * The data set is shown and a name is requested from the user for the registration.
	 * Once registered, the data set can be invoked by its given name.
	 */
	@FXML
	private void handleLoad() {
		String chosenFileName = "";
		String chosenFileNameCanonical = "";

		String [] filenames = handleShowCSV();
		chosenFileName = filenames[1];
		chosenFileNameCanonical = filenames[0];

		System.out.println(chosenFileName +"\n" + chosenFileNameCanonical);

		// Now we must give the dataset a name
		NameGiverForDatasetController nameGetter = new NameGiverForDatasetController();
		//The object is still there even after we have closed the the stage
		String datasetName = nameGetter.getText();
		System.out.println(datasetName);

		//now we must register the dataset
		datasetManager.registerDataset(datasetName, chosenFileNameCanonical);

	}//end method handleLoad

	/**
	 * Facilitates the selection of a CSV from the hard disk and its demonstration
	 * 
	 * @return a String Array with two values: the 0th value with the canonical filename of the chosen CSV and the 1st value with the plain filename
	 */
	@FXML
	private String [] handleShowCSV() {
		String chosenFileName = "";
		String chosenFileNameCanonical = "";

		//https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Pick a CSV file");
		//fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.setInitialDirectory(new File("Resources"));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Files", "*.*"),
				new FileChooser.ExtensionFilter("CSV", "*.csv")
				);
		File file = fileChooser.showOpenDialog(new Stage());
		if(file == null)
			return null;
		else
			try {
				chosenFileNameCanonical = file.getCanonicalPath();
				chosenFileName = file.getName(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		String [] filenames = new String[2];
		filenames[1] = chosenFileName;
		filenames[0] = chosenFileNameCanonical;

		SimpleCSVReader cvsReader = new SimpleCSVReader();
		ArrayList<String []> data = new ArrayList<String []>(); 
		data = cvsReader.load(chosenFileNameCanonical);


		TableViewController tbl = new TableViewController(data, true);
		tbl.show((new Stage()), chosenFileName);

		return filenames;

	}//end handleShow

	/**
	 * Demonstrates an already registered dataset to the user, by requesting its name.
	 * 
	 * Error message if the name is not registered.
	 */
	@FXML
	private void showRegisteredDataset() {
		// Now we must pick the dataset from its name
		NameGiverForDatasetController nameGetter = new NameGiverForDatasetController();
		//The object is still there even after we have closed the the stage
		String datasetName = nameGetter.getText();
		System.out.println(datasetName);

		ArrayList<String[]> data = new ArrayList<String[]>();
		String [] header = datasetManager.retrieveDataset(datasetName, data);

		if (header == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Wrong dataset name");
			//alert.setHeaderText("A simple Stats Application");
			alert.setContentText("The dataset name you gave is not registered! You must try again");
			alert.showAndWait();
			return;
		}
		TableViewController tbl = new TableViewController(data, header);
		tbl.show((new Stage()), datasetName);
	}

	/**
	 * Asks the user to pose a filter over an existing data, of the form (Attribute = value) and produces a new data set that is also registered and shown to the user
	 */
	@FXML
	private void handleFilter() {
		FilterConstructor filterConstructor = new FilterConstructor();
		String oldDatasetName = filterConstructor.getOriginalDatasetName();     	
		String newDatasetName = filterConstructor.getNewDatasetName();     	
		String attribute = filterConstructor.getAttribute();     	
		String value = filterConstructor.getValue();

		String summary = attribute + " = " + value + " at dataset " + oldDatasetName + " gives " + newDatasetName;
		System.out.println(summary);
		
		int regFlag = datasetManager.filterDataset(oldDatasetName, newDatasetName, attribute, value);
		if (regFlag <0 )
			return;

		ArrayList<String[]> data = new ArrayList<String[]>();
		String [] header = datasetManager.retrieveDataset(newDatasetName, data);
		TableViewController tbl = new TableViewController(data, header);
		tbl.show((new Stage()), newDatasetName);
	}//end handle filter
	
	/**
	 * Demonstrates a line chart to the user.
	 * 
	 * Requests the name of the data set, the attribute for the x-axis and the attribute for the y-axis.
	 * Optionally, labels for the two axes can be given, too.
	 */
	@FXML
	private void handleLineChart() {
		AttributePairSelector pairSelector = new AttributePairSelector();
		String datasetName = pairSelector.getDatasetName();     	
		String xAxisMeasure = pairSelector.getxAxisMeasure(); 
		String xAxisTitle = pairSelector.getxAxisTitle();
		String yAxisMeasure = pairSelector.getyAxisMeasure(); 
		String yAxisTitle = pairSelector.getyAxisTitle();

		String chartTitle = yAxisTitle + " over " + xAxisTitle + " for dataset " + datasetName;
		System.out.println(chartTitle);

		ArrayList<String> requestedAttributes = new ArrayList<String>();
		requestedAttributes.add(xAxisMeasure); requestedAttributes.add(yAxisMeasure);

		//ask now the dataset manager to give you a array with xAxis,yAxis measures as columns
		ArrayList<String[]> result = datasetManager.getDatasetProjection(datasetName, requestedAttributes);

		// ********************** DEMO FOR LINECHART
		LineChartPresenter lcp = new LineChartPresenter(new Stage(), chartTitle, xAxisTitle, yAxisTitle, result);
		lcp.displayChartSingleSeries();

	}//end handleLineChart

	/**
	 * Demonstrates a scatter chart to the user.
	 * 
	 * Requests the name of the data set, the attribute for the x-axis and the attribute for the y-axis.
	 * Optionally, labels for the two axes can be given, too.
	 */
	@FXML
	private void handleScatterChart() {
		AttributePairSelector pairSelector = new AttributePairSelector();
		String datasetName = pairSelector.getDatasetName();     	
		String xAxisMeasure = pairSelector.getxAxisMeasure(); 
		String xAxisTitle = pairSelector.getxAxisTitle();
		String yAxisMeasure = pairSelector.getyAxisMeasure(); 
		String yAxisTitle = pairSelector.getyAxisTitle();

		String chartTitle = yAxisTitle + " over " + xAxisTitle + " for dataset " + datasetName;
		System.out.println(chartTitle);

		ArrayList<String> requestedAttributes = new ArrayList<String>();
		requestedAttributes.add(xAxisMeasure); requestedAttributes.add(yAxisMeasure);

		//ask now the dataset manager to give you a array with xAxis,yAxis measures as columns
		ArrayList<String[]> result = datasetManager.getDatasetProjection(datasetName, requestedAttributes);

		if(result==null) {
			System.out.println("NULL result for getting the scatterplot's data projection");
			return;
		}
		if(result.size() == 0) {
			System.out.println("EMPTY result for getting the scatterplot's data projection");
			return;
		}
		// ********************** DEMO FOR LINECHART
		ScatterChartPresenter scp = new ScatterChartPresenter(new Stage(), chartTitle, xAxisTitle, yAxisTitle, result);
		scp.displayChartSingleSeries();

	}//end handleLineChart


	
	private IDatasetManager datasetManager;
}//end class



