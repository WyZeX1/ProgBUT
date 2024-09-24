
public class FullTeamException extends Exception{

	private Equipe equipe;
	private Personnage perso;
	
	public FullTeamException(Equipe e,Personnage p) {
		this.equipe=e;
		this.perso=p;	
	}
	
	public String toString() {
		return "Team "+this.equipe.getNom()+" is already full, "+this.perso.getNom()+" cannot be added.";
	}
}

