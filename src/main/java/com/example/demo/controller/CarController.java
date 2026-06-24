package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.dto.MonthlyCountDto;
import com.example.demo.dto.UpdateSalesResponse;
import com.example.demo.dto.YearlyCountDto;
import com.example.demo.service.CarSalesServiceImp;

@RestController
@RequestMapping("/api/car-sales")
public class CarController {
	@Autowired
	private CarSalesServiceImp carSalesServiceImp;
	
	public CarController(CarSalesServiceImp carSalesServiceImp) {
		this.carSalesServiceImp = carSalesServiceImp;
	}
	
	@PostMapping("/upload-csv")
	public ResponseEntity <ApiResponse<UpdateSalesResponse>> uploadFiles(@RequestParam("file") MultipartFile file) {
		
		//file is avalibal or not
		
		if (file.isEmpty()) {
			
			// response
			
			UpdateSalesResponse response = new UpdateSalesResponse(0,0,0);

			ApiResponse<UpdateSalesResponse> apiresponse = new ApiResponse<>(
					false,
					"File is Empty",
					response,
					HttpStatus.BAD_REQUEST.value() );
	
			return new ResponseEntity<ApiResponse<UpdateSalesResponse>>(apiresponse, HttpStatus.BAD_REQUEST);
			
		}
		
		// response
		UpdateSalesResponse response = carSalesServiceImp.uploadCsv(file);
		ApiResponse<UpdateSalesResponse> apiresponse = getApiResponse(response);
		
		return ResponseEntity.ok(apiresponse);
	
	}
	private static ApiResponse<UpdateSalesResponse> getApiResponse(UpdateSalesResponse response){
		
		String message;
		boolean success;
		
		if(response.getFailedCount()==0) {
			message="All records updated successfully!!";
			success=true;
		}
		else if(response.getSuccessCount()==0) {
			message="All records fail to upload";
			success=false;
		}
		else {
			message="Uploaded with some error"+response.getFailedCount()+"rows failed";
			success=false;
		}
		
		return new ApiResponse<UpdateSalesResponse>(success,message,response,HttpStatus.OK.value());
	}
	
	
	@GetMapping("/yearly-count")
	public ResponseEntity<ApiResponse<List<YearlyCountDto>>> yearlyCount(){
		
		List<YearlyCountDto> carsCount = carSalesServiceImp.getYearlyCarsCount();
		
		ApiResponse<List<YearlyCountDto>> response = new ApiResponse<List<YearlyCountDto>>(true,"Date Read Successfully",carsCount,HttpStatus.OK.value());
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/monthly-count")
	public ResponseEntity<ApiResponse<List<MonthlyCountDto>>> monthlyCount(
		@RequestParam int year){
		
		return ResponseEntity.ok(
				new ApiResponse<>
				(true,
						"Monthly Data Read Successfully",
						carSalesServiceImp.getMonthlyCountByYears(year),
						HttpStatus.OK.value()));
	}
}
