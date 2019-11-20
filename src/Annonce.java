
public class Annonce {

	private	String description;
	private String domaine;
	private int prix;
	private String pseudo;
	
	
	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public String Affiche() {
		return this.getPseudo()+"   "+this.getDomaine()+"     "+ this.getDescription();
	}
}
