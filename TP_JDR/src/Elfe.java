
public class Elfe extends Personnage {

	private int charisme;
	
	public Elfe(String nom) {
		super(nom);
		this.charisme=Personnage.DEFAULT_ATTRIBUTE_VALUE;
	}
	
	public Elfe(String nom, int force, int endurance, int intelligence, int charisme) {
		super(nom,force,endurance,intelligence);
		this.charisme=charisme;
	}
	
	public String toString() {
		return "[ELFE] "+super.toString()+" CHA="+this.charisme;
	}
	
	public int getCharisme() {
		return this.charisme;
	}
	
	public void setCharisme(int charisme) {
		this.charisme=charisme;
	}
	
	public boolean isChampion() {
		return super.isChampion() || this.charisme==Personnage.DEFAULT_ATTRIBUTE_MAX_VALUE;
	}
}
