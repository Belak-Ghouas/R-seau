

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private PrintWriter writerC;
	private BufferedReader readerC;
	private BufferedReader input;
	private PrintWriter output;
	private final Scanner sc = new Scanner(System.in);

	public Client(Socket _socket) throws IOException {
		socket = _socket;
		 input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		 output = new PrintWriter( socket.getOutputStream());

		

	}

	void connect() throws IOException {
		try {
			String message="";
			
		
		while (socket.isConnected()) {
			
				message=input.readLine();
				if (message.contains("server")) {
					PeerToPeer(message);
				}else if(message.contains("client")) {
					ConnectTo(message);
				}else {
				String[] msg=message.split(",");
				for (String string : msg) {
					System.out.println(string);
				}
				}
			
			output.write(sc.nextLine()+"\n");
			output.flush();
			
			
		}
		socket.close();
		input.close();
		output.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}

	private void ConnectTo(String message) throws NumberFormatException, UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		
		String[] split=message.split(" ");
		System.out.println(split[1]);
		Socket sockC = new Socket(split[1],Integer.parseInt(split[2]));
		readerC = new BufferedReader(new InputStreamReader(sockC.getInputStream()));
		writerC = new PrintWriter( sockC.getOutputStream());
		String msg="";
		while(sockC.isConnected() && !sockC.isClosed()) {
			
			msg=readerC.readLine();
			System.out.println(msg);
			
			
			writerC.write(sc.nextLine()+"\n") ;
			writerC.flush();
			
			msg="";
			
			
		}
		
		
	}

	private void PeerToPeer(String message) throws NumberFormatException, IOException {
		
		BufferedReader readerS;
		PrintWriter writerS;
		System.out.println(message);
		String [] result = message.split(" ");
		ServerSocket server= new ServerSocket(Integer.parseInt(result[1]));
		
		Socket sockTmp = server.accept();
		
		readerS = new BufferedReader(new InputStreamReader(sockTmp.getInputStream()));
		writerS = new PrintWriter( sockTmp.getOutputStream());
		String response="";
		writerS.write("bonjour je voulais te contacter"+"\n");
		writerS.flush();
		while(sockTmp.isConnected() && !sockTmp.isClosed()) {
			
			response=readerS.readLine();
			System.out.println(response);
			response="";
			writerS.write(sc.nextLine()+"\n");
			writerS.flush();
			
			
			
			
		}
		
		
		
	}
}
