
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.util.ArrayList;

public class ServerProcess implements Runnable {
	
	
	Gestionnaire geste;
	User user;
	DataInputStream reader;
	DataOutputStream writer;
	Configurator config;

	public ServerProcess(Gestionnaire geste, Socket socket, Configurator config) throws IOException {
		this.geste = geste;
		this.user = new User(socket);
		this.config = config;
		reader = new DataInputStream(socket.getInputStream());
		writer = new DataOutputStream(socket.getOutputStream());

	}

	@Override
	public void run() {
		String request;
		String R="";
		String pseudo;
		int choix = -1;
boolean ok=true;
		while (ok) {
			request = "";
			try {

				if (!user.isLogin()) {

					envoyer(config.getMSG_BIENVENUE());
					System.out.println("JE suis passe");
				
					choix=lireEntier();
					
					switch (choix) {
					case 1:
						envoyer(config.getPSEUDO());
						request = reader.readUTF();
						connect(request);

						break;
					case 2:
						envoyer(config.getPSEUDO());
						request = reader.readUTF();
						sign_in(request);
						
						continue;

					default:
						break;
					}

				} else {
					ok=false;
					envoyer("Heello ***" + user.getPseudo() + "***\n"+config.getTABLEAU_DE_BORD());
					
					Traitement(choix);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void Traitement(int choix) throws IOException {
		while(true) {
		choix = lireEntier();
		String result="";
		try {
			switch (choix) {
			case 1:
				result=poster();
				
				break;
			case 2:
				result="Toutes les annonces:\n "+afficherAnnonces()+"\n";
				
				break;
			case 3:
				result="Toutes les annonces:\n "+geste.MesAnnonces(user.getPseudo())+"\n";
				envoyer(result);
			//	result=MesAnnonces(user.getPseudo());
				if(supprimer(user.getPseudo())) {
					result="SUCCESS";
				}else
					result="FAILED";
				
				break;
			case 4:
				Quitter();
				break;

			default:
				result="";
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		envoyer(result+config.getTABLEAU_DE_BORD());
		}
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
		annonce.setDomaine(reader.readUTF());

		envoyer(">>Entrez Description");
		annonce.setDescription(reader.readUTF());

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
		System.out.println("Lre eniter");
		String request = reader.readUTF();
		
		try {
			choix = Integer.parseInt(request);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return choix;
	}

	public void connect(String pseudo) throws IOException {

		if (this.geste.clientExist(pseudo)) {
			this.user = this.geste.getUser(pseudo);
			
		} else {
			envoyer(config.getERROR_CONNECT());
		}

	}

	public void sign_in(String pseudo) throws IOException {
		if (!geste.clientExist(pseudo)) {
			this.user.setLogin(true);
			this.user.setPseudo(pseudo);
			this.geste.ajouterUser(user);
			
		} else {
			envoyer(config.getERROR_SIGN());

		}
	}

	public void envoyer(String message) throws IOException {
		writer.writeUTF(message);
		 writer.flush();

	}

}
