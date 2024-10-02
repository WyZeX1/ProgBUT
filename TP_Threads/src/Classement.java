import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Classement {

	private HashMap<Integer,CoupleCoureurTemps> ranking;
	private GregorianCalendar debutCourse;
	
	public Classement() {
		this.ranking = new HashMap<Integer,CoupleCoureurTemps>();
	}
	
	public void debutCourse() {
		this.debutCourse = new GregorianCalendar();
	}
	
	public long tempsEcouleDepuisDebutCourse() {
		return (new GregorianCalendar()).getTimeInMillis() - this.debutCourse.getTimeInMillis();
	}
	
	public synchronized void arrive(Coureur c) {
		this.ranking.put(this.ranking.size()+1, new CoupleCoureurTemps(c,this.tempsEcouleDepuisDebutCourse()));
	}
	
	public Coureur vainqueur()
	{
		return this.ranking.get(1).coureur;
	}
	
	public void affiche() {
		System.out.println(this.toString());
	}
	
	public String toString() {
		String res = "\nClassement de la course ("+this.ranking.size()+" participants)";
		res +="\n--------------------------------------------------------------\n";
		for (int i=1; i<=this.ranking.size();i++) {
			res += i+". "+this.ranking.get(i)+"\n";
		}
		return res;
	}
	
	public void toFile (String fileName) {
		try{
			FileWriter fos = new FileWriter(fileName);
			PrintWriter p = new PrintWriter(fos,true); 

			p.println(this.toString());	// écriture avec retour-chariot
			p.close();
			fos.close();
			
		 } catch(IOException e) {}
	}
}