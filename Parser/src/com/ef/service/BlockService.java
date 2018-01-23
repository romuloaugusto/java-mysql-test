package com.ef.service;

import java.sql.SQLException;

import com.ef.dao.BlockDao;
import com.ef.model.Block;

public class BlockService {
	
	private BlockDao dao = new BlockDao();
	
	public int insert(Block block) throws SQLException {
		return dao.insert(block);
	}
	
	public int truncate() throws SQLException {
		return dao.truncate();
	}
	
}
