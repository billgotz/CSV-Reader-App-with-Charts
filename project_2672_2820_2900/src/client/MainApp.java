/**
 * 
 */
package client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

//import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A simple client for the management of CVS files and their statistical exploitaiton
 * 
 * @author pvassil
 *
 */
public class MainApp extends Application {

	
	public MainApp() {
		;
	}

	/**
	 *  The start method of the entire application.
	 *  
	 *  The main idea of a javafx application is:
	 *  (a) You need a stage, where the "windows" of the application will appear
	 *  (b) You need a scene to host a window, that belongs to the stage
	 *  (0) ... but before that, you need to tell the compiler how the current class 
	 *      relates to an FXML specification of the "window" that will appear
	 *      
	 *  The most general way for (0) is the one sugggested by Oracle itself
	 *      https://docs.oracle.com/javafx/2/get_started/fxml_tutorial.htm
	 *  Also, since the .class of this file is located in the /bin/ folder
	 *  we need to point to the correct location of the fxml file, i.e., views/xxx.fxml
	 *    
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
    @Override
    public void start(Stage stage) throws Exception {
    	

    	//VBox root = FXMLLoader.<VBox>load(getClass().getResource("views/MainApp.fxml"));
    	Parent root = FXMLLoader.load(getClass().getResource("views/MainApp.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }//end start

	/**
	 * The main client of the project
	 * 
	 * @param args  A lists of strings. Usage: empty.
	 */
    public static void main(String[] args) {
        launch(args);
    }//end main

    

    
}//end class
