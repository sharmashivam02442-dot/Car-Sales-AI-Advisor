package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.MonthlyCountDto;
import com.example.demo.dto.YearlyCountDto;
import com.example.demo.entity.CarSales;

@Repository
public interface CarSalesRepository extends JpaRepository<CarSales, Long> {
	
	boolean existsByCarNumber(String carNumber);
	
	@Query("""
			Select new com.example.demo.dto.YearlyCountDto(c.year,count(c))
			from CarSales c
			Group by c.year
			Order by c.year
			""")
	List<YearlyCountDto> getYearlyCount();
	
	@Query("""
			Select new com.example.demo.dto.MonthlyCountDto(MONTH(c.dateOfPurchase),COUNT(c))
			from CarSales c
			where YEAR(c.dateOfPurchase) = :year
			Group by MONTH(c.dateOfPurchase)
			Order by MONTH(c.dateOfPurchase)
			""")
	List<MonthlyCountDto> getMonthlyCountByYear(@Param("year") int year);
}
