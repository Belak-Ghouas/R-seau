
import java.net.*;

public class ClientProcessing {
	public static void main(String[] args) throws Exception {
		Client client = new Client(new Socket("172.28.46.147", 2121));
		client.connect();
	}
}



