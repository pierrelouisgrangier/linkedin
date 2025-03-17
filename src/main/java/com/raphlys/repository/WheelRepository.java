package com.raphlys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raphlys.model.WheelModel;

public interface WheelRepository extends JpaRepository<WheelModel, Long> {

}
