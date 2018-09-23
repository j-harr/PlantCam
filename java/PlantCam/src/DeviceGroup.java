import java.util.List;

public class DeviceGroup {
	private List<Device> devices;
	
	public DeviceGroup(List<Device> deviceList) {
		devices = deviceList;
	}
	
	public void send(String message) {
		for(Device d : devices) {
			System.out.println("Sending message to " + d.name() + " at " + d.address());
			d.sendMessage(Config.commandsPort, message);
			System.out.println("Message sent");
		}
	}
	
	
	
}
