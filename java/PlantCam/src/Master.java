import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Master {
	public static void execute() {
		System.out.println("Executing as MASTER");
		System.out.println("Format: xxx.xxx.xxx.xxx=>Type your message here");
		System.out.println("Type 'exit' to quit");
		Scanner scan = new Scanner(System.in);
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
