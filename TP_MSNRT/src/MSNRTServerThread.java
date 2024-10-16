import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MSNRTServerThread extends Thread
{	
	
	public static boolean DEBUG = true;
	
	/** pour gérer la compatibilité du retour chariot WINDOWS/LINUX */
	public static String RC;

	/** le tuyau de communication avec le client */
	private Socket service;

	/**
	 * Initialisation du serveur MSNRT
	 */
	public MSNRTServerThread(Socket service)
	{
		this.service=service;
	}

	/** 
	 * On lance le serveur en écoutant sur le port passé en paramètre
	 * @param port port d'écoute
	 */
	public void run()
	{
		boolean fini = false;
		try 
		{
			if (DEBUG) System.out.println("Serveur thread connect� avec "+service.getInetAddress().getHostAddress()+":"+service.getPort());

			BufferedReader  sr = new BufferedReader (new InputStreamReader (service.getInputStream ()));
			PrintStream   sw = new PrintStream (service.getOutputStream ());

			while(!fini)
			{
				String requete = sr.readLine();
				String[] arrayRequete = requete.split(" ");
				if (arrayRequete.length>0)
				{
					// commande quit, exit, bye
					if (arrayRequete[0].equals("quit") || arrayRequete[0].equals("exit") || arrayRequete[0].equals("bye"))
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
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
