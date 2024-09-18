
public class DocumentInexistantException extends Exception {

	public int numInventaire;
	
	public DocumentInexistantException(int ni) {
		this.numInventaire=ni;
	}
	
	public String toString() {
		return "Le document "+this.numInventaire+" est inexistant";
	}
}
