package com.example.projspring.services;

import com.example.projspring.model.Pet;
import com.example.projspring.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;
    @Autowired
    OwnerService ownerService;

    public List<Pet> listPets() {
        return petRepository.findAll();
    }

    public Pet create(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet update(Pet pet, Long id) {
        if(verifyId(id)) {
            pet.setId(id);
            return petRepository.save(pet);
        }
        return null;
    }

    private boolean verifyId(Long id) {
        if(petRepository.existsById(id)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(Long id) {
        if(verifyId(id)) {
            petRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public int amtPets() {
        return petRepository.findAll().size();
    }

    public Optional<Pet> searchById(Long id) {
        return petRepository.findById(id);
    }
}
