import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Node {
	public static void execute() throws Exception {
		System.out.println("Executing as NODE");
		Broadcast bcaster = new Broadcast(9020);
		bcaster.call();
		try {
			System.out.println("Got to server socket.");
			ServerSocket listener = new ServerSocket(9060);
			while(true) {
				Socket socket = listener.accept();
				BufferedReader input = 
						new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String query = input.readLine();
				if(query.isEmpty() == false) {
					System.out.println("Message recieved:" + query);
					try {
					Process process = new ProcessBuilder("PlantCam").start();
					} catch(Throwable t) {
						System.out.println("Could not execute file");
						t.printStackTrace();
					}
				}
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
