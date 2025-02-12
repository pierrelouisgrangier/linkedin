package com.raphlys.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.raphlys.dto.TruckDto;

@Service
public class TruckService {

	// Dernier identifiant utilisé pour un camion
    private Long last_id = 0l;

    // Map pour stocker les camions avec leur identifiant
    private final Map<Long, TruckDto> TRUCKS = new HashMap<>();

    /**
     * Récupère tous les camions.
     * @return une collection de tous les camions.
     */
    public Collection<TruckDto> getAll() {
        return TRUCKS.values();
    }

    /**
     * Crée un nouveau camion.
     * @param truck les détails du camion à créer.
     * @return l'identifiant du camion créé.
     */
    public Long create(TruckDto truck) {
        truck.setId(last_id);
        TRUCKS.put(truck.getId(), truck);
        last_id++;
        return truck.getId();
    }

    /**
     * Met à jour un camion existant.
     * @param truck les détails du camion à mettre à jour.
     * @return l'ancien camion associé à l'identifiant donné.
     */
    public TruckDto update(TruckDto truck) {
        return TRUCKS.put(truck.getId(), truck);
    }

    /**
     * Supprime un camion par son identifiant.
     * @param id l'identifiant du camion à supprimer.
     * @return true si le camion a été supprimé, false sinon.
     */
    public boolean delete(Long id) {
        return TRUCKS.remove(id) != null;
    }
}
