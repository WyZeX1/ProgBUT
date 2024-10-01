
public class Coureur extends Thread {

	private String nom,prenom;
	
	public Coureur(String nom, String prenom, Course c) {
		this.nom=nom;
		this.prenom=prenom;
	}

	public void run() {
		for (int i=1; i<=10;i++) {
			try {
			Thread.sleep((long)(Math.random()*1000)+1000);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println((i*10)+"m par "+this.nom+" "+this.prenom);
		}
		System.out.println(this.nom+" "+this.prenom+" a fini la course !");
		Course.affichePosition(this);
	}
	
	public String toString() {
		return this.nom+" "+this.prenom;
	}
}
