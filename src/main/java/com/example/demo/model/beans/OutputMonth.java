package com.example.demo.model.beans;

import java.math.BigInteger;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class OutputMonth {
	private BigInteger outputId;

	private int outPrice;

	private String outContent;

	private String outDate;

	private Timestamp CreatedAt;

	private Timestamp UpdatedAt;
	
	public OutputMonth(BigInteger outputId, Timestamp CreatedAt, Timestamp UpdatedAt, String outContent, int outPrice, String outDate) {
		this.outputId = outputId;
		this.outPrice = outPrice;
		this.outContent = outContent;
		this.outDate = outDate;
		this.CreatedAt = CreatedAt;
		this.UpdatedAt = UpdatedAt;
	}
	
	public OutputMonth(Object[] object) {
		this((BigInteger)object[0], (Timestamp)object[1], (Timestamp)object[2], (String)object[3], (int)object[4], (String)object[5]);
	}
}
