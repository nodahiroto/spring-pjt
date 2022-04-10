package com.example.demo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Input;
import com.example.demo.model.beans.InputMonth;

public interface InputRepository extends JpaRepository<Input, Long> {

	// 入金の合計額
	@Query(value = "select SUM(in_price) from many_inputs", nativeQuery = true)
	public int getAllInput();
	
	// 月別の入金合計金額
	@Query(value = "SELECT DATE_FORMAT(in_date, '%Y%m') as YM, SUM(in_price) "
			+ "FROM many_inputs GROUP BY DATE_FORMAT(in_date, '%Y%m')", nativeQuery = true)
	public List<Object[]> getSumMonthInput();
	
	// 現在月のデータ一覧を取得
	@Query(value = "SELECT * FROM many_inputs WHERE MONTH(in_date) = MONTH(CURRENT_DATE()) "
			+ "AND YEAR(in_date) = YEAR(CURRENT_DATE())", nativeQuery = true)
	public List<Object[]> getNowMonthInput();
	
	default List<InputMonth> findNowMonthInput() {
		return getNowMonthInput().stream().map(InputMonth::new).collect(Collectors.toList());
	}
	
	// 特定の月のデータ一覧を取得
	@Query(value = "SELECT * FROM many_inputs WHERE DATE_FORMAT(in_date, '%m')=:month", nativeQuery = true)
	public List<Object[]> getMonthInput(@Param("month") int month);
	
	default List<InputMonth> findMonthInput(int month) {
		return getMonthInput(month).stream().map(InputMonth::new).collect(Collectors.toList());
	}
	
	// 現在の月の合計金額
	@Query(value = "SELECT SUM(in_price) FROM many_inputs WHERE MONTH(in_date) = MONTH(CURRENT_DATE())", nativeQuery = true)
	public int getTotalNowMonthInput();
	
	// 特定の月の合計金額
	@Query(value = "SELECT SUM(in_price) FROM many_inputs WHERE MONTH(in_date) = :month", nativeQuery = true)
	public int getTotalMonthInput(@Param("month") int month);
}
