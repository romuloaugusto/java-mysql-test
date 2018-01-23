package com.ef.model;

public class Block {
	
	private Integer id;
	
	private String ip;
	
	private String details;

	public Block(String ip, String details) {
		this.ip = ip;
		this.details = details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	

}
