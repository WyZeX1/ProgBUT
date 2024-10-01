import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Equipe {

	private String nom;

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
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addMembre(Personnage perso) throws FullTeamException {
		if (this.equipe.size() >= Equipe.MAX_MEMBERS) throw new FullTeamException(this,perso);
			this.equipe.add(perso);
	}
	
	public void save(String fileName) {
		try {
			FileWriter fos = new FileWriter(fileName);
			PrintWriter p = new PrintWriter(fos,true); 

			String res = this.nom+"\n";
			for (Personnage perso : this.equipe) {
				boolean isNain = (perso instanceof Nain);
				String str_perso = (isNain ? "NAIN:" : "ELFE:");
				str_perso +=perso.getNom()+":"+perso.getForce()+":"+perso.getEndurance()+":"+perso.getIntelligence();
				if (isNain) 
					str_perso +=":"+((Nain) perso).getEndAlcool();
				else 
					str_perso +=":"+((Elfe) perso).getCharisme();
				res+=str_perso+"\n";
				
			}
			p.println(res);	//écriture avec retour-chariot
			fos.close();

		 	} catch(Exception e) {
			 e.printStackTrace();
		 }
	}

	public static Equipe load(String fileName) throws FullTeamException
	{

		Equipe eq =null;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String s;
		
			s=in.readLine();	// 1ere ligne du fichier texte = nom equipe
			eq=new Equipe(s);
			while((s = in.readLine()) != null)
				{
					// s correspond aux lignes suivantes : 
				    //signature d'un personnage
					//ELFE:Galadrielle:14:12:2:18
					String[] tab=s.split(":");
					boolean isNain = (tab[0].equals("NAIN"));
					String nom = tab[1];
					int force = Integer.valueOf(tab[2]);
					int endurance = Integer.valueOf(tab[3]);
					int intelligence = Integer.valueOf(tab[4]);
					int attrib_special = Integer.valueOf(tab[5]);
					
					if(isNain)
						eq.addMembre(new Nain(nom,force,endurance,intelligence,attrib_special));
					else
						eq.addMembre(new Elfe(nom,force,endurance,intelligence,attrib_special));
				}
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eq;
	}
	
}
