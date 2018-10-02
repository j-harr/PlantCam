package sample;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ListView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class Controller {

    private ObservableList<String> outputLog;


    @FXML
    private FlowPane flowPane;
    @FXML
    private ListView listView;

    @FXML
    private void findDevices(){
        System.out.println("Finding devices");
        listView.getItems().add("Hello");
    }
}
