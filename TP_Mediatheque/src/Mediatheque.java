
import java.util.ArrayList;

public class Mediatheque {

	private ArrayList<Document> inventaire;
	
	public Mediatheque() {
		this.inventaire = new ArrayList<Document>();
	}
	
	public void ajouteDocument(Document d) {
		this.inventaire.add(d);
	}
	
	public String toString() {
		String resultat = "La médiathèque possède "+this.inventaire.size()+" documents.\n";
		for (int i=0 ; i < this.inventaire.size();i++) {
			resultat += this.inventaire.get(i).toString()+"\n";
		}
		return resultat;
	}
	
	public void afficheCatalogue() {
		System.out.println(this.toString());
	}

	public Document get(int numInventaire) throws DocumentInexistantException {
		for (Document d : this.inventaire) {
			if (d.getNumInventaire() == numInventaire) {
				return d;
			}
		}
		throw new DocumentInexistantException(numInventaire);
	}

	public boolean supprimeDocument(int numInventaire) throws DocumentInexistantException {
		return this.inventaire.remove(this.get(numInventaire));
	}
	
	public void emprunte(int numInventaire) throws DocumentInexistantException, DocumentDejaEmprunteException {
		Document d = this.get(numInventaire);
		d.emprunte(true);
		throw new DocumentDejaEmprunteException(numInventaire);
	}
	
	public void retourne(int numInventaire) throws DocumentInexistantException, DocumentDejaRenduException {
		this.get(numInventaire).emprunte(false);
	}
	
	public ArrayList<Document> listeDocumentEmprunte() {
		ArrayList<Document> docEmprunte = new ArrayList<Document>();
		for (Document d : this.inventaire) if (d.estEmprunte()) docEmprunte.add(d);
		return docEmprunte;
	}
	
	public ArrayList<Document> recherche(String rec) {
		ArrayList<Document> recherche = new ArrayList<Document>();
	    String[] mots_rec = rec.split(":");
	    for (Document d: this.inventaire) {
	    	for (String mot : mots_rec) {
	    		if (mot == d.getTitre().split(" ")[0]) {
		    		recherche.add(d);
		    	}
	    	
	    	}
	    }
		return recherche;
	}
}
