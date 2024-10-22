import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


/**
 * Serveur Text Viewer Protocol
 * @author jamontj
 *
 */
public class MSNRTServerThread extends Thread
{	

	public final static int PARAMETER_NUMBER_OPEN = 2;
	public final static int PARAMETER_NUMBER_CLOSE = 0;
	public final static int PARAMETER_NUMBER_SENDB = 1;
	public final static int PARAMETER_NUMBER_SEND = 2;
	public final static int PARAMETER_NUMBER_PSEUDOS = 0;
	public final static int PARAMETER_NUMBER_CONNECTED = 1;

	/** pour gérer la compatibilit� du retour chariot WINDOWS/LINUX */
	public static String RC;

	private MSNRTClientManager authentifier;
	private MSNRTMessageDispatcher dispatcher;

	private Session session;

	/** le tuyau de communication avec le client */
	private Socket service;

	/**
	 * Initialisation du serveur MSNRT
	 */
	public MSNRTServerThread  (Socket socket, MSNRTClientManager authentifier,  MSNRTMessageDispatcher dispatcher)
	{
		this.service=socket;
		this.authentifier=authentifier;
		this.dispatcher=dispatcher;
		this.session=new Session();
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
			if (MSNRTServer.DEBUG) System.out.println("Serveur thread connect� avec "+service.getInetAddress().getHostAddress()+":"+service.getPort());

			BufferedReader  sr = new BufferedReader (new InputStreamReader (service.getInputStream ()));
			PrintStream   sw = new PrintStream (service.getOutputStream ());


			while(!fini)
			{
				String requete = sr.readLine();
				String[] arrayRequete = requete.split(" ");
				if (arrayRequete.length>0)
				{
					if (arrayRequete[0].equals("open")) 
					{
						if(arrayRequete.length == 1+PARAMETER_NUMBER_OPEN)
						{
							this.session.login=arrayRequete[1];

							// j'ai ce qu'il faut pour authentifier l'utilisateur
							try
							{
								this.session.pseudo = this.authentifier.register(arrayRequete[1], arrayRequete[2]);
								MSNRTServer.DEBUG("AUTHORIZED");
								sw.println("AUTHORIZED");
								this.session.authorized=true;
							}
							catch(UnauthorizedUserException e)
							{
								MSNRTServer.DEBUG("UNAUTHORIZED");
								sw.println("UNAUTHORIZED");
								this.session.authorized=false;
							}
						}
						else
						{
							// la commande a �t� mal utilisée
							MSNRTServer.DEBUG("Commande open mal utilisée ("+requete+")");
							sw.println("Commande open mal utilisée ("+requete+")");
						}
					}
					// commande quit, exit, bye
					else if (arrayRequete[0].equals("quit") || arrayRequete[0].equals("exit") || arrayRequete[0].equals("bye"))
					{
						sw.println("Bye !!");
						fini=true;
					}
					else if (this.session.authorized)
					{
						// commande sendb
						if(arrayRequete[0].equals("sendb"))
						{
							if(arrayRequete.length >= 1+PARAMETER_NUMBER_SENDB)
							{
								String msg="";
								for(int i=1;i<arrayRequete.length;i++)
									msg+=arrayRequete[i]+" ";
								// j'ai ce qu'il faut pour authentifier l'utilisateur
								this.dispatcher.broadcastMessage(this.session.pseudo,msg);
							}
							else
							{
								// la commande a �t� mal utilis�e
								MSNRTServer.DEBUG("Commande sendb mal utilisée ("+requete+")");
								sw.println("Commande sendb mal utilisée ("+requete+")");
							}
						}
						else if(arrayRequete[0].equals("close"))
						{
							if(arrayRequete.length == 1+PARAMETER_NUMBER_CLOSE)
							{
								// j'ai ce qu'il faut pour authentifier l'utilisateur
								this.authentifier.unregister(this.session.login);
								this.session.authorized=false;
								sw.println("Vous êtes déconnecté");
							}
							else
							{
								// la commande a été mal utilisée
								MSNRTServer.DEBUG("Commande close mal utilisée ("+requete+")");
								sw.println("Commande close mal utilisée ("+requete+")");
							}
						}
						// commande sendb
						else if(arrayRequete[0].equals("sendb"))
						{
							if(arrayRequete.length >= 1+PARAMETER_NUMBER_SENDB)
							{
								// On reconstitue le message (à améliorer)
								String msg="";
								for(int i=1;i<arrayRequete.length;i++)
									msg+=arrayRequete[i]+" ";


								// j'ai ce qu'il faut pour authentifier l'utilisateur
								this.dispatcher.broadcastMessage(this.session.pseudo,msg);
							}
							else
							{
								// la commande a �t� mal utilis�e
								MSNRTServer.DEBUG("Commande sendb mal utilisée ("+requete+")");
								sw.println("Commande sendb mal utilisée ("+requete+")");
							}

						}
						else if(arrayRequete[0].equals("send"))
						{
							if(arrayRequete.length >= 1+PARAMETER_NUMBER_SEND)
							{
								// On reconstitue le message (à améliorer)
								String msg="";
								for(int i=2;i<arrayRequete.length;i++)
									msg+=arrayRequete[i]+" ";

								// j'ai ce qu'il faut pour authentifier l'utilisateur
								this.dispatcher.dispatchPrivateMessage(this.session.pseudo,arrayRequete[1], msg);
							}
							else
							{
								// la coté mal utilisée
								MSNRTServer.DEBUG("Commande send mal utilisée ("+requete+")");
								sw.println("Commande send mal utilisée ("+requete+")");
							}

						}
						else if(arrayRequete[0].equals("pseudos"))
						{
							if(arrayRequete.length == 1+PARAMETER_NUMBER_PSEUDOS)
							{
								String res="";
								for (String s : this.authentifier.registeredUsers()) {
									res += s+",";
								}
								sw.println(res);
							}
							else
							{
								// la commande a été mal utilisée
								MSNRTServer.DEBUG("Commande pseudos mal utilisée ("+requete+")");
								sw.println("Commande pseudos mal utilisée ("+requete+")");
							}
						}
						else if(arrayRequete[0].equals("connected"))
						{
							if(arrayRequete.length == 1+PARAMETER_NUMBER_PSEUDOS)
							{
								boolean estConnecte = false; 
								for (String s : this.authentifier.registeredUsers()) 
									if (s.equals(arrayRequete[1])) estConnecte = true;
								sw.println("L'utilisateur "+arrayRequete[1]+(estConnecte ? "est connecté" : "n'est pas connecté"));
							}
							else
							{
								// la commande a été mal utilisée
								MSNRTServer.DEBUG("Commande connected mal utilisée ("+requete+")");
								sw.println("Commande connected mal utilisée ("+requete+")");
							}
						}
					}
					else
					{
						MSNRTServer.DEBUG("Commande non comprise <<"+requete+" "+">>");
						sw.println("Commande non comprise <<"+requete+" "+">>");
					}
				}
			}
			sr.close();
			sw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}