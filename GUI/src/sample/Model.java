package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import plantcam.Config;
import plantcam.Device;
import plantcam.DeviceGroup;

import java.util.List;

public class Model {
    private ObservableList<String> deviceList;
    private List<Device> devices;
    private DeviceGroup deviceGroup;
    private Config cfg;

    public Model(){
        deviceList = FXCollections.observableArrayList();
        deviceGroup = new DeviceGroup(devices, cfg);
        cfg = new Config();
    }

    public void clearDeviceList(){
        deviceList.clear();
    }

    public void addDevice(String device){
        deviceList.add(device);
        System.out.println("Added a device to the list.");
    }

    public void setDevices(List<Device> devices){
        this.devices = devices;
        this.deviceGroup = new DeviceGroup(this.devices);
    }

    public ObservableList<String> getDeviceList(){return deviceList;}
    public List<Device> getDevices(){return devices;}
    public DeviceGroup getDeviceGroup(){return deviceGroup;}
    public Config config(){return cfg;}
}
