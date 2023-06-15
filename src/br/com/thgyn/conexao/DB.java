package br.com.thgyn.conexao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import br.com.thgyn.exceptions.DbException;

public class DB {
	
	private static String url;
	private static Properties properties;
	
	public static Connection getConnection() {
		try {
			if(DB.url == null || DB.properties == null) {
				properties = loadProperties();
				url = properties.getProperty("dburl");
				DriverManager.getConnection(url, properties);
				return DriverManager.getConnection(url, properties);
			}
			return DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() {
		try(InputStream inputStream = DB.class.getResourceAsStream("db.properties")) {
			Properties properties = new Properties();
			properties.load(inputStream);
			return properties;
		}
		catch(IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
