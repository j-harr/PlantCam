import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {
		System.out.println(args.length);
		Scanner scanny = new Scanner(System.in);
		System.out.print("Enter address you would like to connect to: ");
		String serverAddress = scanny.nextLine();
		Socket s = new Socket(serverAddress, 9060);
		BufferedReader input = 
				new BufferedReader(new InputStreamReader(s.getInputStream()));
		String answer = input.readLine();
		System.out.println(answer);
		scanny.close();
		s.close();
		System.exit(0);
	}

	public static List<Device> scanNetwork() throws IOException{
		String thisAddress = InetAddress.getLocalHost().getHostAddress();
		String baseNetwork = thisAddress.substring(0, thisAddress.lastIndexOf("."));
		List<Device> devices = new ArrayList<Device>();
		System.out.println("Scanning network with base: " + baseNetwork);
		int timeout=1;
		for(int i = 1; i < 255; i++){
			String host = baseNetwork + "." + i;
			if(InetAddress.getByName(host).isReachable(timeout)){
				devices.add(new Device(host));
				System.out.println(host);
			}
		}
		if(devices.isEmpty())
			System.out.println("No devices found");
		return devices;
	}
}
