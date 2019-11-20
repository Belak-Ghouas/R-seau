

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private final Scanner sc = new Scanner(System.in);

	public Client(Socket _socket) {
		socket = _socket;

		

	}

	void connect() throws IOException {
		try {
			DataInputStream input = new DataInputStream(socket.getInputStream());

	        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		
		while (true) {
			
				
			System.out.println(input.readUTF());
			output.writeUTF(sc.nextLine());
			
			
		}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
}
