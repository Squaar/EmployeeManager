package dumford;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;

public class MySQLConnector{

	private Connection connection;

	public MySQLConnector(String host, int port, String database, String user, String password) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
	}

	//close connection
	public void close() throws SQLException{
		connection.close();
	}

	//create tables to be used for this assignment
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

	//remove tables used for this assignment
	public void dropTables() throws SQLException{
		Statement st = connection.createStatement();
		st.executeUpdate("DROP TABLE employee");
		st.executeUpdate("DROP TABLE worksfor");
	}

	//run a query and return results as 2D array of Strings
	public String[][] query(String query) throws SQLException{
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query);

		int numCols = rs.getMetaData().getColumnCount();

		ArrayList<String[]> data = new ArrayList<String[]>();

		while(rs.next()){
			String[] row = new String[numCols];
			
			for(int i=0; i<numCols; i++){
				row[i] = rs.getString(i+1); //col index starts at 1, not 0
			}
			
			data.add(row);
		}

		rs.close();
		st.close();

		return data.toArray(new String[0][0]);
	}

	//insert, delete, etc.
	public void update(String update) throws SQLException{
		Statement st = connection.createStatement();
		st.executeUpdate(update);
	}
} 