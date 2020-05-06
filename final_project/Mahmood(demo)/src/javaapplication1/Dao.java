package javaapplication1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dao {
	// instance fields
	static Connection connect = null;
	static Connection connect1 = null;
	Statement statement = null;
	Statement statement1 = null;
	// constructor
	public Dao() {
	  
	}

	public Connection getConnection() {
		// Setup the connection with the DB
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://www.papademas.net:3307/tickets?autoReconnect=true&useSSL=false"
							+ "&user=fp411&password=411");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}

	// CRUD implementation

	public void createTables() {
		// variables for SQL Query table creations
		final String createTicketsTable = "CREATE TABLE sauguss_tickets(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_issuer VARCHAR(30), ticket_description VARCHAR(200))";
		final String createUsersTable = "CREATE TABLE sauguss_users(uid INT AUTO_INCREMENT PRIMARY KEY, uname VARCHAR(30), upass VARCHAR(30), admin int)";
		final String createWorkersTable = "CREATE TABLE nguyen_workers(worker_name VARCHAR(100), worker_id INT AUTO_INCREMENT PRIMARY KEY)";
		try {

			// execute queries to create tables

			statement = getConnection().createStatement();

			statement.executeUpdate(createTicketsTable);
			statement.executeUpdate(createUsersTable);
			statement.executeUpdate(createWorkersTable);
			System.out.println("Created tables in given database...");

			// end create table
			// close connection/statement object
			statement.close();
			connect.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// add users to user table
		addUsers();
	}

	public void addUsers() {
		// add list of users from userlist.csv file to users table

		// variables for SQL Query inserts
		String sql;

		Statement statement;
		BufferedReader br;
		List<List<String>> array = new ArrayList<>(); // list to hold (rows & cols)

		// read data from file
		try {
			br = new BufferedReader(new FileReader(new File("./userlist.csv")));

			String line;
			while ((line = br.readLine()) != null) {
				array.add(Arrays.asList(line.split(",")));
			}
		} catch (Exception e) {
			System.out.println("There was a problem loading the file");
		}

		try {

			// Setup the connection with the DB

			statement = getConnection().createStatement();

			// create loop to grab each array index containing a list of values
			// and PASS (insert) that data into your User table
			for (List<String> rowData : array) {

				sql = "insert into sauguss_users(uname,upass,admin) " + "values('" + rowData.get(0) + "'," + " '"
						+ rowData.get(1) + "','" + rowData.get(2) + "');";
				statement.executeUpdate(sql);
			}
			System.out.println("Inserts completed in the given database...");

			// close statement object
			statement.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	
	
	
	
	
	public int insertRecords(String ticketName, String ticketDesc) {
		int id = 0;
		try {
			statement = getConnection().createStatement();
			statement.executeUpdate("Insert into sauguss_tickets" + "(ticket_issuer, ticket_description) values(" + " '"
					+ ticketName + "','" + ticketDesc + "')", Statement.RETURN_GENERATED_KEYS);

			// retrieve ticket id number newly auto generated upon record insertion
			ResultSet resultSet = null;
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// retrieve first field in table
				id = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;

	}
	
	
	
	
	public int insertuser(String fullname, String email, String username, String password, String admin ) {
		int id1 = 0;
		try {
			statement = getConnection().createStatement();
			statement.executeUpdate("Insert into tickets.sauguss_user" + "(fullname,email, username, password, admin) values(" + " '"
					+ fullname + "','" + email + "','" + username + "' , '" + password + "' , '" + admin + "')", Statement.RETURN_GENERATED_KEYS);

			// retrieve ticket id number newly auto generated upon record insertion
			ResultSet resultSet = null;
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// retrieve first field in table
				id1 = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id1;

	}
	
	
	
	
	
	public int insertadmin(String uname, String upass, String admin ) {
		int id2 = 0;
		try {
			statement = getConnection().createStatement();
			statement.executeUpdate("Insert into sauguss_users" + "(uname, upass, admin) values(" + " '"
					+ uname + "','" + upass + "','" + admin + "')", Statement.RETURN_GENERATED_KEYS);

			// retrieve ticket id number newly auto generated upon record insertion
			ResultSet resultSet = null;
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// retrieve first field in table
				id2 = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id2;

	}
	
	
	
	public int inserthistory(String uid, String ticket_issuer, String ticket_description, String start_date , String end_date) {
		int id2 = 0;
	
		try {
			statement = getConnection().createStatement();
			statement.executeUpdate("Insert into sauguss_history" + "(uid,ticket_issuer, ticket_description, start_date,end_date) values(" + " '" + uid + "', '"
					+ ticket_issuer + "','" + ticket_description + "','" + start_date + "', '" + end_date + "')", Statement.RETURN_GENERATED_KEYS);

			// retrieve ticket id number newly auto generated upon record insertion
			ResultSet resultSet = null;
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// retrieve first field in table
				id2 = resultSet.getInt(1);
			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id2;

	}
	
	
	
	
	public int delhistory(String ticket_id ) {
		int id2 = 0;
	
		try {
			statement = getConnection().createStatement();
			statement.executeUpdate("DELETE FROM sauguss_history WHERE ticket_id='" + ticket_id + "'", Statement.RETURN_GENERATED_KEYS);

			// retrieve ticket id number newly auto generated upon record insertion
			ResultSet resultSet = null;
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// retrieve first field in table
				id2 = resultSet.getInt(2);
			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id2;

	}
	
	
	
	
	
	
	

	public ResultSet readRecords() {

		ResultSet results = null;
		try {
			statement = connect.createStatement();
			results = statement.executeQuery("SELECT * FROM sauguss_tickets");
			//connect.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return results;
	}
	
	
	
	public ResultSet readuserRecords() {

		ResultSet results2 = null;
		try {
			statement = connect.createStatement();
			results2 = statement.executeQuery("SELECT * FROM tickets.sauguss_user");
			//connect.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return results2;
	}
	
	public ResultSet readadminRecords() {

		ResultSet results3 = null;
		try {
			statement = connect.createStatement();
			results3 = statement.executeQuery("SELECT * FROM tickets.sauguss_users");
			
		} catch (SQLException e3) {
			e3.printStackTrace();
			
		}
		return results3;

	}
	
	
	
	
	
	public ResultSet readhistoryRecords() {

		ResultSet results4 = null;
		try {
			statement = connect.createStatement();
			results4 = statement.executeQuery("SELECT * FROM tickets.sauguss_history");
			
		} catch (SQLException e4) {
			e4.printStackTrace();
			
		}
		return results4;

	}
	
	
	
	
	
	// continue coding for updateRecords implementation

	// continue coding for deleteRecords implementation
}
