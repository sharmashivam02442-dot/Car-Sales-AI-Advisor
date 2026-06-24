package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="car_sales")
public class CarSales {

	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "car_number")
	    private String carNumber;

	    @Column(name = "brand")
	    private String brand;

	    @Column(name = "model")
	    private String model;

	    @Column(name = "color")
	    private String color;

	    @Column(name = "year")
	    private Integer year;

	    @Column(name = "date_of_purchase")
	    private LocalDate dateOfPurchase;

	    @Column(name = "time_of_purchase")
	    private LocalTime timeOfPurchase;

	    @Column(name = "price_rs")
	    private Long priceRs;

	    @Column(name = "mileage")
	    private Double mileage;

	    @Column(name = "engine_cc")
	    private Integer engineCc;

	    @Column(name = "fuel_type")
	    private String fuelType;

	    @Column(name = "payment_mode")
	    private String paymentMode;

	    @Column(name = "state")
	    private String state;

	    @Column(name = "city")
	    private String city;

	    @Column(name = "customer_name")
	    private String customerName;

	    @Column(name = "contact_no")
	    private String contactNo;

	    @Column(name = "email")
	    private String email;

	    @Column(name = "warranty_period_years")
	    private Integer warrantyPeriodYears;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCarNumber() {
			return carNumber;
		}

		public void setCarNumber(String carNumber) {
			this.carNumber = carNumber;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}

		public LocalDate getDateOfPurchase() {
			return dateOfPurchase;
		}

		public void setDateOfPurchase(LocalDate dateOfPurchase) {
			this.dateOfPurchase = dateOfPurchase;
		}

		public LocalTime getTimeOfPurchase() {
			return timeOfPurchase;
		}

		public void setTimeOfPurchase(LocalTime timeOfPurchase) {
			this.timeOfPurchase = timeOfPurchase;
		}

		public Long getPrice() {
			return priceRs;
		}

		public void setPrice(Long priceRs) {
			this.priceRs = priceRs;
		}

		public Double getMileage() {
			return mileage;
		}

		public void setMileage(Double mileage) {
			this.mileage = mileage;
		}

		public Integer getEngineCc() {
			return engineCc;
		}

		public void setEngineCc(Integer engineCc) {
			this.engineCc = engineCc;
		}

		public String getFuelType() {
			return fuelType;
		}

		public void setFuelType(String fuelType) {
			this.fuelType = fuelType;
		}

		public String getPaymentMode() {
			return paymentMode;
		}

		public void setPaymentMode(String paymentMode) {
			this.paymentMode = paymentMode;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getContactNumber() {
			return contactNo;
		}

		public void setContactNumber(String contactNo) {
			this.contactNo = contactNo;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getWarrantyPeriodYears() {
			return warrantyPeriodYears;
		}

		public void setWarrantyPeriodYears(Integer warrantyPeriodYears) {
			this.warrantyPeriodYears = warrantyPeriodYears;
		}
	    
	    
	}

