
public class SalleCreativite {

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
	}
}
