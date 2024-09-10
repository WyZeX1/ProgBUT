
public class SalleCreativite extends Salle {

	private boolean babyfoot,machineCafe;
	private int nbLit;
	public static final int DEFAULT_NB_LIT = 0;
	public static final boolean DEFAULT_BOOL = false;
	
	public SalleCreativite(String nom)
	{
		super(nom);
		this.nbLit=DEFAULT_NB_LIT;
		this.babyfoot=DEFAULT_BOOL;
		this.machineCafe=DEFAULT_BOOL;
	}
	
	public SalleCreativite(String nom, int t1p, int t2p, int c, int lit)
	{
		super(nom,t1p,t2p,c);
		this.nbLit=lit;
		this.babyfoot=DEFAULT_BOOL;
		this.machineCafe=DEFAULT_BOOL;
	}
	
	public void setNbLit(int lit) {
		this.nbLit = lit;
	}
	
	public int getNbLit() {
		return this.nbLit;
	}
	
	public void setMachineCafe(boolean machine) {
		this.machineCafe = machine;
	}
	
	public boolean getMachineCafe() {
		return this.machineCafe;
	}
	
	public void setBabyfoot(boolean baby) {
		this.babyfoot = baby;
	}
	
	public boolean getBabyfoot() {
		return this.babyfoot;
	}
	
	public String toString() {
		return "Salle de créativité "+super.toString()+", "+this.nbLit+" lit(s), "+(this.machineCafe ? "une machine à café" : "pas de machine à café");
	}
}
