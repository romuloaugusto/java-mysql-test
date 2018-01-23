package com.ef.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ef.connection.DatabaseConnection;
import com.ef.model.Log;

public class LogDao {

	private static final String SELECT_IP_STATEMENT = "SELECT ip, COUNT(id) as count FROM log WHERE date BETWEEN ? AND ? GROUP BY ip HAVING count > ?";
	private static final String TRUNCATE_STATEMENT = "TRUNCATE log";
	private static final String INSERT_STATEMENT = "INSERT INTO log (date, ip, request, status, useragent) VALUES (?,?,?,?,?)";

	public int insert(Log log) throws SQLException {

		Connection connection = DatabaseConnection.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERT_STATEMENT);
		
		try {
			ps.setTimestamp(1, new Timestamp(log.getDate().getTime()));
			ps.setString(2, log.getIp());
			ps.setString(3, log.getRequest());
			ps.setInt(4, log.getStatus());
			ps.setString(5, log.getUserAgent());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public void insertBatch(List<Log> logs) throws SQLException {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERT_STATEMENT);

		try {
			for (Log log : logs) {
				ps.setTimestamp(1, new Timestamp(log.getDate().getTime()));
				ps.setString(2, log.getIp());
				ps.setString(3, log.getRequest());
				ps.setInt(4, log.getStatus());
				ps.setString(5, log.getUserAgent());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public int truncate() throws SQLException {

		Connection connection = DatabaseConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(TRUNCATE_STATEMENT);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public List<String> fetchIpByDateAndThreshold(Date dateFrom, Date dateTo, int threshold) throws SQLException {

		Connection connection = DatabaseConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		List<String> result = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(SELECT_IP_STATEMENT);
			ps.setTimestamp(1, new Timestamp(dateFrom.getTime()));
			ps.setTimestamp(2, new Timestamp(dateTo.getTime()));
			ps.setInt(3, threshold);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				result.add(resultSet.getString("ip"));
			}
			return result;

		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}

	}

}
