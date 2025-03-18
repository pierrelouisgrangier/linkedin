package com.raphlys.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raphlys.dto.TruckDto;
import com.raphlys.service.TruckService;

/**
 * Contrôleur REST pour gérer les opérations CRUD sur des objets Truck.
 */
@RestController()
@RequestMapping("raph")
public class RaphlysController {
	
	@Autowired
	private TruckService truckService;

    /**
     * Récupère tous les camions.
     * @return une collection de tous les camions.
     */
    @GetMapping("all")
    public Collection<TruckDto> findAll() {
        return truckService.findAll();
    }

    /**
     * Crée un nouveau camion.
     * @param truck les détails du camion à créer.
     * @return l'identifiant du camion créé.
     */
    @PostMapping()
    public TruckDto createTruck(@RequestBody() TruckDto truck) {
        return truckService.create(truck);
    }

    /**
     * Met à jour un camion existant.
     * @param truck les détails du camion à mettre à jour.
     * @return l'ancien camion associé à l'identifiant donné.
     */
    @PutMapping()
    public TruckDto updateTruck(@RequestBody() TruckDto truck) {
        return truckService.update(truck);
    }

    /**
     * Supprime un camion par son identifiant.
     * @param id l'identifiant du camion à supprimer.
     * @return true si le camion a été supprimé, false sinon.
     */
    @DeleteMapping("{id}")
    public boolean deleteTruck(@PathVariable("id") Long id) {
        return truckService.delete(id);
    }

}
