import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) throws IOException {
		String serverAddress = JOptionPane.showInputDialog(
				"Enter IP Address of machine that has server:");
		Socket s = new Socket(serverAddress, 9090);
		BufferedReader input = 
				new BufferedReader(new InputStreamReader(s.getInputStream()));
		String answer = input.readLine();
		JOptionPane.showMessageDialog(null,  answer, "Response", 0);
		System.exit(0);
	}
}
