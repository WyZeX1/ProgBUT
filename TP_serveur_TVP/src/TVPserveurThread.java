import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TVPserveurThread extends Thread{

	public static String RC;
	private String repCourant;
	public static boolean DEBUG = true;
	private Socket service;
	
	public TVPserveurThread(Socket service) {
		this.repCourant = new File(".").getAbsolutePath();
		if (this.getOS()==OS.WINDOWS) 
			TVP_Daemon.RC="\r\n";
		else
			TVP_Daemon.RC="\n";
		this.service=service;
	}


	public void run()
	{
		boolean fini = false;
		try 
		{

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
								sw.println("Erreur dans le traitement de la commande cd : il manque un paramètre");
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
							sw.println("Vous devez préciser le nom du fichier en paramètre");
						sw.flush();
					}
					//commande help, ?
					else if (arrayRequete[0].equals("help") || arrayRequete[0].equals("?")) 
					{
						sw.println(this.help());
						sw.flush();
					}
					//commande rename, mv
					else if (arrayRequete[0].equals("rename") || arrayRequete[0].equals("mv"))
					{
						if(arrayRequete.length>2)
							this.rename(arrayRequete[1],arrayRequete[2]);
						else
							sw.println("Vous devez préciser l'ancien et le nouveau nom du fichier");
						sw.flush();
					}
					//commande cp, copy
					else if (arrayRequete[0].equals("cp") || arrayRequete[0].equals("copy"))
					{
						if(arrayRequete.length>2)
							this.copy(arrayRequete[1],arrayRequete[2]);
						else
							sw.println("Vous devez préciser le nom du fichier existant et le nouveau nom");
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

	public String help() {
		String res="Commandes disponible :"+RC+RC;
		res += "ls : liste les fichiers sur le repertoire courant"+RC;
		res += "pwd : affiche le repertoire courant"+RC;
		res += "cd : permet de se déplacer si un repertoire est spécifié"+RC;
		res += "cat/view/more : permet de visionner le fichier spécifié"+RC;
		res += "quit/exit/bye : coupe la connexion avec le serveur TVP"+RC;		
		res += "rename/mv : permet de renommer le fichier spécifié"+RC;		
		res += "copy/cp : permet de copier le fichier spécifié"+RC;		

		return res;
	}

	public String rename(String oldName, String newName) {
		String res = "";
		File old = new File(this.repCourant+"\\"+oldName);
		File nouveau = new File(this.repCourant+"\\"+newName);

		if(old.renameTo(nouveau)) {
			res += "Le fichier "+old+" a bien été renommé.";			 
		} else {			 
			res += "Echec! Le fichier "+old+" n'a pas pu être renommé.";
		}
		return res;
	}

	public String copy(String source, String cible) {
		String res = "";
		File fileSource = new File(this.repCourant+"\\"+source);
		String newFile = this.repCourant+"\\"+cible;

		if(fileSource.exists()) {
			try{
				FileWriter fos = new FileWriter(newFile);
				PrintWriter p = new PrintWriter(fos,true); 

				String contenu = viewToString(this.repCourant+"\\"+source);
				
				p.print(contenu);		// écriture sans retour-chariot
				p.close();
			} catch(IOException e) {e.printStackTrace();}

			res += "La copie à été effectué.";			 
		} else {			 
			res += "Echec! Le fichier "+source+" n'a pas pu être copié.";
		}
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
