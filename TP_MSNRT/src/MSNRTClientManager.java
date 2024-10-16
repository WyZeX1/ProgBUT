/** definition du contrat entre un gestionnaire de client MSNRT et le client MSNRT */
public interface MSNRTClientManager {
	// un gestionnaire de client MSNRT s'engage a fournir une méthode register qui permet au client de s'introduire dans le système */
	/**
	 * enregistre un utilisateur
	 * @param login login de l'utilisateur voulant se connecter au service MSNRT
	 * @param passwd login mot de passe de l'utilisateur voulant se connecter au service MSNRT
	 * @return le pseudo de l'utilisateur si l'authentification a réussi
	 */
	public String register(String login, String passwd) throws UnauthorizedUserException;
	
	// un gestionnaire de client MSNRT s'engage a fournir une méthode unregister qui permet au client de quitter le système */
	/**
	 * libère l'utilisateur 
	 * @param login login de l'utilisateur voulant quitter la messagerie MSNRT
	 */
	public void unregister(String login);
	
	/**
	 * renvoie la liste des pseudonymes des utilisateurs connectés
	 * @return les pseudonymes des utilisateurs connectés
	 */
	public  String[] getPseudos();
}