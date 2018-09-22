import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class FindDevices implements Callable<Object> {
	private int portNum;
	private List<String> devices;
	private DatagramSocket s;
	private int msg_length;
	
	public FindDevices(int port, int msg_length) {
		System.out.println("Find Devices COnstructor");
		portNum = port;
		if(msg_length <= 0)
			this.msg_length = 100;
		else this.msg_length = msg_length;
	}
	
	public FindDevices(int port) {
		portNum = port;
		msg_length = 100;
	}

	@Override
	public List<String> call() throws Exception {
		devices = new ArrayList<String>();
		s = new DatagramSocket(portNum);
		int repeats = 0;
		int attempts = 0;
		while(repeats < 2 && attempts++ <= 5) {
			System.out.println("Find Devices Loop on port" + portNum);
			byte[] recvBuffer = new byte[msg_length];
			DatagramPacket packet = new DatagramPacket(recvBuffer, msg_length);
			System.out.println("Waiting to receive packet.");
			s.receive(packet);
			String receiveStr = new String(recvBuffer);
			
			if(devices.contains(receiveStr) == false) {
				System.out.println(receiveStr);
				devices.add(receiveStr);
			}
		}
		return devices;
	}
	
}
