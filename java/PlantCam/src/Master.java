import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Master {
	public void execute() throws Exception {
		System.out.println("Executing as MASTER");
		System.out.println("Type 'exit' to quit");
		FindDevices deviceFinder = new FindDevices(9030);
		deviceFinder.call();
		Scanner scan = new Scanner(System.in);
		System.out.println("Starting loop in Master");
		while(true) {
			String line = scan.nextLine();
			if(line.equalsIgnoreCase("EXIT"))
				break;
			String host = line.substring(0, line.indexOf("=>"));
			String message = line.substring(line.indexOf("=>"), line.length());
			
		}
		scan.close();
	}
}
