import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Node {
	public static void execute() {
		System.out.println("Executing as NODE");
		try {
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
