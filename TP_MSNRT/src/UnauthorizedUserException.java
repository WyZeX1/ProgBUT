
public class UnauthorizedUserException extends Exception{

	private static final long serialVersionUID = 1L;
	private String login;
	
	public UnauthorizedUserException(String login)
	{
		this.login=login;
	}
	public String toString()
	{
		return "L'utilisateur "+this.login+" n'a pas �t� autoris� � se connecter (mdp erron�, non enregistr�...)";
	}
}
