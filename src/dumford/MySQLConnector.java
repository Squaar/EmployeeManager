package dumford;

import java.sql.*;

public class MySQLConnector{

	private Connection connection;

	public MySQLConnector(String host, int port, String database, String user, String password) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
	}

	public void close() throws SQLException{
		connection.close();
	}

	public void createTables() throws SQLException{
		Statement st = connection.createStatement();
		String createEmployee = "CREATE TABLE employee " +
								"(eid INTEGER not NULL, " +
								" name VARCHAR(20), " + 
								" salary INTEGER, " +
								" PRIMARY KEY (eid))";
		String createWorksfor = "CREATE TABLE worksfor " + 
								"(eid INTEGER, " + 
								" mid INTEGER)";

		st.executeUpdate(createEmployee);
		st.executeUpdate(createWorksfor);
	}

	public void dropTables() throws SQLException{
		Statement st = connection.createStatement();
		st.executeUpdate("DROP TABLE employee");
		st.executeUpdate("DROP TABLE worksfor");
	}
} 