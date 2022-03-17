package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="many_inputs")
public class Input {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "input_id", nullable = false)
	private Long inputId;
	
	@NotNull
	@Positive
	@Min(1)
	@Max(1000000)
	@Column(name = "in_price")
	private int inPrice;
	
	@Column(name = "in_content")
	private String inContent;
	
	@NotNull
	@Column(name = "in_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate inDate;
	
	@Column(name = "created_at")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDateTime CreatedAt;
	
	@Column(name = "update_at")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDateTime UpdatedAt;
	
}
