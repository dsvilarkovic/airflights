package com.isa.airflights.dto;


public class AirlineDTO {
	private Long id;
	private String fullName;
	private String promoInfo;
	private Double longitude;
	private Double latitude;
	
	private Double gradeSum = 0.0;	
	private Integer gradeCount = 0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPromoInfo() {
		return promoInfo;
	}
	public void setPromoInfo(String promoInfo) {
		this.promoInfo = promoInfo;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getGradeSum() {
		return gradeSum;
	}
	public void setGradeSum(Double gradeSum) {
		this.gradeSum = gradeSum;
	}
	public Integer getGradeCount() {
		return gradeCount;
	}
	public void setGradeCount(Integer gradeCount) {
		this.gradeCount = gradeCount;
	}
	
	
	
	

}