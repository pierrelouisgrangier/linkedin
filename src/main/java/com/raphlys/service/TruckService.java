package com.raphlys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raphlys.common.CrudService;
import com.raphlys.dto.TruckDto;
import com.raphlys.dto.WheelDto;
import com.raphlys.dto.WheelSteeringDto;
import com.raphlys.model.TruckModel;
import com.raphlys.model.WheelModel;
import com.raphlys.model.WheelSteeringModel;

@Service
public class TruckService extends CrudService<TruckDto, TruckModel, Long> {	
    
    @Override
    public List<Class<?>> getPropertiesModel() {
    	return List.of(WheelModel.class, WheelSteeringModel.class);
    }
    
    @Override
    public List<Class<?>> getPropertiesCreateDto() {
    	return List.of(WheelDto.class, WheelSteeringDto.class);
    }
    
    @Override
    public List<Class<?>> getPropertiesReadDto() {
    	return List.of(WheelDto.class, WheelSteeringDto.class);
    }

}
