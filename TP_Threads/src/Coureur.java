
public class Coureur extends Thread {

	private String nom,prenom;
	private Course course;
	
	public Coureur(String nom, String prenom, Course c) {
		this.nom=nom;
		this.prenom=prenom;
		this.course=c;
	}

	public void run() {
		for (int i=1; i<=10;i++) {
			try {
				//if (!this.nom.equals("JAMONT")) Thread.sleep(  1000 + (long) (Math.random()*1000) );
				Thread.sleep(1000 + (long) (Math.random()*1000));
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println((i*10)+"m par "+this.nom+" "+this.prenom);
		}
		System.out.println(this.nom+" "+this.prenom+" a fini la course en "+(this.course.getClassement().tempsEcouleDepuisDebutCourse()/1000.0)+"s !");
		//Course.affichePosition(this);
		this.course.arrive(this);
	}
	
	public String toString() {
		return this.nom+" "+this.prenom;
	}
}
