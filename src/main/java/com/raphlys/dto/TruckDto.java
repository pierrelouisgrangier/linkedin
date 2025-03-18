package com.raphlys.dto;

import java.util.List;

import com.raphlys.common.IDto;

public class TruckDto  implements IDto<Long> {
	   // Identifiant unique du camion
    private Long id;

    // Marque du camion
    private String brand;

    // Nom du camion
    private String name;

	private WheelSteeringDto wheelSteering;

	private List<WheelDto> wheels;

	private List<DriverDto> drivers;

    // Getters et setters pour les propriétés de Truck
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public WheelSteeringDto getWheelSteering() {
		return wheelSteering;
	}

	public void setWheelSteering(WheelSteeringDto wheelSteering) {
		this.wheelSteering = wheelSteering;
	}

	public List<WheelDto> getWheels() {
		return wheels;
	}

	public void setWheels(List<WheelDto> wheels) {
		this.wheels = wheels;
	}

	public List<DriverDto> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<DriverDto> drivers) {
		this.drivers = drivers;
	}
    
}
