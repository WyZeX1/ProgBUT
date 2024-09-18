
public class DocumentDejaEmprunteException extends Exception {

	public int numInventaire;
	
	public DocumentDejaEmprunteException(int ni) {
		this.numInventaire=ni;
	}
	
	public String toString() {
		return "Le document "+this.numInventaire+" est déjà emprunté";
	}
}
