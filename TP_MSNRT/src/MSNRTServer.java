import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MSNRTServer 
{
	/** port par défaut s'il n'est pas précisé */
	public static int DEFAULT_WORKING_PORT = 10000;

	/** Pour afficher des traces de debuggage */
	public static boolean DEBUG = true;

	/**
	 * Initialisation du serveur TVP
	 */
	public MSNRTServer()
	{
	}

	/** 
	 * On lance le serveur en écoutant sur le port par défaut
	 */
	public void serverRun()
	{
		this.serverRun(DEFAULT_WORKING_PORT);
	}

	/** 
	 * On lance le serveur en écoutant sur le port passé en paramètre
	 * @param port port d'écoute
	 */
	public void serverRun(int port)
	{
		ServerSocket srvSocket = null;
		try 
		{
			if (DEBUG) System.out.println("Ecoute sur le port "+port);
			srvSocket = new ServerSocket (port) ;

			while(true)
			{
				if (DEBUG) System.out.println("Attente de connexion...");
				Socket service  = srvSocket.accept() ;
				if (DEBUG) System.out.println("Serveur connect� avec "+service.getInetAddress().getHostAddress()+":"+service.getPort());

				(new MSNRTServerThread(service)).start();
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
