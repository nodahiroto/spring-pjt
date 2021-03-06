package com.example.demo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Output;
import com.example.demo.model.beans.OutputMonth;

public interface OutputRepository extends JpaRepository<Output, Long> {

	    // 支出の合計額
		@Query(value = "select SUM(out_price) from many_outputs", nativeQuery = true)
		public Integer getAllOutput();
		
		// 月別の支出合計金額
		@Query(value = "SELECT DATE_FORMAT(out_date, '%Y%m') as YM, SUM(out_price) "
				+ "FROM many_outputs GROUP BY DATE_FORMAT(out_date, '%Y%m')", nativeQuery = true)
		public Object[][] getSumMonthOutput();
		
//		default List<ByMonth> findSumMonthOutput() {
//			return getSumMonthOutput().stream.map(ByMonth::new).collect(Collectors.toList());
//		}
		
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
		public Integer getTotalNowMonthOutput();
		
		// 特定の月の合計金額
		@Query(value = "SELECT SUM(out_price) FROM many_outputs WHERE MONTH(out_date) = :month", nativeQuery = true)
		public Integer getTotalMonthOutput(@Param("month") int month);
		
		// 特定の月のデータが存在するかチェック
		@Query(value = "SELECT EXISTS(SELECT * FROM many_outputs WHERE MONTH(out_date) = :month)", nativeQuery = true)
		public int checkMonth(@Param("month") int month);
		
		// 全データを取得(日付 昇順)
		@Query(value = "SELECT * FROM many_outputs ORDER BY out_date", nativeQuery = true)
		public List<Output> findOutputUp();
		
		// 全データを取得(日付 降順)
		@Query(value = "SELECT * FROM many_outputs ORDER BY out_date DESC", nativeQuery = true)
		public List<Output> findOutputDown();
}
