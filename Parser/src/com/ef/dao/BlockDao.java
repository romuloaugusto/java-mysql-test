package com.ef.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ef.connection.DatabaseConnection;
import com.ef.model.Block;

public class BlockDao {

	private static final String TRUNCATE_STATEMENT = "TRUNCATE block";
	private static final String INSERT_STATEMENT = "INSERT INTO block (ip, details) VALUES (?,?)";

	public int insert(Block block) throws SQLException {

		Connection connection = DatabaseConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(INSERT_STATEMENT);
			ps.setString(1, block.getIp());
			ps.setString(2, block.getDetails());
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

}
