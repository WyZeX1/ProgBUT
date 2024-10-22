import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class MSNRTServer implements MSNRTMessageDispatcher,MSNRTClientManager
{
	/** port par défaut s'il n'est pas précisé */
	public static int DEFAULT_WORKING_PORT = 10000;

	/** Pour afficher des traces de debuggage */
	public static boolean DEBUG = true;

	public LinkedList<String> registeredUsers;
	/**
	 * Initialisation du serveur TVP
	 */
	public MSNRTServer()
	{
		this.registeredUsers = new LinkedList<String>();
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
				DEBUG("Attente de connexion...");
				Socket service  = srvSocket.accept() ;
				DEBUG("Serveur connecté avec "+service.getInetAddress().getHostAddress()+":"+service.getPort());

				(new MSNRTServerThread(service,this,this)).start();
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String register(String login, String passwd) throws UnauthorizedUserException {
		if (login.isEmpty())
			throw new UnauthorizedUserException(login);
		
		for (String l : this.registeredUsers)
			if (l.equals(login)) throw new UnauthorizedUserException(login);
		
		this.registeredUsers.add(login);
		return login;
	}

	@Override
	public void unregister(String login) {
		DEBUG("Client "+login+" à quitté le chat");
	}

	@Override
	public String[] registeredUsers() {
		String[] res = new String[this.registeredUsers.size()];
		for(int i=0;i<this.registeredUsers.size();i++)
				res[i]=this.registeredUsers.get(i);
		return res;
	}

	@Override
	public void broadcastMessage(String sender_pseudo, String msg) {
		DEBUG("["+sender_pseudo+" → all] "+msg);
	}

	@Override
	public void dispatchPrivateMessage(String sender_pseudo, String receiver_pseudo, String msg) {
		DEBUG("["+sender_pseudo+" → "+sender_pseudo+"] "+msg);
	}

	public static void DEBUG(String string) {
		if (DEBUG) System.out.println(string);		
	}
}
