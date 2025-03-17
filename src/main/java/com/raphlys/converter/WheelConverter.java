package com.raphlys.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raphlys.common.ConverterDtoModel;
import com.raphlys.dto.TruckDto;
import com.raphlys.dto.WheelDto;
import com.raphlys.model.TruckModel;
import com.raphlys.model.WheelModel;
import com.raphlys.repository.WheelRepository;

@Service
public class WheelConverter extends ConverterDtoModel<WheelModel, WheelDto>{
	
	@Autowired
	private WheelRepository repository;
	
	@Autowired
	private TruckConverter truckConverter;

	public WheelConverter() {
		super(WheelDto.class, WheelModel.class);
	}

	@Override
	protected WheelDto internalToDto(WheelModel model, List<Class<?>> classes) {
		WheelDto dto = new WheelDto();
		dto.setBrand(model.getBrand());
		dto.setId(model.getId());
		dto.setSize(model.getSize());
		if(classes.contains(TruckDto.class)) {
			dto.setTruck(truckConverter.toDto(model.getTruck(), classes));	
		}
		return dto;
	}

	@Override
	protected WheelModel internalToModel(WheelDto dto, List<Class<?>> classes) {
		WheelModel model = new WheelModel();
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
