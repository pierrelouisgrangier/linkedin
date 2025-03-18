package com.raphlys.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.raphlys.common.ConverterDtoModel;
import com.raphlys.dto.TruckDto;
import com.raphlys.dto.WheelSteeringDto;
import com.raphlys.model.TruckModel;
import com.raphlys.model.WheelSteeringModel;

@Service
public class WheelSteeringConverter extends ConverterDtoModel<WheelSteeringModel, WheelSteeringDto, Long>{
	
	@Autowired
	private JpaRepository<WheelSteeringModel, Long> repository;
	
	@Autowired
	private TruckConverter truckConverter;

	public WheelSteeringConverter() {
		super(WheelSteeringDto.class, WheelSteeringModel.class);
	}

	@Override
	protected WheelSteeringDto internalToDto(WheelSteeringModel model, List<Class<?>> classes) {
		WheelSteeringDto dto = new WheelSteeringDto();
		dto.setBrand(model.getBrand());
		dto.setId(model.getId());
		dto.setSize(model.getSize());
		if(classes.contains(TruckDto.class)) {
			dto.setTruck(truckConverter.toDto(model.getTruck(), classes));	
		}
		return dto;
	}

	@Override
	protected WheelSteeringModel internalToModel(WheelSteeringDto dto, List<Class<?>> classes) {
		WheelSteeringModel model = new WheelSteeringModel();
		if(dto.getId()!=null) {
			model = repository.findById(dto.getId()).get();
		}
		model.setBrand(dto.getBrand());
		model.setSize(dto.getSize());
		if(classes.contains(TruckModel.class)) {
			model.setTruck(truckConverter.toModel(dto.getTruck(), classes));	
		}
		return model;
	}

}
