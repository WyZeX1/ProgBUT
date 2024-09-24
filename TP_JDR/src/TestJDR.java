
public class TestJDR {

	public static void main (String[] args) {
		
		Personnage p1 = new Personnage("Alex");
		Personnage p2 = new Personnage("Bob",1,20,2);
		Personnage jamont = new Personnage("JPeG",20,20,20);
		
		Nain p3 = new Nain("Gimli",15,5,4,20);
		Elfe p4 = new Elfe("Legolas",19,19,19,20);
	
		System.out.println(p1);
		System.out.println("\nIl arrive ! -------"+jamont+"-------\n");
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);
		
		
		Equipe e1 = new Equipe("R&T Killers");
		try {
			e1.addMembre(p1);
			e1.addMembre(p2);
			e1.addMembre(p3);
			e1.addMembre(jamont);
			e1.addMembre(p4);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(e1);;
	}
}