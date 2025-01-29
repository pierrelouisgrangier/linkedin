package com.raphlys.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST pour gérer les opérations CRUD sur des objets Truck.
 */
@RestController()
@RequestMapping("raph")
public class RaphlysController {

    // Dernier identifiant utilisé pour un camion
    private Long last_id = 0l;

    // Map pour stocker les camions avec leur identifiant
    private final Map<Long, Truck> TRUCKS = new HashMap<>();

    /**
     * Récupère tous les camions.
     * @return une collection de tous les camions.
     */
    @GetMapping("all")
    public Collection<Truck> getAll() {
        return TRUCKS.values();
    }

    /**
     * Crée un nouveau camion.
     * @param truck les détails du camion à créer.
     * @return l'identifiant du camion créé.
     */
    @PostMapping()
    public Long createTruck(@RequestBody() Truck truck) {
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
    @PutMapping()
    public Truck updateTruck(@RequestBody() Truck truck) {
        return TRUCKS.put(truck.getId(), truck);
    }

    /**
     * Supprime un camion par son identifiant.
     * @param id l'identifiant du camion à supprimer.
     * @return true si le camion a été supprimé, false sinon.
     */
    @DeleteMapping("{id}")
    public boolean deleteTruck(@PathVariable("id") Long id) {
        return TRUCKS.remove(id) != null;
    }

    /**
     * Classe interne représentant un camion.
     */
    public static class Truck {

        // Identifiant unique du camion
        private Long id;

        // Marque du camion
        private String brand;

        // Nom du camion
        private String name;

        // Getters et setters pour les propriétés de Truck
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
