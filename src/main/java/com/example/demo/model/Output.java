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
@Table(name="many_outputs")
public class Output {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "output_id", nullable = false)
	private Long outputId;
	
	@NotNull
	@Positive
	@Min(1)
	@Max(1000000)
	@Column(name = "out_price")
	private int outPrice;
	
	@Column(name = "out_content")
	private String outContent;
	
	@Column(name = "out_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate outDate;
	
	@Column(name = "created_at")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDateTime CreatedAt;
	
	@Column(name = "update_at")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDateTime UpdatedAt;

}
