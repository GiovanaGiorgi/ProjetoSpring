package com.example.projspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Entity
public class Hotel { //Encapsulamento
    @NotNull
    private int maxCapacity = 8;
    private int currentCapacity = 0;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Map<Long, Pet> pets = new HashMap<>();
    @Id
    private Long id;

    public Hotel() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public Map<Long, Pet> getPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        if (currentCapacity < maxCapacity) {
            pets.put(pet.getId(), pet);
            currentCapacity++;
        } else {
            throw new RuntimeException("Hotel is at maximum capacity.");
        }
    }

    public void removePet(Pet pet) {
        if (pets.remove(pet.getId()) != null) {
            currentCapacity--;
        } else {
            throw new RuntimeException("Pet not found in hotel.");
        }
    }
}