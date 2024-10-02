
public class CoupleCoureurTemps {

	public Coureur coureur;
	private long temps;
	
	public CoupleCoureurTemps(Coureur c,long t) {
		this.coureur=c;
		this.temps=t;
	}
	
	public String toString() {
		return coureur.toString()+" ("+CoupleCoureurTemps.msToHHMMSSCCC(temps)+")";
	}
	
	public static String msToHHMMSSCCC(long ms)
	{

		long dif = ms/1000;
		long difNoMS = dif * 1000;

		int i;

		//HH
		i=(int)(dif/3600);
		String h = new String(""+i);
		if (h.length()==1) h = (new String("0")).concat(h);
		dif = dif - i * 3600;

		//MM
		i=(int)(dif/60);
		String m = new String(""+i);
		if (m.length()==1) m = (new String("0")).concat(m);
		dif = dif - i * 60;

		// SS
		String s = new String(""+(int)dif);
		if (s.length()==1) s = (new String("0")).concat(s);

		// CC
		String c = new String(""+(int)(ms-difNoMS));
		if (c.length()==1) c = (new String("00")).concat(s);
		if (c.length()==2) c = (new String("0")).concat(s);

		return h.concat("h").concat(m).concat("m").concat(s).concat("s").concat(c).concat("ms");
	}
	
}
