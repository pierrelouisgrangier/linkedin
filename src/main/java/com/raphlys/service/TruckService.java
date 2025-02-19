package com.raphlys.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raphlys.dto.TruckDto;
import com.raphlys.model.TruckModel;
import com.raphlys.repository.TruckRepository;

@Service
public class TruckService {

	
	@Autowired
	private TruckRepository truckRepository;
	
    /**
     * Récupère tous les camions.
     * @return une collection de tous les camions.
     */
    public Collection<TruckDto> getAll() {
        return truckRepository.findAll().stream().map(truck -> toDto(truck)).toList();
    }

    /**
     * Crée un nouveau camion.
     * @param truck les détails du camion à créer.
     * @return l'identifiant du camion créé.
     */
    public Long create(TruckDto truck) {
        return truckRepository.save(toModel(truck)).getId();
    }

    /**
     * Met à jour un camion existant.
     * @param truck les détails du camion à mettre à jour.
     * @return l'ancien camion associé à l'identifiant donné.
     */
    public TruckDto update(TruckDto truck) {
    	return toDto(truckRepository.save(toModel(truck)));
    }

    /**
     * Supprime un camion par son identifiant.
     * @param id l'identifiant du camion à supprimer.
     * @return true si le camion a été supprimé, false sinon.
     */
    public boolean delete(Long id) {
    	truckRepository.deleteById(id);
        return true;
    }
    
    private TruckDto toDto(TruckModel model) {
    	TruckDto dto = new TruckDto();
    	dto.setId(model.getId());
    	dto.setBrand(model.getBrand());
    	dto.setName(model.getName());
    	return dto;
    }
    
    private TruckModel toModel(TruckDto dto) {
    	TruckModel model = new TruckModel();
    	model.setId(dto.getId());
    	model.setBrand(dto.getBrand());
    	model.setName(dto.getName());
    	return model;
    }
}
