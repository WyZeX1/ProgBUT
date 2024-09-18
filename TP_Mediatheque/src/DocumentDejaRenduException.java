
public class DocumentDejaRenduException extends Exception {

	public int numInventaire;
	
	public DocumentDejaRenduException(int ni) {
		this.numInventaire=ni;
	}
	
	public String toString() {
		return "Le document "+this.numInventaire+" est déjà rendu";
	}
}
