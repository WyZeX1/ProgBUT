
import java.util.LinkedList;

public class Mediatheque {

	private LinkedList<Document> bibliotheque;
	
	public Mediatheque() {
		this.bibliotheque = new LinkedList<Document>();
	}
	
	public void ajouteDocument(Document d) {
		bibliotheque.add(d);
	}
	
	public String afficheCatalogue() {
		return "La médiathèque possède "+bibliotheque.size()+" documents.";
	}

	public Document get(int numInventaire) {
		return "JSP";
	}

	public boolean supprimeDocument(int numInventaire) {
	
	}
}
