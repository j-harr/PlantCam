package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class Main extends Application {

    @FXML
    private ListView<String> deviceListView;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ObservableList<String> devices = FXCollections.observableArrayList();
        deviceListView = new ListView<>(devices);
        deviceListView.setEditable(true);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("PlantCam");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
