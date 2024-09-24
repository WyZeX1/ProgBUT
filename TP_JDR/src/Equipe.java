import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Equipe {

	private String nom;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	private ArrayList<Personnage> equipe;
	public final static int MAX_MEMBERS = 4;
	
	public Equipe(String nom) {
		this.nom=nom;
		this.equipe = new ArrayList<Personnage>();
	}
	
	public String toString() {
		String res = "\nEquipe : ---"+this.nom+"---\n";
		for (Personnage p : this.equipe) {
			res+=p.toString()+"\n";
		}
		return res;
	}

	public void addMembre(Personnage perso) throws FullTeamException {
		if (this.equipe.size() >= Equipe.MAX_MEMBERS) throw new FullTeamException(this,perso);
			this.equipe.add(perso);
	}
	
	public void save(String fileName) {
		try{
			FileWriter fos = new FileWriter(fileName);
			PrintWriter p = new PrintWriter(fos,true); 

			String s = "Je suis une cha�ne";
			
			p.println(s);	//écriture avec retour-chariot
			
			fos.close();

		 } catch(Exception e)
		{
			 e.printStackTrace();
		}
		
	}
	
}
