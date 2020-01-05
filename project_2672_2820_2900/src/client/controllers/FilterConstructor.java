package client.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FilterConstructor {

	public FilterConstructor() {
        Label originalDatasetdLbl = new Label("Original Dataset (*)");
		TextField originalDatasetTB = new TextField();  

		Label attributeLbl = new Label("Attribute (*)");
		TextField attributeTB = new TextField();  
 
        Label valueLbl = new Label("Value: (*)");
		TextField valueTB = new TextField();  
       
		Label newDatasetdLbl = new Label("New Dataset (*)");
		TextField newDatasetTB = new TextField();  
		        
        Button submBTN = new Button("Submit");
        
        submBTN.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                originalDatasetName = originalDatasetTB.getText();
                attribute = attributeTB.getText();
                value = valueTB.getText();
                newDatasetName = newDatasetTB.getText();
                
                if(originalDatasetName.equals("")) {
        	    	Alert alert = new Alert(AlertType.WARNING);
        	    	alert.setTitle("Empty originalDataset name");
        	    	alert.setHeaderText("Empty originalDataset name");
        	    	alert.setContentText("The originalDataset name must be completed");
        	    	alert.showAndWait();
        	    	return;
        		}
                

                
                stage.close();
            }
        }));
        
        VBox root = new VBox();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().addAll(originalDatasetdLbl, originalDatasetTB, attributeLbl, attributeTB, valueLbl, valueTB, newDatasetdLbl, newDatasetTB);        
        root.getChildren().add(submBTN);
        
        Scene scene = new Scene(root, 300, 300);
        this.stage = new Stage();
        stage.setTitle("Please give me the necessary filter");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
	}//end constructor


	public Stage getStage() {
		return stage;
	}
	public String getAttribute() {
		return attribute;
	}

	public String getValue() {
		return value;
	}

	public String getOriginalDatasetName() {
		return originalDatasetName;
	}
	
	public String getNewDatasetName() {
		return newDatasetName;
	}
	
    private Stage stage;
    private String attribute; 
    private String value; 
    private String originalDatasetName;
    private String newDatasetName;

}//end class
