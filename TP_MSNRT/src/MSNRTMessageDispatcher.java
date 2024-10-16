
public interface MSNRTMessageDispatcher {
	/**
	 * délivre à  tous  les  utilisateurs  connect�s  le  message msg envoyé  par l'utilisateur pseudo
	 * @param sender_pseudo pseudonyme de l'utilisateur qui envoie le message
	 * @param msg message a délivrer
	 */
	public void broadcastMessage(String  sender_pseudo,  String msg);

	/**
	 * délivre à l'utilisateur receiver_pseudo (s'il est connecté)  le  message msg envoyé  par sender_pseudo
	 * @param sender_pseudo pseudonyme de l'émetteur du message
	 * @param receiver_pseudo pseudonyme du destinataire du message
	 * @param msg
	 */
	public void dispatchPrivateMessage(String sender_pseudo, String receiver_pseudo, String msg);	
}
