import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Principale {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Gestionnaire geste = new Gestionnaire();
		 Configurator config = new Configurator();
		
		
		try {
			ServerSocket server = new ServerSocket(2121);
			
			while (true) {
				Socket socket = server.accept();
				ServerProcess mult = new ServerProcess(geste,socket,config);
				Thread t = new Thread(mult);
				t.start();
			}
		} catch (Exception e) {

		}
	}

}
