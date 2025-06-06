package com.raphlys.dto;

import java.util.List;

import com.raphlys.common.IDto;

public class DriverDto implements IDto<Long> {

	private Long id;

	private String name;

	private List<TruckDto> trucks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TruckDto> getTrucks() {
		return trucks;
	}

	public void setTrucks(List<TruckDto> trucks) {
		this.trucks = trucks;
	}

}
