import java.util.List;

public class DeviceGroup {
	private List<Device> devices;
	
	public DeviceGroup(List<Device> deviceList) {
		devices = deviceList;
	}
	
	public void send(String message) {
		for(Device d : devices) {
			d.sendMessage(Config.commandsPort, message);
		}
	}
	
	
	
}
