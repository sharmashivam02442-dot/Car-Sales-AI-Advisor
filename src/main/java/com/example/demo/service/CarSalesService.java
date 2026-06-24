package com.example.demo.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.MonthlyCountDto;
import com.example.demo.dto.UpdateSalesResponse;
import com.example.demo.dto.YearlyCountDto;


public interface CarSalesService {
	
	UpdateSalesResponse uploadCsv(MultipartFile file);
	List<YearlyCountDto> getYearlyCarsCount();
	List<MonthlyCountDto>getMonthlyCountByYears(int year);
}
