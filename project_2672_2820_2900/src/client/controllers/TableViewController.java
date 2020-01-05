package client.controllers;


import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableViewController {


	private final TableView<ObservableList<StringProperty>> table;
	private String [] header;
	private List<String[]> dataToShow;
	private Integer numCols = 0;
	private Integer numRows = 0;
	//ObservableList<String []> data;

	/**
	 * Constructor for data sets that we know their header and data
	 * 
	 * @param inputData An ArrayList<String> with the lines of the data set
	 * @param aHeader An Array of Strings with the header values of the columns; if null, the constructor assumes that the 0th element of the arrayList is the unknown header
	 */
	public TableViewController(ArrayList<String []> inputData, String [] aHeader) {
		if(inputData.size() > 0 && inputData != null) {
			this.dataToShow = inputData;
			numRows = inputData.size();
		}
		else {
			this.dataToShow = null;
			numRows = 0;
		}
		if(aHeader.length > 0) {
			this.header = aHeader;
			numCols = header.length; 
		}
		else {
			//			this.header = null;
			//Assume the first line is the header.
			header = inputData.get(0);
			numCols = header.length;
			dataToShow = inputData.subList(1, inputData.size()); 
		}

		table = new TableView<>();
	}

	/**
	 * Constructor for data sets that we are unaware of their contents. 
	 * 
	 * The inputData[] is assumed to contains BOTH a header and data. Can be wrong.
	 * 
	 * @param inputData The lines of the data set
	 * @param hasHeader A Boolean flag: set to true if you assume the data set to have a header, false otherwise
	 */
	public TableViewController(ArrayList<String []> inputData, Boolean hasHeader) {
		table = new TableView<>();
		numRows = inputData.size();
		if (numRows == 0)
			return;
		numCols = inputData.get(0).length;

		if (hasHeader) {
			header = inputData.get(0);
			dataToShow = inputData.subList(1, inputData.size()); 
		}
		else {
			dataToShow = inputData;
			header = new String[numCols];
			for (int i=0; i < header.length; i++)
				header[i] = "Column" + i;
		}
		System.out.println("Loaded " + numRows +" rows, "+ numCols + " columns.");
	}//end constructor


	/**
	 * populates a TableView and shows it
	 * <p>
	 * It is very important that you have used the correct constructor before calling show().
	 * <p>
	 * Practically from {@link https://community.oracle.com/message/10731570}
	 * Many thanks!!
	 * 
	 */
	public void show(Stage stage, String aTitle) {
		if(numRows <= 0 || numCols <= 0) {
			System.out.println("Cannot demonstrate data. Num. Rows: " + numRows + " numCols: " + numCols);
			return;
		}
		
		stage.setTitle(aTitle);
//		stage.setWidth(800);
//		stage.setHeight(600);

		table.setEditable(false);
		table.getColumns().clear();
//		//https://stackoverflow.com/questions/12933918/tableview-has-more-columns-than-specified/34112066
//		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
 		
		//Allows horizontal scrollbar
		table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		//Button that allows to remove col's from the ones being displayed 
        table.setTableMenuButtonVisible(true);
        
        for (int i=0; i < header.length; i++) {
			table.getColumns().add(createColumn(i, header[i]));
		}

		for(String [] nextLine: dataToShow) {
			ObservableList<StringProperty> localRow = FXCollections.observableArrayList();
			for (String value : nextLine) {
				localRow.add(new SimpleStringProperty(value));
			}			
			table.getItems().add(localRow);
		}

	
		
		AnchorPane root = new AnchorPane();	
		AnchorPane.setTopAnchor(table, 10.0);
		AnchorPane.setLeftAnchor(table, 10.0);
		AnchorPane.setRightAnchor(table, 10.0);
		AnchorPane.setBottomAnchor(table, 10.0);
		root.getChildren().add(table);


		Scene scene = new Scene(root, 800, 600);
		root.setPrefWidth(scene.widthProperty().doubleValue());
		
		stage.setScene(scene);
		stage.show();
	}

	private static TableColumn<ObservableList<StringProperty>, String> createColumn(final int columnIndex, String columnTitle) {
		TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
		String title;
		if (columnTitle == null || columnTitle.trim().length() == 0) {
			title = "Column " + (columnIndex + 1);
		} else {
			title = columnTitle;
		}
		column.setText(title);
		column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(
					CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
				ObservableList<StringProperty> values = cellDataFeatures.getValue();
				if (columnIndex >= values.size()) {
					return new SimpleStringProperty("");
				} else {
					return cellDataFeatures.getValue().get(columnIndex);
				}
			}
		});
		return column;
	}//end createColumn


}//end class



