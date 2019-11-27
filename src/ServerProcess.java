
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.util.Pair;

public class ServerProcess implements Runnable {
	
	
	Gestionnaire geste;
	User user;
	BufferedReader reader;
	PrintWriter writer;
	Configurator config;
	String Error="";
	

	public ServerProcess(Gestionnaire geste, Socket socket, Configurator config) throws IOException {
		this.geste = geste;
		this.user = new User(socket);
		this.config = config;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
		writer = new PrintWriter( socket.getOutputStream());

	}

	@Override
	public void run() {
		
		String request;
		String R="";
		String pseudo;
		int choix = -1;
		boolean ok=true;
		while (ok) {
			ok=user.socket.isConnected() && !user.socket.isClosed();
		
			request = "";
			try {
					
				if (!user.isLogin()) {
					
					envoyer(Error+config.getMSG_BIENVENUE());
					Error="";
					choix=lireEntier();
					
					switch (choix) {
					case 1:
						envoyer(config.getPSEUDO());
						request = reader.readLine();
						connect(request);

						break;
					case 2:
						envoyer(config.getPSEUDO());
						request = reader.readLine();
						sign_in(request);
						
						continue;

					default:
						Error=config.getERROR_COMMAND();
						break;
					}

				} else {
					ok=false;
				
					envoyer("Coonecté***" + user.getPseudo() + "*** , + Msg en Attente ("+this.user.EnAttente.size()+")"+config.getTABLEAU_DE_BORD());
					Traitement(choix);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void Traitement(int choix) throws IOException {
		
		boolean isconnected=true;
		
		while(isconnected) {
			
		choix = lireEntier();
		String result="";
		try {
			switch (choix) {
			case 1:
				result=poster();
				envoyer(result+ "Msg en Attente ("+this.user.EnAttente.size()+")"+config.getTABLEAU_DE_BORD());
				break;
			case 2:
				result="Toutes les annonces:, "+afficherAnnonces()+",";
				envoyer(result+"Msg en Attente ("+this.user.EnAttente.size()+")"+config.getTABLEAU_DE_BORD());
				break;
			case 3:
				result="Tapez l'indice d'annonce à supprimer: , "+geste.MesAnnonces(user.getPseudo())+" ,";
				envoyer(result);
			
				if(supprimer(user.getPseudo())) {
					result="SUCCESS";
				}else
					result="FAILED";
				
				envoyer(result+"Msg en Attente ("+this.user.EnAttente.size()+")"+config.getTABLEAU_DE_BORD());
				break;
			case 4:
				Quitter();
				isconnected=false;
				break;
			case 5:
				 result="Avec quel utilisateur souhaité vous vous connectez ," +this.geste.afficheUser();
				 envoyer(result);
				 PeerTopeer();
				 Quitter();
				 isconnected=false;
				break;
			case 6:
				 ConnectToUser();
				 Quitter();
				 isconnected=false;
				 break;
				
			default:
				result="";
				envoyer(result+"Msg en Attente ("+this.user.EnAttente.size()+")"+config.getTABLEAU_DE_BORD());
				break;
			}
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	private void ConnectToUser() throws IOException {
		
		String msg="";
		// envoyer au client que quel'un et entrain de l'attendre pour communiqué
		msg="client "+this.user.EnAttente.get(0).getKey()+" "+this.user.EnAttente.get(0).getValue();
		
		envoyer(msg);
		
		
	}

	private void PeerTopeer() throws IOException {
		// TODO Auto-generated method stub
		int	choix = lireEntier();
		
		// récupérer l'utilisateur avec qui on veut parler
		User user =this.geste.getUsers().get(choix);
		//récupérer son ip 
		String inet=this.user.getSocket().getInetAddress().toString();
		String port="7776";
		
		Pair<String, String> pair = new Pair<>(inet.substring(1), port);
		// stocké tout ça dans la liste des utilisateurs avec qui on veut parler
		
		user.EnAttente.add(pair);
		
		//envoyer un msg au client en lui disant mnt tu peux étre serveur et attendre l'autre client.
		envoyer("server "+"7776");
	}

	private boolean supprimer(String pseudo) throws IOException {
		
	int	choix = lireEntier();
	ArrayList<Annonce> liste=new ArrayList<Annonce>();
	liste=geste.MaListeAnnonce(pseudo);
	if(liste.size()<choix) {
		return false;
	}else {
		geste.supprimerAnnonce(liste.get(choix));
		return true;
	}
	
		
	}

	private void Quitter() throws IOException {
		this.user.setLogin(false);
		this.reader.close();
		this.writer.close();
		this.user.socket.close();

	}

	private String MesAnnonces(String pseudo) {
		return geste.MesAnnonces(pseudo);

	}

	private String afficherAnnonces() {
		
		return geste.afficherAnonce();
		
	}

	private String poster() throws IOException {
		// TODO Auto-generated method stub
		Annonce annonce = new Annonce();
		envoyer(">>Entrez Domaine");
		annonce.setDomaine(reader.readLine());

		envoyer(">>Entrez Description");
		annonce.setDescription(reader.readLine());

		envoyer(">>Entrez prix");
		int a=lireEntier();
		if(a<0) {
			return "ERROR";
		}else {
			annonce.setPrix(a);
			annonce.setPseudo(user.getPseudo());
			geste.ajouterAnnonce(annonce);
			return "Succes";
		}
		
		

	}

	public int lireEntier() throws IOException {
		
		int choix = -1;
		try {
		String request = reader.readLine();
		
		
			choix = Integer.parseInt(request);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return choix;
	}

	public void connect(String pseudo) throws IOException {

		if (this.geste.clientExist(pseudo)) {
			this.user = this.geste.getUser(pseudo);
			this.user.setLogin(true);
			
			
		} else {
			Error=config.getERROR_CONNECT();
		}

	}

	public void sign_in(String pseudo) throws IOException {
		if (!geste.clientExist(pseudo)) {
			this.user.setLogin(true);
			this.user.setPseudo(pseudo);
			this.geste.ajouterUser(user);
			
		} else {
			Error=config.getERROR_SIGN();

		}
	}

	public void envoyer(String message) throws IOException {
		if(user.socket.isConnected()) {
		writer.write(message+"\n");
		 writer.flush();
		}
	}

}
