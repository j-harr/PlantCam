import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Master {
	public static void execute() throws Exception {
		System.out.println("Executing as MASTER");
		System.out.println("Format: xxx.xxx.xxx.xxx=>Type your message here");
		System.out.println("Type 'exit' to quit");
		FindDevices deviceFinder = new FindDevices(9020);
		deviceFinder.call();
		Scanner scan = new Scanner(System.in);
		System.out.println("Starting loop in Master");
		while(true) {
			String line = scan.nextLine();
			if(line.equalsIgnoreCase("EXIT"))
				break;
			String host = line.substring(0, line.indexOf("=>"));
			String message = line.substring(line.indexOf("=>"), line.length());
			Socket s;
			try {
				s = new Socket(host, 9060);
				PrintWriter out = 
						new PrintWriter(s.getOutputStream(), true);
				out.println(message);
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		scan.close();
	}
}
