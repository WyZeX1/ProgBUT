
public class Dvd extends Document {

	private String nomReal;
	
	public Dvd(int numInv, String titre, String nomReal, String editeur, int anneeEdition) {
		super(numInv,titre,editeur,anneeEdition);
		this.nomReal=nomReal;
	}
	
	public String getReal() {
		return this.nomReal;
	}
	
	public String toString() {
		return "Doc n°"+super.getNumInventaire()+" DVD \""+this.getTitre()+"\" réalisé par "+this.nomReal+" édité par "+this.getEditeur()+" en "+this.getAnneeEdition();
	}
	
}
