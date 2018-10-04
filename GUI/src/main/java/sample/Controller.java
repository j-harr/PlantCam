package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ListView;
import plantcam.Config;
import plantcam.Device;
import plantcam.FindDevices;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.*;


public class Controller {
    private Model model;
    private boolean findingDevices = false;

    @FXML
    private ListView<String> deviceListView;


    public Controller(Model model){
        this.model = model;
        deviceListView = new ListView<String>(model.getDeviceList());
    }



    @FXML
    private void takePicture(){
        model.getDeviceGroup().sendToAll("Please take the picture.");
    }

    @FXML
    private void findDevices() throws Exception {
        if(findingDevices == false){
            findingDevices = true;
            new Thread(){
                public void run(){
                    System.out.println("Finding devices");
                    FindDevices deviceFinder = new FindDevices(model.config());
                    try {
                        System.out.println("Calling device Finder");
                        model.setDevices(deviceFinder.call());
                        System.out.println("Done");
                    } catch (Exception e) {
                        System.out.println("This is an exception in findDevices controller");
                        e.printStackTrace();
                    }

                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            model.clearDeviceList();
                            for(Device d : model.getDevices()){
                                model.addDevice(d.name() + "\t" + d.address());
                            }
                            deviceListView.setItems(model.getDeviceList());
                        }
                    });
                    findingDevices = false;
                }
            }.start();
        } else{
            System.out.println("Currently looking for devices already");
        }



    }
}
