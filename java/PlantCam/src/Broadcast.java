import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Callable;

public class Broadcast implements Callable<Object>{
	private int portNum;
	private String localhost;
	private String hostname;
	private String baseNetwork;
	private DatagramSocket s;
	
	public Broadcast(int port) throws UnknownHostException, SocketException {
		System.out.println("Broadcast constructor");
		portNum = port;
		localhost = getLocalHost();
		hostname = InetAddress.getLocalHost().getHostName();
		System.out.println(localhost);
		baseNetwork = localhost.substring(0, localhost.lastIndexOf("."));
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

	public static String getLocalHost() throws SocketException {
		List<String> hosts = new ArrayList<String>();
		
		Enumeration<NetworkInterface> net;
		net = NetworkInterface.getNetworkInterfaces();
		while(net.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) net.nextElement();
			Enumeration<InetAddress> adr = ni.getInetAddresses();
			while(adr.hasMoreElements()) {
				InetAddress ia = (InetAddress) adr.nextElement();
				String a = ia.getHostAddress();
				if((a.length() - a.replace(".","").length()) == 3) {
					String base = a.substring(0, 3);
					if(base.equals("127") == false && base.equals("172") == false)
						return ia.getHostAddress();
					else hosts.add(ia.getHostAddress());
				}
			}
		}
		if(hosts.isEmpty())
			return "none";
		else {
			return hosts.get(0);
		}
	}
}
