package com.ef;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ef.model.Block;
import com.ef.service.BlockService;
import com.ef.service.LogService;

public class Parser {
	
	//TODO: 
	// 1. inserir no log de bloqueio quantos requests
	// 2. alterar nome tabela log para request
	// 3. alterar nome tabela block para ip_blocked
	// 4. tratar exceções jdbc ok
	// 5. tratar paramentros por linha de comando ok
	// 6. gerar o Parse.jar ok
	// 7. Progress bar
	

	private static LogService logService = new LogService();
	private static BlockService blockService = new BlockService();
	private static SetupDatabase setupDatabase = new SetupDatabase();
	
	public static void main(String[] args) throws SQLException, NumberFormatException, ParseException {
	
		Integer threshold = null;
		Date dateFrom = null;
		Date dateTo = null;
		
		if(args.length == 4) {
			if(args[0].contains("--accesslog=") && args[1].contains("--startDate=") && args[2].contains("--duration=") && args[3].contains("--threshold=")) {
				
				setupDatabase.setup(args[0].replace("--accesslog=", ""));
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
				dateFrom = format.parse(args[1].replace("--startDate=", ""));

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateFrom);
				
				String duration = args[2].replace("--duration=", "");

				if (duration.equals("hourly")) {
					calendar.roll(Calendar.HOUR, 1);
				} else if (duration.equals("daily")) {
					calendar.roll(Calendar.DAY_OF_YEAR, 1);
				} else {
					throw new IllegalArgumentException();
				}
				
				dateTo = calendar.getTime();
				
				threshold = new Integer(args[3].replace("--threshold=", ""));
				
				List<String> list = logService.fetchIpByDateAndThreshold(dateFrom, dateTo, threshold);
				
				block(list, dateFrom, dateTo, threshold);
				
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
	

	private static void block(List<String> list, Date dateFrom, Date dateTo, int threshold) throws SQLException {
		for (String ip : list) {
			String message = ip + " blocked because more than " + threshold + " requests between " + dateFrom + " and " + dateTo;
			System.out.println(message);
			blockService.insert(new Block(ip, message));
		}
	}



}
