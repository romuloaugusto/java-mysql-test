package com.ef;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ef.model.Log;
import com.ef.service.LogService;

public class SetupDatabase {
	
	private LogService logService = new LogService();
	
	public void setup(String path) throws NumberFormatException, ParseException, SQLException {
		try {
			FileReader file = new FileReader(path);
			BufferedReader in = new BufferedReader(file);
			String line;
			System.out.println("Running inserts on table log");
			List<Log> logBatch = new ArrayList<>();
			while((line = in.readLine()) != null) {
			    String[] record = line.split("\\|");
			    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		    	logBatch.add(new Log(format.parse(record[0]), record[1], record[2], new Integer(record[3]), record[4]));
			}
			logService.insertBatch(logBatch);
			in.close();
			System.out.println("Inserts on table log is done");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, ParseException, SQLException {
			SetupDatabase main  = new SetupDatabase();
			main.setup(System.getProperty("user.dir") + "/access.log");
	}

}
