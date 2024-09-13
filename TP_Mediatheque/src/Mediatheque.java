
import java.util.LinkedList;

public class Mediatheque {

	private LinkedList<Document> inventaire;
	
	public Mediatheque() {
		this.inventaire = new LinkedList<Document>();
	}
	
	public void ajouteDocument(Document d) {
		inventaire.add(d);
	}
	
	public void afficheCatalogue() {
		System.out.println("La médiathèque possède "+inventaire.size()+" documents.");
		for (int i=0 ; i < inventaire.size();i++) {
			System.out.println(inventaire.get(i));
		}
	}

	public Document get(int numInventaire) {
		for (int i=0 ; i < inventaire.size();i++) {
			if (inventaire.get(i).getNumInventaire() == numInventaire) {
				return inventaire.get(i);
			}
		}
		return null;
	}

	public boolean supprimeDocument(int numInventaire) {
		for (int i=0 ; i < inventaire.size();i++) {
			if (inventaire.get(i).getNumInventaire() == numInventaire) {
				inventaire.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void emprunte(int numInventaire) {
		
	}
}
