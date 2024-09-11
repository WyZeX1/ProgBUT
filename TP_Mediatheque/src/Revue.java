
public class Revue extends Document {

	private int nbPages,numVolume;
	private String issn;
	
	public Revue(int numInv, String titre, String editeur, int anneeEdition, int numVolume, String issn, int nbPages ) {
		super(numInv,titre,editeur,anneeEdition);
		this.issn=issn;
		this.numVolume=numVolume;
		this.nbPages=nbPages;
	}
	
	public String toString() {
		return "Doc n°"+super.getNumInventaire()+" Revue \""+this.getTitre()+"\", volume "+this.numVolume+" de "+this.nbPages+" pages édité par "+this.getEditeur()+" (ISSN="+this.issn+")";
	}
	
	public int getNbPages() {
		return this.nbPages;
	}
	
	public int getNumVolume() {
		return this.numVolume;
	}
	
	public String getIssn() {
		return this.issn;
	}
}
