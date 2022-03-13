package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Input;

public interface InputRepository extends JpaRepository<Input, Long> {

	// 入金の合計額
	@Query(value = "select SUM(in_price) from many_inputs", nativeQuery = true)
	public int getAllInput();
}
