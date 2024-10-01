
public abstract class Personnage {

	private String nom;
	private int force,endurance,intelligence;
	final static public int DEFAULT_ATTRIBUTE_VALUE = 8;
	final static public int DEFAULT_ATTRIBUTE_MAX_VALUE = 20;
	
	public Personnage(String nom) {
		/*
		this.nom=nom;
		this.force=Personnage.DEFAULT_ATTRIBUTE_VALUE;
		this.endurance=Personnage.DEFAULT_ATTRIBUTE_VALUE;
		this.intelligence=Personnage.DEFAULT_ATTRIBUTE_VALUE;
		*/
		this(nom,Personnage.DEFAULT_ATTRIBUTE_VALUE,Personnage.DEFAULT_ATTRIBUTE_VALUE,Personnage.DEFAULT_ATTRIBUTE_VALUE);
	}
	
	public Personnage(String nom, int force, int endurance, int intelligence) {
		this.nom=nom;
		this.force=force;
		this.endurance=endurance;
		this.intelligence=intelligence;
	}
	
	public String toString() {
		return "NOM="+this.nom+(this.isChampion() ? "*" : "")+" FOR="+this.force+" END="+this.endurance+" INT="+this.intelligence;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom) {
		this.nom=nom;
	}
	
	public int getForce() {
		return this.force;
	}
	
	public void setForce(int frc) {
		this.force=frc;
	}
	
	public int getEndurance() {
		return this.endurance;
	}
	
	public void setEndurance(int endurance) {
		this.endurance=endurance;
	}
	
	public int getIntelligence() {
		return this.intelligence;
	}
	
	public void setIntelligence(int intelligence) {
		this.intelligence=intelligence;
	}
	
	public boolean isChampion() {
		return this.force == Personnage.DEFAULT_ATTRIBUTE_MAX_VALUE || this.endurance == Personnage.DEFAULT_ATTRIBUTE_MAX_VALUE || this.intelligence == Personnage.DEFAULT_ATTRIBUTE_MAX_VALUE;
	}
}
