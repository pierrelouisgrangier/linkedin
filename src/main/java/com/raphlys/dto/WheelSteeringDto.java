package com.raphlys.dto;

import com.raphlys.common.IDto;

public class WheelSteeringDto implements IDto<Long> {

	private Long id;

	private String brand;

	private String size;

	private TruckDto truck;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public TruckDto getTruck() {
		return truck;
	}

	public void setTruck(TruckDto truck) {
		this.truck = truck;
	}

}