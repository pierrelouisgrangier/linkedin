package com.raphlys.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.raphlys.common.ConverterDtoModel;
import com.raphlys.dto.TruckDto;
import com.raphlys.dto.WheelDto;
import com.raphlys.model.TruckModel;
import com.raphlys.model.WheelModel;
import com.raphlys.repository.TruckRepository;

@Service
public class TruckConverter extends ConverterDtoModel<TruckModel, TruckDto> {
	
	@Autowired
	private TruckRepository repository;
	
	@Autowired
	@Lazy
	private WheelConverter wheelConverter;

	public TruckConverter() {
		super(TruckDto.class, TruckModel.class);
	}

	@Override
	protected TruckDto internalToDto(TruckModel model, List<Class<?>> classes) {
    	TruckDto dto = new TruckDto();
    	dto.setId(model.getId());
    	dto.setBrand(model.getBrand());
    	dto.setName(model.getName());
    	if(classes.contains(WheelDto.class)) {
    		dto.setWheels(wheelConverter.toDtos(model.getWheels(), classes));
    	}
    	return dto;
	}

	@Override
	protected TruckModel internalToModel(TruckDto dto, List<Class<?>> classes) {
    	TruckModel model = new TruckModel();
    	if(dto.getId()!=null) {
    		model = repository.findById(dto.getId()).get();
    	}
    	model.setBrand(dto.getBrand());
    	model.setName(dto.getName());
    	if(classes.contains(WheelModel.class)) {
    		model.setWheels(wheelConverter.toModels(dto.getWheels(), classes));
    	}
    	return model;
	}

}
