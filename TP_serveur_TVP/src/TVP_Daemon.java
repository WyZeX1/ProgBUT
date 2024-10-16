import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TVP_Daemon {

	public static String RC;
	public static int DEFAULT_WORKING_PORT = 10000;
	public static boolean DEBUG = true;

	public TVP_Daemon() {
		new File(".").getAbsolutePath();
		if (this.getOS()==OS.WINDOWS) 
			TVP_Daemon.RC="\r\n";
		else
			TVP_Daemon.RC="\n";
	}

	public void serverRun() {
		this.serverRun(DEFAULT_WORKING_PORT);
	}

	public void serverRun(int port)
	{
		ServerSocket srvSocket = null;
		boolean fini = false;
		try 
		{
			if (DEBUG) System.out.println("Ecoute sur le port "+port);
			srvSocket = new ServerSocket (port) ;

			while(true)
			{
			if (DEBUG) System.out.println("Attente de connexion...");
			Socket service  = srvSocket.accept() ;
			if (DEBUG) System.out.println("Serveur connect� avec "+service.getInetAddress().getHostAddress()+":"+service.getPort());
			
			(new TVPserveurThread(service)).start();
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
