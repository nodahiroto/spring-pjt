package com.example.demo.model.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ByMonth implements Serializable {

	private Timestamp byMonth;
	
	private int total;
	
	public ByMonth(Timestamp byMonth, int total) {
		this.byMonth = byMonth;
		this.total = total;
	}
	
	public ByMonth(Object[] obj) {
		this((Timestamp)obj[0], (int)obj[1]);
	}
}
