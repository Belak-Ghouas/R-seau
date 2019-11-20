import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static int port = 7000;
	public int portTcp;
	private static ServerSocket server;

	public Serveur(int port) throws IOException {
		portTcp = port;
		Gestionnaire geste = new Gestionnaire();
		//User user=new User();
		server=new ServerSocket(port);
		
	}

}
		



