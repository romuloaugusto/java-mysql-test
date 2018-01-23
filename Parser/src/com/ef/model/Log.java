package com.ef.model;

import java.util.Date;

public class Log {
	
	private Integer id;
	
	private Date date;
	
	private String ip;
	
	private String request;
	
	private Integer status;
	
	private String userAgent;

	public Log(Date date, String ip, String request, Integer status, String userAgent) {
		this.date = date;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.userAgent = userAgent;
	}
	
	public Log() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public String toString() {
		return "LogModel [date=" + date + ", ip=" + ip + ", request=" + request + ", status=" + status + ", userAgent="
				+ userAgent + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
