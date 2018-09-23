import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Test {
	public static void main(String[] args) throws SocketException {
		String hostname = "Unknown";
		
		Enumeration<NetworkInterface> net;
		net = NetworkInterface.getNetworkInterfaces();
		while(net.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) net.nextElement();
			Enumeration adr = ni.getInetAddresses();
			while(adr.hasMoreElements()) {
				InetAddress ia = (InetAddress) adr.nextElement();
				System.out.println(ia.getHostAddress());
			}
		}

		/*try
		{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    System.out.println(addr);
		    hostname = addr.getHostName();
		    System.out.println(addr.getHostAddress());
		    System.out.println(hostname);
		}
		catch (UnknownHostException ex)
		{
		    System.out.println("Hostname can not be resolved");
		}*/
	}
}
