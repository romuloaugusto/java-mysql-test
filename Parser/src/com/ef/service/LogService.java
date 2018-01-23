package com.ef.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.ef.dao.LogDao;
import com.ef.model.Log;

public class LogService {
	
	private LogDao dao = new LogDao();
	
	public int insert(Log log) throws SQLException {
		return dao.insert(log);
	}
	
	public void insertBatch(List<Log> logs) throws SQLException {
		dao.insertBatch(logs);
	}
	
	public int truncate() throws SQLException {
		return dao.truncate();
	}
	
	public List<String> fetchIpByDateAndThreshold(Date dateFrom, Date dateTo, int threshold) throws SQLException {
		return dao.fetchIpByDateAndThreshold(dateFrom, dateTo, threshold);
	}

}
