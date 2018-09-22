import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class Broadcast implements Callable<Object>{
	private int portNum;
	private String localhost;
	private String baseNetwork;
	private DatagramSocket s;
	
	public Broadcast(int port) throws UnknownHostException {
		System.out.println("Broadcast constructor");
		
		portNum = port;
		localhost = InetAddress.getLocalHost().getHostAddress();
		baseNetwork = localhost.substring(0, localhost.lastIndexOf("."));
		System.out.println(localhost);
	}

	public Object call() throws Exception {
		s = new DatagramSocket(portNum);
		while(true) {
			System.out.println("Starting broadcast");
			for(int i = 1; i <= 255; i++) {
				String ip = baseNetwork+ "." + Integer.toString(i);
				InetAddress reciever = InetAddress.getByName(ip);
				byte[] sendBuffer = localhost.getBytes();
				DatagramPacket packet = new DatagramPacket(sendBuffer,
						sendBuffer.length, reciever, portNum);
						
				s.send(packet);
			}
			Thread.sleep(500);
		}
	}


}
