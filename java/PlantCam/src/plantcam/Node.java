package plantcam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Node {
	public void execute() throws Exception {
		System.out.println("Executing as NODE");
		Callable<Object> bcaster = new Broadcast(Config.discoveryPort);
		FutureTask<Object> broadcastTask = new FutureTask<Object>(bcaster);
		Thread t_broadcast = new Thread(broadcastTask);
		t_broadcast.start();
		
		/* Listen for commands */
		try {
			ServerSocket listener = new ServerSocket(9060);
			while(true) {
				System.out.println("Server waiting for command...");
				Socket socket = listener.accept();
				BufferedReader input = 
						new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String query = input.readLine();
				if(query.isEmpty() == false) {
					System.out.println("Message recieved:" + query);
					try {
					Process process = new ProcessBuilder("PlantCam").start();
					} catch(Throwable t) {
						System.out.println("Could not execute process");
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
