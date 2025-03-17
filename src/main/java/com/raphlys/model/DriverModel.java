package com.raphlys.model;

import java.util.List;

import com.raphlys.common.IModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity()
public class DriverModel implements IModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(name = "driver_truck", 
	joinColumns = @JoinColumn(name = "driver_id"), 
	inverseJoinColumns = @JoinColumn(name = "truck_id"))
	private List<TruckModel> trucks;

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

	public List<TruckModel> getTrucks() {
		return trucks;
	}

	public void setTrucks(List<TruckModel> trucks) {
		this.trucks = trucks;
	}

}
