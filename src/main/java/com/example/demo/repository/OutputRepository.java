package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Output;

public interface OutputRepository extends JpaRepository<Output, Long> {

	// 入金の合計額
		@Query(value = "select SUM(out_price) from many_outputs", nativeQuery = true)
		public int getAllOutput();
}
