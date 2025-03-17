package com.raphlys.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raphlys.converter.TruckConverter;
import com.raphlys.dto.TruckDto;
import com.raphlys.model.WheelModel;
import com.raphlys.repository.TruckRepository;

@Service
public class TruckService {

	
	@Autowired
	private TruckRepository truckRepository;
	
	@Autowired
	private TruckConverter truckConverter;
	
    /**
     * Récupère tous les camions.
     * @return une collection de tous les camions.
     */
    public Collection<TruckDto> getAll() {
        return truckRepository.findAll().stream().map(truck -> truckConverter.toDto(truck)).toList();
    }

    /**
     * Crée un nouveau camion.
     * @param truck les détails du camion à créer.
     * @return l'identifiant du camion créé.
     */
    public Long create(TruckDto truck) {
        return truckRepository.save(truckConverter.toModel(truck, List.of(WheelModel.class))).getId();
    }

    /**
     * Met à jour un camion existant.
     * @param truck les détails du camion à mettre à jour.
     * @return l'ancien camion associé à l'identifiant donné.
     */
    public TruckDto update(TruckDto truck) {
    	return truckConverter.toDto(truckRepository.save(truckConverter.toModel(truck)));
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
    
}
