package com.raphlys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raphlys.model.TruckModel;

public interface TruckRepository extends JpaRepository<TruckModel, Long> {

}
