package persistance;
import carDealership.Vehicle;
import carDealership.Car;
import carDealership.Motorcycle;

import java.io.*;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBManager {

	private static DBManager m_dbManager;

	private String m_dbPath;
	private Connection m_connection;

	private DBManager() throws SQLException {
		var fileSystem = FileSystems.getDefault();
		m_dbPath = fileSystem.getPath(System.getProperty("user.home"), "dealership.sqlite3").toString();

		initDB();
	}

	public void runInsert(String query) throws SQLException {
		System.out.println("Will run insert query: " + query);
		var stmt = m_connection.createStatement();
		stmt.execute(query);
		m_connection.commit();
	}

	public ResultSet runQuery(String query) throws SQLException {
		System.out.println("Will run query: " + query);
		var stmt = m_connection.createStatement();
		return stmt.executeQuery(query);
	}

	public void runUpdate(String query) throws SQLException {
		System.out.println("Will run update query: " + query);
		var stmt = m_connection.createStatement();
		stmt.execute(query);
		m_connection.commit();
	}

	public void runDelete(String query) throws SQLException {
		System.out.println("Will run delete query: " + query);
		var stmt = m_connection.createStatement();
		stmt.execute(query);
		m_connection.commit();
	}

	private void initDB() throws SQLException {
		var dbFile = new File(m_dbPath);
		var mustCreateTables = !dbFile.exists();

		var url = "jdbc:sqlite:" + m_dbPath;
		try {
			m_connection = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			m_connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		if (!mustCreateTables) {
			System.out.println("DB file " + m_dbPath + " already exists. Not creating the database.");
		} else {
			System.out.println("Creating the DB file " + m_dbPath + " and the tables.");
			createTables();
		}
	}

	public Connection getConnection() {
		return m_connection;
	}

	private void createTables() throws SQLException {
		System.out.println("Creating the dealerships table");
		var dealershipSQL = "CREATE TABLE IF NOT EXISTS dealerships (id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " name text NOT NULL, location text NOT NULL, capacity INTEGER);";
	
		var stmt = m_connection.createStatement();
		stmt.execute(dealershipSQL);
	
        var userSQL = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " username text NOT NULL, password text NOT NULL, role TEXT CHECK(role IN ('Admin', 'Manager', 'Salesperson', 'Customer')) NOT NULL);";
	
		stmt.execute(userSQL);
	
		// Create the vehicles table
		var vehicleSQL = "CREATE TABLE IF NOT EXISTS vehicles ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "make TEXT NOT NULL, "
				+ "model TEXT NOT NULL, "
				+ "color TEXT, "
				+ "year INTEGER, "
				+ "price REAL, "
				+ "mileage INTEGER, "
				+ "status TEXT, "
				+ "type TEXT, "
				+ "handlebarType TEXT);";
	
		stmt.execute(vehicleSQL);

		// Create the sales table
	
		m_connection.commit();
	}

	public static DBManager getInstance() throws SQLException {
		if (m_dbManager == null) {
			m_dbManager = new DBManager();
		}

		return m_dbManager;
	}
}
