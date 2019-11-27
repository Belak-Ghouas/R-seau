
import java.net.*;

public class ClientProcessing {
	public static void main(String[] args) throws Exception {
		Client client = new Client(new Socket("172.28.46.51", 2121));
		client.connect();
	}
}



