import java.util.GregorianCalendar;
import java.util.HashMap;

public class Classement {

	private HashMap<Integer,Coureur> classement;
	private GregorianCalendar debutCourse;
	
	public Classement() {
		this.classement = new HashMap<Integer,Coureur>(); 
	}
	
	public synchronized void arrive(Coureur c) {
		this.classement.put(1+this.classement.size(),c);
	}
	
	public String toString() {
		String res = "Classement de la course ("+this.classement.size()+" participants)";
		res +="--------------------------------------------------------------";
		for (int i=1; i<this.classement.size();i++) {
			res += i+". "+this.classement.get(i)+"\n";
		}
		return res;
	}
}
