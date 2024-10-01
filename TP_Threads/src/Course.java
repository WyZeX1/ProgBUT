import java.util.ArrayList;

public class Course {

	private ArrayList<Coureur> course;
	
	public Course() {
		this.course = new ArrayList<Coureur>();
	}
	
	public void add(String nom, String prenom) {
		this.add(new Coureur(nom,prenom,this));
	}
	
	public void add(Coureur c) {
		this.course.add(c);
	}
	
	public synchronized void gogogo() {
		for (Coureur c : this.course) c.start();
	}	
	
	/*
	public synchronized static void affichePosition(Coureur c) {
		System.out.print(c+" arrive en "+Course.position+"éme position... ");
		System.out.print("Pour "+c+"...");
		try {Thread.sleep(500);} catch (InterruptedException e){e.printStackTrace();}
		System.out.println("Les hip hip HOURRA!");
		Course.position++;
	}
	*/
}