package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import plantcam.Config;

public class Model {
    @FXML
    private ObservableList<String> deviceList;

    Config cfg;

    public Model(){
        deviceList = FXCollections.observableArrayList();
        cfg = new Config();
    }

    public void clearDeviceList(){
        deviceList.clear();
    }

    public void addDevice(String device){
        deviceList.add(device);
        System.out.println("Added a device to the list.");
    }

    public ObservableList<String> getDeviceList(){return deviceList;}

    public Config config(){return cfg;}
}
