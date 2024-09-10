public class Salle {

    private String nom;
    private int nbTable1p,nbTable2p,nbChaise;

    public Salle(String nom, int nbTable1p, int nbTable2p, int nbChaise) 
    {
        this.nom = nom;
        this.nbTable1p = nbTable1p;
        this.nbTable2p = nbTable2p;
        this.nbChaise = nbChaise;

    }

    public Salle(String nom) 
    {
        this.nom = nom;
        this.nbTable1p = 0;
        this.nbTable2p = 0;
        this.nbChaise = 0;

    }
    public Salle(String nom, int nbChaise) {
        this.nom = nom;
        this.nbChaise = nbChaise;
        this.nbTable1p = 0;
        this.nbTable2p = 0;
    }

    public String toString() 
    {
    	//if (this.estBienConfiguree()) {
    		//return "Salle "+this.nom+" bien configurée : " + this.nbChaise+ " chaise(s) " +(this.nbTable1p + this.nbTable2p) + " table(s) ";
    	//} else {
    		//return "Salle "+this.nom + " mal configurée : " + this.nbChaise+ " chaise(s) " +(this.nbTable1p + this.nbTable2p) + " table(s) ";
    	//}
    	// mieux ↓
    	return "Salle "+this.nom+(this.estBienConfiguree() ? " bien configurée" : " mal configurée")+" : "+this.nbChaise+ " chaise(s) " +(this.nbTable1p + this.nbTable2p) + " table(s) ";
    }

    public int getNbchaise() {
        return this.nbChaise;
    }

    public int getNbtable() {
        return (this.nbTable1p + this.nbTable2p);
    }

    public void setNbChaise(int nbChaise) {
        this.nbChaise = nbChaise;
    }

    public void setNbTable(int nbTable) {
        this.nbTable1p = nbTable;
        this.nbTable2p = nbTable;
    }
    
    public int capacite() {
    	return Math.min(this.nbChaise,this.nbTable1p+this.nbTable2p*2);
    }
    
    public boolean estBienConfiguree() {
    	return this.nbChaise == (this.nbTable1p+2*this.nbTable2p);
    }
    
}	