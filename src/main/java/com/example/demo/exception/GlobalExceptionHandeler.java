package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.dto.UpdateSalesResponse;

@ControllerAdvice
public class GlobalExceptionHandeler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<UpdateSalesResponse>> handleAllException(Exception e) {
		
		UpdateSalesResponse response = new UpdateSalesResponse(0,0,0);
		
		ApiResponse<UpdateSalesResponse> apiresponse = new ApiResponse<UpdateSalesResponse>(
				false,e.getMessage(),
				response,
				HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<ApiResponse<UpdateSalesResponse>>(apiresponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//** STUDY BUILDER_PATTERN AGAIN **
	//** STUDY RECORDS AGAIN **
	}

