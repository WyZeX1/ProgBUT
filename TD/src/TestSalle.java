public class TestSalle {
	
    public static void main(String[] args)
    {
        System.out.println("Test du TD Salle\n\n");

        Salle s1 = new Salle("A001");
        Salle s2 = new Salle("D106",2,8,18);
        Salle s3 = new Salle("C102",6,6,12);
        Salle s4 = new Salle("C113",36);

        SalleTP s5 = new SalleTP("C113-A",16,0,8,16);
        SalleTP s6 = new SalleTP("C113-B",16,0,8,14);
        
        System.out.println("S1 : "+s1.toString());
        System.out.println("capacité de S1 : "+s1.capacite());
        
        System.out.println("\nS2 : "+s2.toString());
        System.out.println("capacité de S2 : "+s2.capacite());
        
        System.out.println("\nS3 : "+s3.toString());
        System.out.println("capacité de S3 : "+s3.capacite());
        
        System.out.println("\nS4 : "+s4.toString());
        System.out.println("capacité de S4 : "+s4.capacite());
        
        System.out.println("\nS5 : "+s5.toString());
        System.out.println("S6 : "+s6.toString());
    }
}