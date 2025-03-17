package com.raphlys.model;

import java.util.List;

import com.raphlys.common.IModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "truck")
public class TruckModel implements IModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String brand;

	@Column(unique = true)
	private String name;

	@OneToOne(optional = false)
	@JoinColumn(name = "wheel_steering_id")
	private WheelSteeringModel wheelSteering;

	@OneToMany(mappedBy = "truck")
	private List<WheelModel> wheels;

	@ManyToMany(mappedBy = "trucks")
	private List<DriverModel> drivers;

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

	public WheelSteeringModel getWheelSteering() {
		return wheelSteering;
	}

	public void setWheelSteering(WheelSteeringModel wheelSteering) {
		this.wheelSteering = wheelSteering;
	}

	public List<WheelModel> getWheels() {
		return wheels;
	}

	public void setWheels(List<WheelModel> wheels) {
		this.wheels = wheels;
	}

	public List<DriverModel> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<DriverModel> drivers) {
		this.drivers = drivers;
	}

}
