import java.util.ArrayList;

public class Gestionnaire {

	private ArrayList<Annonce> annonces;
	private ArrayList<User> users;

	public ArrayList<Annonce> getAnnonces() {
		return annonces;
	}

	public void setAnnonces(ArrayList<Annonce> annonces) {
		this.annonces = annonces;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public Gestionnaire() {
		annonces = new ArrayList<Annonce>();
		users = new ArrayList<User>();
	}

	public String afficherAnonce() {
		String result = "";
		
		for (int i = 0; i < annonces.size(); i++) {
			result += "id>> " + i + "  " +annonces.get(i).Affiche()+"\n";
		}
		return result;
	}

	public void afficheUser() {
		for (int i = 0; i < users.size(); i++) {
			System.out.println("id: " + i + "  " + users.get(i).getPseudo());
		}
	}

	public void ajouterAnnonce(Annonce an) {
		System.out.println("je suis l l "+an.getDescription()); 
		this.annonces.add(an);
	}

	public boolean supprimerAnnonce(Annonce e) {
		for (int i = 0; i < annonces.size(); i++) {
			if(annonces.get(i).equals(e)) {
				annonces.remove(i);
				return true;
			}
			
		}
		return false;
	}

	public void ajouterUser(User u) {
		this.users.add(u);
	}

	public boolean supprimerUser(String pseudo) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getPseudo().equals(pseudo)) {
				users.remove(i);
				return true;

			}
		}
		return false;
	}

	public boolean clientExist(String pseudo) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getPseudo().equals(pseudo))
				return true;
		}
		return false;
	}

	public User getUser(String pseudo) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getPseudo().equals(pseudo))
				return users.get(i);
		}
		return null;
	}
	
	public String MesAnnonces(String pseudo) {
		String result="";
		for (int i = 0; i < annonces.size(); i++) {
			if (annonces.get(i).getPseudo().equals(pseudo))
				result+=annonces.get(i).Affiche();
				
		}
		return result;
	}
	public ArrayList<Annonce> MaListeAnnonce(String pseudo){
		ArrayList<Annonce> result=new ArrayList<Annonce>();
		for (int i = 0; i < annonces.size(); i++) {
			if(annonces.get(i).getPseudo()==pseudo)
				result.add(annonces.get(i));
		}
			return result;
	}
}
