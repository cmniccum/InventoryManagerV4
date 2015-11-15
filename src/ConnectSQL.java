import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectSQL {
	
	//Constructor
	public ConnectSQL() throws Exception {
			
		
	}
		
	//getMySqlConnection connects to the MySQL database
	public Connection getMySqlConnection ()throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://" + 
				"localhost:3306/inventory", "root", "root");	
	}
}
