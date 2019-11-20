import java.net.Socket;

public class User {
	
	private String pseudo;
	private boolean login=false;
	Socket socket;
	
	public User(Socket socket) {
		this.socket= socket;
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
