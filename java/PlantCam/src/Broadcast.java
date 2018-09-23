import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class Broadcast implements Callable<Object>{
	private int portNum;
	private String localhost;
	private String hostname;
	private String baseNetwork;
	private DatagramSocket s;
	
	public Broadcast(int port) throws UnknownHostException {
		System.out.println("Broadcast constructor");
		portNum = port;
		localhost = InetAddress.getLocalHost().getHostAddress();
		hostname = InetAddress.getLocalHost().getHostName();
		baseNetwork = localhost.substring(0, localhost.lastIndexOf("."));
		if(localhost.substring(0,3).equals("127")) {
			baseNetwork = Config.baseNetwork;
			System.out.println("Using base network: " + baseNetwork);
		}
		System.out.println(localhost);
	}

	public Object call() throws Exception {
		/* Sending a non-blocking UDP message */
		s = new DatagramSocket(portNum);
		
		/* Broadcast Continuously */
		while(true) {
			System.out.println("Broadcasting...");
			/* Send device name and address to every address on LAN */
			for(int i = 1; i <= 255; i++) {
				String ip = baseNetwork+ "." + Integer.toString(i);
				InetAddress reciever = InetAddress.getByName(ip);
				byte[] sendBuffer = (localhost + "=" + hostname).getBytes();
				DatagramPacket packet = new DatagramPacket(sendBuffer,
						sendBuffer.length, reciever, portNum);
						
				s.send(packet);
			}
			Thread.sleep(500);
		}
	}


}
