package plantcam;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Root {
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(9060);
		try {
			while(true) {
				Socket socket = listener.accept();
				try {
					PrintWriter out = 
							new PrintWriter(socket.getOutputStream(), true);
					out.println("Hello tere" + InetAddress.getLocalHost().getHostAddress());
				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}
}
