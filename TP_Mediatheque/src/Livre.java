
public class Livre extends Document {

	private int nbPages;
	private String auteur,isbn;
	
	public Livre(int numInv, String titre, String auteur, String editeur, int anneeEdition, String isbn, int nbPages ) {
		super(numInv,titre,editeur,anneeEdition);
		this.auteur=auteur;
		this.isbn=isbn;
		this.nbPages=nbPages;
	}
	
	public String toString() {
		return "Doc n°"+super.getNumInventaire()+" Livre \""+this.getTitre()+"\" de "+this.nbPages+" pages écrit par "+this.auteur+" en "+this.getAnneeEdition()+" (ISBN="+this.isbn+")";
	}
	
	public int getNbPages() {
		return this.nbPages;
	}
	
	public String getAuteur() {
		return this.auteur;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
}
