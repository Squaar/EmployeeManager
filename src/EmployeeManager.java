import java.sql.*;

public class EmployeeManager{
	public static void main(String args[]){

		if(args.length < 4)
			System.exit(1);

		String db = args[1];
		String user = args[2];
		String pass = args[3];

		MySQLConnector conn;
		try{
			conn = new MySQLConnector("localhost", 3306, db, user, pass);
			conn.createTables();
		} catch(Exception e){e.printStackTrace();}

		
	}
}