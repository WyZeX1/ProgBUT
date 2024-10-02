public class TestCourse {

	public static void main(String[] args)
	{
    	Course c = new Course();
    	c.add("AMBLARD", "Marion");
    	c.add("BENEFICE", "Claire");
    	c.add("BERGERON", "Karine");
    	c.add("COULET", "Louis");
    	c.add("DUCCINI", "Christian");
     	c.add("FOURTY", "Nicolas");
    	c.add("GENON-CATALOT", "Denis");
    	c.add("JAMONT", "Jean-Paul");
    	c.add("LIEVIN", "Romain");
    	c.add("MICHEL", "Frederic");
    	c.add("MOLLIEX", "Raphaël");
	
    	
    	c.gogogo();
    	c.getClassement().debutCourse();
    	
    	System.out.println("*************************");

    	try {
			Thread.sleep(20000); // Attendre la durée maximale de la course avant d'afficher le classement
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	c.getClassement().affiche();
    	
    	System.out.println("Est arrivé premier : "+c.getClassement().vainqueur());
    	
    	c.getClassement().toFile("C:\\Users\\Aurélien\\OneDrive\\Documents\\Bureau/TP_course.txt");
	}
}