
public class TestJDR {

	public static void main (String[] args) {
		
	
		Personnage gahauss = new Nain("Gahauss",19,19,2,20);
		Personnage idhril = new Elfe("Idhril",15,13,18,20);
		Personnage p3 = new Nain("Gimli",15,5,4,20);
		Personnage p4 = new Elfe("Legolas",19,19,19,20);
	
	
		System.out.println(p3);
		System.out.println(p4);
		
		
		Equipe e1 = new Equipe("R&T Killers");
		try {
			e1.addMembre(gahauss);
			e1.addMembre(idhril);
			e1.addMembre(p3);
			e1.addMembre(new Elfe("Isildur",12,15,20,17));
			e1.addMembre(p4);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(e1);
		
		try {
			e1.save("C:\\Users\\Aurélien\\OneDrive\\Documents\\Bureau/equipe.txt");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		Equipe nouvelle_equipe = null;
		try {
			nouvelle_equipe = Equipe.load("C:\\\\Users\\\\Aurélien\\\\OneDrive\\\\Documents\\\\Bureau/equipe2.txt");
		} catch (FullTeamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(nouvelle_equipe);
	}
}