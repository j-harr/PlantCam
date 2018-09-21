import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) throws IOException {
		scanNetwork("192.168.2");
		Scanner scanny = new Scanner(System.in);
		System.out.print("Enter address you would like to connect to: ");
		String serverAddress = scanny.nextLine();
		Socket s = new Socket(serverAddress, 9090);
		BufferedReader input = 
				new BufferedReader(new InputStreamReader(s.getInputStream()));
		String answer = input.readLine();
		System.out.println(answer);
		scanny.close();
		System.exit(0);
	}

	public static void scanNetwork(String baseNetwork) throws IOException{
		int timeout=10;
		for(int i = 1; i < 20; i++){
			String host = baseNetwork + "." + i;
			if(InetAddress.getByName(host).isReachable(timeout)){
				System.out.println("Found device:" + host);
			}
			System.out.print("\rScanning network: ["+ (i * 100 / 20) + "%]");
		}
		System.out.println();
	}
}
