
public class Personnage {

	private String nom;
	private int force,endurance,intelligence;
	private int DEFAULT_ATTRIBUTE_VALUE = 8;
	
	public Personnage(String nom) {
		this.nom=nom;
		this.force=DEFAULT_ATTRIBUTE_VALUE;
		this.endurance=DEFAULT_ATTRIBUTE_VALUE;
		this.intelligence=DEFAULT_ATTRIBUTE_VALUE;
	}
	
	public Personnage(String nom, int force, int endurance, int intelligence) {
		this.nom=nom;
		this.force=force;
		this.endurance=endurance;
		this.intelligence=intelligence;
	}
	
	public String toString() {
		return "NOM="+this.nom+" FOR="+this.force+" END="+this.endurance+" INT="+this.intelligence;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getForce() {
		return this.force;
	}
	
	public int getEndurance() {
		return this.endurance;
	}
	
	public int getIntelligence() {
		return this.intelligence;
	}
	
	public boolean isChampion() {
		if (this.force == 20 || this.endurance == 20 || this.intelligence == 20) {
			return true;
		}
		return false;
	}
}
