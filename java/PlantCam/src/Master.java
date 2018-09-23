import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Master {
	private List<Device> devices;
	private DeviceGroup deviceGroup;
	
	public void execute() throws Exception {
		System.out.println("Executing as MASTER");
		System.out.println("Type 'exit' to quit");
		FindDevices deviceFinder = new FindDevices(9030);
		
		devices = deviceFinder.call();
		deviceGroup = new DeviceGroup(devices);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Starting loop in Master");
		while(true) {
			String line = scan.nextLine();
			if(line.equalsIgnoreCase("EXIT"))
				break;
			else {
				deviceGroup.send(line);
			}
			
		}
		scan.close();
	}
}
