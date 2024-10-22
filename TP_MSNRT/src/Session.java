
public class Session {

	public String login,pseudo;
	public boolean authorized;
	
	public Session() {
		this.login="";
		this.pseudo="";
		this.authorized=false;
	}
	
	public String toString() {
		return "Session ("+this.login+","+this.pseudo+","+(this.authorized ? "connecté" : "non connecté");
	}
}
