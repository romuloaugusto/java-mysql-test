package com.ef.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

	private static DatabaseConnection instance;
	private Connection connection;

	private String dbHost;
	private String database;
	private String dbUser;
	private String dbPassword;

	private DatabaseConnection() throws SQLException {
		try {
			loadDatabaseConfig();
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(
					"jdbc:mysql://" + dbHost + "/" + database + "?user=" + dbUser + "&password=" + dbPassword + "&useSSL=false");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DatabaseConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new DatabaseConnection();
		} else if (instance.getConnection().isClosed()) {
			instance = new DatabaseConnection();
		}
		return instance;
	}

	private void loadDatabaseConfig() {
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");
			Properties prop = new Properties();
			prop.load(input);
			
			database = prop.getProperty("database");
			dbHost = prop.getProperty("dbhost");
			dbUser = prop.getProperty("dbuser");
			dbPassword = prop.getProperty("dbpassword");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}