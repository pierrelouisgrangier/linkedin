package com.raphlys.dto;

public class TruckDto {
	   // Identifiant unique du camion
    private Long id;

    // Marque du camion
    private String brand;

    // Nom du camion
    private String name;

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
}
