package com.example.demo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Output;
import com.example.demo.model.beans.OutputMonth;

public interface OutputRepository extends JpaRepository<Output, Long> {

	    // 入金の合計額
		@Query(value = "select SUM(out_price) from many_outputs", nativeQuery = true)
		public int getAllOutput();
		
		// 月別の入金合計金額
		@Query(value = "SELECT DATE_FORMAT(out_date, '%Y%m') as YM, SUM(out_price) "
				+ "FROM many_outputs GROUP BY DATE_FORMAT(out_date, '%Y%m')", nativeQuery = true)
		public List<Object[]> getSumMonthOutput();
		
		// 現在月のデータ一覧を取得
		@Query(value = "SELECT * FROM many_outputs WHERE MONTH(out_date) = MONTH(CURRENT_DATE()) "
				+ "AND YEAR(out_date) = YEAR(CURRENT_DATE())", nativeQuery = true)
		public List<Object[]> getNowMonthOutput();
		
		default List<OutputMonth> findNowMonthOutput() {
			return getNowMonthOutput().stream().map(OutputMonth::new).collect(Collectors.toList());
		}
		
		// 特定の月のデータ一覧を取得
		@Query(value = "SELECT * FROM many_outputs WHERE DATE_FORMAT(out_date, '%m')=:month", nativeQuery = true)
		public List<Object[]> getMonthOutput(@Param("month") int month);
		
		default List<OutputMonth> findMonthOutput(int month) {
			return getMonthOutput(month).stream().map(OutputMonth::new).collect(Collectors.toList());
		}
		
		// 現在の月の合計金額
		@Query(value = "SELECT SUM(out_price) FROM many_outputs WHERE MONTH(out_date) = MONTH(CURRENT_DATE())", nativeQuery = true)
		public int getTotalNowMonthOutput();
		
		// 特定の月の合計金額
		@Query(value = "SELECT SUM(out_price) FROM many_outputs WHERE MONTH(out_date) = :month", nativeQuery = true)
		public int getTotalMonthOutput(@Param("month") int month);
}
