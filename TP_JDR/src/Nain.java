
public class Nain extends Personnage {

	private int enduranceAlcool;
	
	public Nain(String nom) {
		super(nom);
		this.enduranceAlcool=Personnage.DEFAULT_ATTRIBUTE_VALUE;
	}
	
	public Nain(String nom, int force, int endurance, int intelligence, int enduranceAlcool) {
		super(nom,force,endurance,intelligence);
		this.enduranceAlcool=enduranceAlcool;
	}
	
	public String toString() {
		return "[NAIN] "+super.toString()+" END_ALCOOL="+this.enduranceAlcool;
	}
	
	public int getEndAlcool() {
		return this.enduranceAlcool;
	}

	public void setEndAlcool(int enduranceAlcool) {
		this.enduranceAlcool = enduranceAlcool;
	}

	public boolean isChampion() {
		return super.isChampion() || this.enduranceAlcool == Personnage.DEFAULT_ATTRIBUTE_MAX_VALUE;
	}
}
