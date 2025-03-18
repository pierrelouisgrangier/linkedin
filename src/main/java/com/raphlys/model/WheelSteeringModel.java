package com.raphlys.model;

import com.raphlys.common.IModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity()
public class WheelSteeringModel implements IModel<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String brand;

	@Column(unique = true)
	private String size;

	@OneToOne(mappedBy = "wheelSteering")
	private TruckModel truck;

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

	public TruckModel getTruck() {
		return truck;
	}

	public void setTruck(TruckModel truck) {
		this.truck = truck;
	}

}