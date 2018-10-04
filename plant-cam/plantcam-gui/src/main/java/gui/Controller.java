package gui;

import core.Device;
import core.FindDevices;
import gui.Model;
import javafx.application.Platform;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;



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
