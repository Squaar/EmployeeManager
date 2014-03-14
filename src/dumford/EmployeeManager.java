package dumford;

import java.util.Scanner;
import java.io.File;
import java.sql.SQLException;

//import MySQLConnector;

public class EmployeeManager{

	private static MySQLConnector conn;

	public static void main(String args[]){

		if(args.length < 3){
			System.err.println("Not enough arguments. \n\tArgs: database user password");
			System.exit(1);
		}

		String db = args[0];
		String user = args[1];
		String pass = args[2];

		
		try{
			conn = new MySQLConnector("localhost", 3306, db, user, pass);
			conn.createTables();
			Scanner sc = new Scanner(new File("transfile"));

			while(sc.hasNextLine()){
				String line = sc.nextLine().trim();
				handleLine(line);				
			}

			conn.dropTables();
			conn.close();
		}catch(Exception e){e.printStackTrace();}
		System.out.println("Done.");
	}

	private static void handleLine(String line){
		if(!line.matches("[1-6]{1}.*"))
			System.err.println("Error parsing line: " + line);
		else{
			String parse[] = line.split(" ");

			switch(Integer.parseInt(parse[0])){
				case 1: handle1(parse);
						break;
				case 2: handle2(parse);
						break; 
				case 3: handle3(parse);
						break;
				case 4: handle4(parse);
						break;
				case 5: handle5(parse);
						break;
				case 6: handle6(parse);
						break;
			}
		}
	}

	//delete employee
	private static void handle1(String line[]){
		
	}

	//insert new employee
	private static void handle2(String line[]){
		
	}

	//get average salary
	private static void handle3(String line[]){
		String query = 	"SELECT AVG(salary) " +
						"FROM employee";
		try{
			String avgSalary = conn.executeQuery(query)[0][0];
			if(avgSalary == null)
				System.out.println("No employees to get the average salary of!");
			else
				System.out.println("Average Salary: " + avgSalary);
		}catch(SQLException e){
			System.err.println("Error getting average salary.");
			e.printStackTrace();
		}
	}

	//get names of employees that work under a manager recursively
	private static void handle4(String line[]){
		
	}

	//get average salaries of employees that work under a manager
	private static void handle5(String line[]){
		
	}

	//get employees with more than one manager
	private static void handle6(String line[]){
		
	}
}