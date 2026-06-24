package com.example.demo.service;

import java.io.BufferedReader;
//import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.MonthlyCountDto;
import com.example.demo.dto.UpdateSalesResponse;
import com.example.demo.dto.YearlyCountDto;
import com.example.demo.entity.CarSales;
import com.example.demo.repository.CarSalesRepository;

@Service
public class CarSalesServiceImp implements CarSalesService{

	@Autowired
	public  CarSalesRepository repository;
	
	public CarSalesServiceImp(CarSalesRepository repository) {
		this.repository = repository; 
	}

	@Override
	public UpdateSalesResponse uploadCsv(MultipartFile file) {
		
		List<CarSales> cars = new ArrayList<CarSales>();
		
		int failCount=0;
		int totalRecords=0;
		try(BufferedReader reader =
				new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
			
//			InputStream inputStream = file.getInputStream();//raw bites
//			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);		
		
		
		//CSVFormat
			CSVFormat csvformet = CSVFormat.DEFAULT.builder()
			.setHeader()
			.setSkipHeaderRecord(true)
			.setIgnoreHeaderCase(true)
			.setTrim(true)
			.build();   //Header
			 
			CSVParser csvparse = CSVParser.parse(reader, csvformet);
			
			for(CSVRecord record : csvparse ){
				totalRecords ++;
				
				try{
				
			 	String carNumber = record.get("Car Number");
			 	boolean exists = repository.existsByCarNumber(carNumber);
			 	
			 	if(exists) {
			 		failCount ++;
			 		System.out.println("Duplicate Data Found"+carNumber);
			 		continue;
			 	}
			 	
			 	CarSales carSales = new CarSales();

			 	carSales.setCarNumber(record.get("Car Number"));
			 	carSales.setBrand(record.get("Brand"));
			 	carSales.setModel(record.get("Model"));
			 	carSales.setColor(record.get("Color"));
			 	carSales.setYear(Integer.parseInt(record.get("Year")));
			 	carSales.setDateOfPurchase(LocalDate.parse(record.get("Date of Purchase"),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			 	carSales.setTimeOfPurchase(LocalTime.parse(record.get("Time of Purchase")));
			 	carSales.setPrice(Long.parseLong(record.get("Price (Rs)")));
			 	carSales.setMileage(Double.parseDouble(record.get("Mileage (Km/L)")));
			 	carSales.setEngineCc(Integer.parseInt(record.get("Engine (cc)")));
			 	carSales.setFuelType(record.get("Fuel Type"));
			 	carSales.setPaymentMode(record.get("Payment Mode"));
			 	carSales.setState(record.get("State"));
			 	carSales.setCity(record.get("City"));
			 	carSales.setCustomerName(record.get("Customer Name"));
			 	carSales.setContactNumber(record.get("Contact Number"));
			 	carSales.setEmail(record.get("Email"));
			 	carSales.setWarrantyPeriodYears(
			 	        Integer.parseInt(record.get("Warranty Period (years)")));
			 	
			 	cars.add(carSales);
				}
				catch(Exception ex) {
					failCount ++;
					System.out.println("Fail To Process Row: "+record.getRecordNumber());
					ex.printStackTrace();
				}
			
			}
			
			if(!cars.isEmpty()) {
				repository.saveAll(cars);
			}
			
			 	}
		catch(Exception exception) {
			exception.printStackTrace();
			
			throw new RuntimeException("unable to pars CSV");
			
		}
		
		int sucessCount = totalRecords - failCount;
		return new UpdateSalesResponse(totalRecords,sucessCount,failCount);
	}

	@Override
	public List<YearlyCountDto> getYearlyCarsCount() {
		
		return repository.getYearlyCount();
	}

	@Override
	public List<MonthlyCountDto> getMonthlyCountByYears(int year) {
		List<MonthlyCountDto> data = repository.getMonthlyCountByYear(year);
		
		Map<Integer, Long> map = data.stream()
				.collect(Collectors.toMap(
						MonthlyCountDto::month,
						MonthlyCountDto::count
					));
		
		List<MonthlyCountDto> result = new ArrayList<>();
		for(int i = 0; i <= 12; i++) {
			result.add(new MonthlyCountDto(i,map.getOrDefault(i,0L)));
		}
		
		// TODO Auto-generated method stub
		return result;
	}
	

}
