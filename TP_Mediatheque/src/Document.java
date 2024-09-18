
public class Document {

	private int anneeEdition;
	private int numInventaire;
	private String titre,editeur;
	private boolean emprunte;
	
	public Document(int numInv, String titre, String editeur, int annee) {
		this.numInventaire=numInv;
		this.titre=titre;
		this.editeur=editeur;
		this.anneeEdition=annee;
	}
	
	public String toString() {
		return "Document [numero_inventaire=" + this.numInventaire + ", titre=" + this.titre + ", editeur=" + this.editeur + ", annee_edition=" + this.anneeEdition + "]";
	}

	public int getNumInventaire() {
		return this.numInventaire;
	}

	public String getTitre() {
		return this.titre;
	}
	
	public String getEditeur() {
		return this.editeur;
	}
	
	public int getAnneeEdition() {
		return this.anneeEdition;
	}
	
	public void emprunte(boolean e) {
		this.emprunte=e;
	}
	
	public boolean estEmprunte() {
		return this.emprunte;
	}
}
