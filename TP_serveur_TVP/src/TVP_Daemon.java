import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TVP_Daemon {

	public static String RC;
	public static int DEFAULT_WORKING_PORT = 10000;
	private String repCourant;
	public static boolean DEBUG = true;
	private final static String directory = "C:\\Users\\Aurélien\\OneDrive\\Documents\\Bureau\\Serv_TVP";
	
	public TVP_Daemon() {
		this.repCourant = new File(".").getAbsolutePath();
		if (this.getOS()==OS.WINDOWS) 
			TVP_Daemon.RC="\r\n";
		else
			TVP_Daemon.RC="\n";
	}
	
	public void serverRun() {
		this.serverRun(DEFAULT_WORKING_PORT);
	}
	
	/*
	public void serverRun(int port)
	{
		System.out.println("\n----\nOS : \n"+this.getOS());
		System.out.println("\n----\nRépertoire courant : \n"+pwdToString());
		System.out.println("\n----\nContenu du répertoire : \n"+lsToString());
		System.out.println("\n----\nOn se déplace d'un répertoire en arrière : \n"+pwdToString());
		System.out.println("\n----\nContenu du répertoire : \n"+lsToString());
		System.out.println("\n----\nContenu du fichier TP_course.txt : \n"+viewToString(TVP_Daemon.directory+"/TP_course.txt"));
		
	}*/
	
	public void serverRun(int port)
	{
		ServerSocket srvSocket = null;
		boolean fini = false;
		try 
		{
			if (DEBUG) System.out.println("Ecoute sur le port "+port);
			srvSocket = new ServerSocket (port) ;

			if (DEBUG) System.out.println("Attente de connexion...");
			Socket service  = srvSocket.accept() ;
			if (DEBUG) System.out.println("Serveur connect� avec "+service.getInetAddress().getHostAddress()+":"+service.getPort());

			BufferedReader  sr = new BufferedReader (new InputStreamReader (service.getInputStream ()));
			PrintStream   sw = new PrintStream (service.getOutputStream ());


			while(!fini)
			{
				String requete = sr.readLine();
				String[] arrayRequete = requete.split(" ");

				if (arrayRequete.length>0)
				{
					// commande ls
					if(arrayRequete[0].equals("ls"))
					{
						sw.println(this.lsToString());
						sw.flush();
					}
					// commande pwd
					else if (arrayRequete[0].equals("pwd"))
					{
						sw.println(this.pwdToString());
						sw.flush();
					}
					// commande cd
					else if (arrayRequete[0].equals("cd"))
					{
						try
						{
							this.cd(arrayRequete[1]);
							sw.println(this.pwdToString());
							sw.flush();
						}
						catch(Exception e)
						{
							if(arrayRequete.length==1)
								sw.println("Erreur dans le traitement de la commande cd : il manque un param�tre");
							else
								sw.println(e.toString());

							sw.flush();
						}
					}
					// commande cat, view, more
					else if (arrayRequete[0].equals("cat") || arrayRequete[0].equals("view") || arrayRequete[0].equals("more"))
					{
						if(arrayRequete.length>1)
							sw.println(this.viewToString(this.repCourant+"\\"+arrayRequete[1]));
						else
							sw.println("Vous devez pr�ciser le nom du fichier en param�tre");
						sw.flush();

					}
					// commande quit, exit, bye
					else if (arrayRequete[0].equals("quit") || arrayRequete[0].equals("exit") || arrayRequete[0].equals("bye"))
					{
						fini=true;
					}
					else
					{
						System.out.println("Commande non comprise <<"+requete+" "+">>");
					}
				}
			}
			sr.close();
			sw.close();
			srvSocket.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String pwdToString()
	{
		return new File(this.repCourant).getAbsolutePath();
	}
	
	public String lsToString() {
		return lsToString(true);
	}
	
	public String lsToString(boolean relative) {
		File f=new File(this.repCourant);

		String[] lstFile = f.list();
		String res="";
		for(int i=0;i<lstFile.length;i++) res+=(relative ? "" : pwdToString())+lstFile[i]+RC;

		return res;
	}
	
	public String viewToString(String fileName) {
		String res = "";

		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(new File(fileName)));
			String s = new String();
			while((s = in.readLine()) != null)
				res+=s+TVP_Daemon.RC;;
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Le fichier "+fileName+" n'a pas été trouvé";
		} catch (IOException e) {
			return "Ouverture du fichier "+fileName+" impossible!";
		}
		return res;
	}
	
	public void cd(String res) throws Exception
	{
		try 
		{
			this.repCourant = new File(this.repCourant,res).getCanonicalPath();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			this.repCourant = new File(res).getCanonicalPath();
		}
	}
	
	public OS getOS()
	{
		String libelleOS = System.getProperty("os.name").toLowerCase();

		// un peu violent
		if (libelleOS.indexOf("win") >= 0) 
			return OS.WINDOWS;
		else if (libelleOS.indexOf("mac") >= 0) 
			return OS.MACOS;
		else if (libelleOS.indexOf("nux") >= 0) 
			return OS.LINUX;
		else if (libelleOS.indexOf("sunos") >= 0) 
			return OS.SOLARIS;
		else 
			return OS.AUTRE;
	}

}
