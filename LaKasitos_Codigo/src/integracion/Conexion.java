package integracion;

import java.sql.*;

public class Conexion {

	private static String url = "jdbc:mysql://localhost:3306/gimnasio_lakalle";
	private static String username = "root";
	private static String password = "root";

	public static Connection getConnection() throws SQLException {
		// begin-user-code
		return DriverManager.getConnection(url, username, password);
		// end-user-code
	}
}