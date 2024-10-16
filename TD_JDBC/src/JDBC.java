import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	public static void main(String[] args)
	{
		String driverClassName = "com.mckoi.JDBCDriver";
		String databaseURL= "jdbc:mckoi://127.0.0.1/";
		String username = "admin";
		String password = "pass";

		//On vérifie que le driver existe et est accessible
		try {Class.forName(driverClassName);}
		catch (ClassNotFoundException e) {
			System.err.println("Le driver n'a pas été trouvé :(");
			e.printStackTrace();
		}
		
		// Préparer connexion au serveur SGBD
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(databaseURL, username, password);
			//try {connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
			System.out.println("Connexion établie : "+connection+"\n");

			String reqSelect = "SELECT * FROM RegisteredUsers";
			Statement stat = connection.createStatement();
			ResultSet rs = null;

			rs = stat.executeQuery(reqSelect);

			while (rs.next()) {
				String login = rs.getString("login");
				String pseudo = rs.getString("pseudo");
				System.out.println("L'utilisateur ---"+login+"--- a pour pseudo : "+pseudo);
			}

		} catch (SQLException e) {e.printStackTrace();}
	}
}
