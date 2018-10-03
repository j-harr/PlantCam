package sample;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ListView;
import plantcam.Config;
import plantcam.Device;
import plantcam.FindDevices;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class Controller {
    private List<Device> devices;

    @FXML
    private ObservableList<String> deviceList;

    @FXML
    private ListView deviceListView;

    @FXML
    private void findDevices() throws Exception {
        Config cfg = new Config();
        System.out.println("Finding devices");
        FindDevices deviceFinder = new FindDevices(cfg);
        devices = deviceFinder.call();
        deviceList.clear();
        for(Device d : devices){
            deviceList.add(d.name() + "\t" + d.address());
        }
    }
}
