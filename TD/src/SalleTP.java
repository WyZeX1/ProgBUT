
public class SalleTP extends Salle {
	
	public final static int DEFAULT_POSTE_NUMBER = 0;
	private int nbPoste;
	
	public SalleTP(String nom) {
		super(nom);
		this.nbPoste = SalleTP.DEFAULT_POSTE_NUMBER;
	}
	
	public SalleTP(String nom, int t1p, int t2p, int c, int nbPoste) {
		super(nom,t1p,t2p,c);
		this.setNbPoste(nbPoste);
	}
	
	public int getNbPoste() {
		return this.nbPoste;
	}

	public void setNbPoste(int nbPoste) {
		this.nbPoste = nbPoste;
	}
	
	public String toString()
	{
		return super.toString()+", "+this.nbPoste+" poste(s)";
				
	}
	
	public int capacite() {
    	return Math.min(super.capacite(),this.nbPoste);
    }
	
	public boolean estBienConfiguree() 
	{
		return super.estBienConfiguree()&&this.nbPoste==super.capacite();
	}
}
