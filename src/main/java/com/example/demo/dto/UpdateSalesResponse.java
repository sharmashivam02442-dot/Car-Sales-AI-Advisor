package com.example.demo.dto;

public class UpdateSalesResponse {
	
	
	private int totalResponse;
	private int successCount;
	private int failedCount;
	
	 public UpdateSalesResponse(int totalResponse,
             int successCount,
             int failedCount) {
this.totalResponse = totalResponse;
this.successCount = successCount;
this.failedCount = failedCount;

}

	 public int getTotalResponse() {
		 return totalResponse;
	 }

	 public void setTotalResponse(int totalResponse) {
		 this.totalResponse = totalResponse;
	 }

	 public int getSuccessCount() {
		 return successCount;
	 }

	 public void setSuccessCount(int successCount) {
		 this.successCount = successCount;
	 }

	 public int getFailedCount() {
		 return failedCount;
	 }

	 public void setFailedCount(int failedCount) {
		 this.failedCount = failedCount;
	 }
}
