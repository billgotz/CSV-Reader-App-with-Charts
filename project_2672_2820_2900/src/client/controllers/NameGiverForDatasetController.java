package client.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Simple class that displays a single textfield for the user to input information
 * 
 * Typically used to give a name for a data set, either for loading or registration purposes
 * @author pvassil
 *
 */
public class NameGiverForDatasetController {

	public NameGiverForDatasetController() {
        textBox = new TextField();  
        
        Button submBTN = new Button("Submit");
        
        submBTN.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                datasetName = getText();
                stage.close();
            }
        }));
        
        VBox root = new VBox();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().add(textBox);        
        root.getChildren().add(submBTN);
        Scene scene = new Scene(root, 300, 250);

        this.stage = new Stage();
        stage.setTitle("Please give me the necessary value");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
    }//end constructor

    public String getText() {
        return textBox.getText();
    }
    
    
    private Stage stage;
    private TextField textBox; 
    private String datasetName;
}


