import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.util.Pair;

public class User {
	
	private String pseudo;
	private boolean login=false;
	Socket socket;
	ArrayList<Pair<String, String>> EnAttente;
	
	public User(Socket socket) {
		this.socket= socket;
		EnAttente= new ArrayList<Pair<String,String>>();
		
	}
	
	public String getPseudo() {
		return pseudo;
	}
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public boolean isLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	
	

}
