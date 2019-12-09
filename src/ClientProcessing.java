
import java.net.*;

public class ClientProcessing {
	public static void main(String[] args) throws Exception {
		Client client = new Client(new Socket("localhost", 2121));
		client.connect();
	}
}



