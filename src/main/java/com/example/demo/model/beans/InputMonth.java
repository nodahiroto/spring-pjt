package com.example.demo.model.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class InputMonth implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigInteger inputId;

	private int inPrice;

	private String inContent;

	private String inDate;

	private Timestamp CreatedAt;

	private Timestamp UpdatedAt;
	
	public InputMonth(BigInteger inputId, Timestamp CreatedAt, Timestamp UpdatedAt, String inContent, int inPrice, String inDate) {
		this.inputId = inputId;
		this.inPrice = inPrice;
		this.inContent = inContent;
		this.inDate = inDate;
		this.CreatedAt = CreatedAt;
		this.UpdatedAt = UpdatedAt;
	}
	
	public InputMonth(Object[] object) {
		this((BigInteger)object[0], (Timestamp)object[1], (Timestamp)object[2], (String)object[3], (int)object[4], (String)object[5]);
	}
}
