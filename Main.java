package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    //


    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle("Address Book");
        window.setMinWidth(400);
        window.setMinHeight(600);
        window.setScene(new Scene(root, 300, 275));
        window.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
