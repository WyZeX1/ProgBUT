public class TestMediatheque
{
	public static void main(String[] args)
	{
		Document doc1 = new Document(12345,"letitre","lediteur",2020);
		System.out.println(doc1);
		
		// LIVRES
		Livre l1 = new Livre(1001,"Mademoiselle Liberté","Alexandre Jardin","Gallimard",2002,"978-2-07-042793-3",249);
		Livre l2 = new Livre(1003,"L'arbre qui rêvait...","Partice Ricordeau","L'enfant-Lumi�re",1997,"978-2-95-259340-3",44);
		Livre l3 = new Livre(1005,"L'homme qui murmurait à l'oreille des chevaux","Nicholas Evans","Pocket",1997,"978-2-26-606724-9",437);
		Livre l4 = new Livre(2000,"Ni d'Eve ni d'Adam","Amélie Nothomb","Albin Michel ",2007,"978-2-22-617964-7",244);
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(l3);
		System.out.println(l4);
		
		// DVD
		Dvd d1 = new Dvd(1002,"L'homme qui murmurait à l'oreille des chevaux","Robert Redford","Buena Vista Home Entertainement",2002);
		Dvd d2 = new Dvd(1006,"Le grand bleu","Luc Besson","Gaumont",2011);
		Dvd d3 = new Dvd(1007,"Cheval de Guerre","Steven Spielberg","Disney",2002);
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);

		// REVUE
		Revue r1 = new Revue(1004,"Entreprise Information Systems", "Taylor&Francis", 2007, 1, "1751-7575",98);
		Revue r2 = new Revue(1008,"IEEE Internet Computing", "IEEE", 2022, 6, "1089-7801",95);
		Revue r3 = new Revue(1009,"Revue Ouverte d'Intelligence Artificielle", "Centre Marsenne", 2023, 2, "2967-9672",221);
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		
		// MEDIATHEQUE
		Mediatheque m=new Mediatheque();
		m.ajouteDocument(l1);
		m.ajouteDocument(r1);
		m.ajouteDocument(d1);
		m.ajouteDocument(l2);
		m.ajouteDocument(d2);
		m.ajouteDocument(r2);
		m.ajouteDocument(l3);
		m.ajouteDocument(d3);
		m.ajouteDocument(l4);
		m.ajouteDocument(r3);
		m.afficheCatalogue();
		
		System.out.println("Le document 1003 : "+m.get(1003));
		
		
		System.out.println("On supprime le document 1009");
		m.supprimeDocument(1009);
		m.afficheCatalogue();
		
		
		// teste la gestion des emprunts
		//m.emprunteDocument(1001);
		//m.emprunteDocument(1002);
		//System.out.println("Documents emprunt�s :\n"+m.listeDocumentEmprunte());
		
		//System.out.println("R�sultat recherche (chev) :\n"+m.recherche("chev"));
		//System.out.println("R�sultat recherche (livre:chev) :\n"+m.recherche("livre:chev"));
		
	}

}