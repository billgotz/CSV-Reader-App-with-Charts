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

public class AttributePairSelector {

	public AttributePairSelector() {
        Label datasetdLbl = new Label("Dataset (*)");
		TextField datasetTB = new TextField();  

		Label xAxisAttrLbl = new Label("x-Axis Attribute (*)");
		TextField xAxisAttrTB = new TextField();  
        Label xAxisTitleLbl = new Label("x-Axis Title");
		TextField xAxisTitleTB = new TextField();  
        Label yAxisAttrLbl = new Label("y-Axis Attribute: (*)");
		TextField yAxisAttrTB = new TextField();  
        Label yAxisTitleLbl = new Label("y-Axis Title");
		TextField yAxisTitleTB = new TextField();  
		        
        Button submBTN = new Button("Submit");
        
        submBTN.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                datasetName = datasetTB.getText();
                xAxisMeasure = xAxisAttrTB.getText();
                xAxisTitle = xAxisTitleTB.getText();
                yAxisMeasure = yAxisAttrTB.getText();
                yAxisTitle = yAxisTitleTB.getText();

                if(datasetName.equals("")) {
        	    	Alert alert = new Alert(AlertType.WARNING);
        	    	alert.setTitle("Empty dataset name");
        	    	alert.setHeaderText("Empty dataset name");
        	    	alert.setContentText("The dataset name must be completed");
        	    	alert.showAndWait();
        	    	return;
        		}
                
                if(xAxisTitle.equals(""))
                	xAxisTitle = xAxisMeasure;
                if(yAxisTitle.equals(""))
                	yAxisTitle = yAxisMeasure;
                
                stage.close();
            }
        }));
        
        VBox root = new VBox();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().addAll(datasetdLbl, datasetTB, xAxisAttrLbl, xAxisAttrTB, xAxisTitleLbl, xAxisTitleTB, yAxisAttrLbl, yAxisAttrTB, yAxisTitleLbl, yAxisTitleTB);        
        root.getChildren().add(submBTN);
        
        Scene scene = new Scene(root, 300, 300);
        this.stage = new Stage();
        stage.setTitle("Please give me the necessary value");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
	}//end constructor


	public Stage getStage() {
		return stage;
	}
	public String getxAxisMeasure() {
		return xAxisMeasure;
	}
	public String getxAxisTitle() {
		return xAxisTitle;
	}
	public String getyAxisMeasure() {
		return yAxisMeasure;
	}
	public String getyAxisTitle() {
		return yAxisTitle;
	}
	public String getDatasetName() {
		return datasetName;
	}

    private Stage stage;
    private String xAxisMeasure; 
    private String xAxisTitle;
    private String yAxisMeasure; 
    private String yAxisTitle;
    private String datasetName;
}//end class
