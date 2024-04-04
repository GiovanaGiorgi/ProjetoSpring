package com.example.projspring.controller;

import com.example.projspring.model.Pet;
import com.example.projspring.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<Pet> listPets() {
        return petService.listPets();
    }

    @PostMapping
    public Pet create(@Valid @RequestBody Pet pet) {
        return petService.create(pet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Pet pet, @PathVariable Long id) {
        Optional<Pet> petOptional = petService.searchById(id);
        if (!petOptional.isPresent()) {
            String message = "O id informado não existe";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            Pet updatedPet = petService.update(pet, id);
            return ResponseEntity.ok(updatedPet);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(petService.delete(id)) {
            String message = "O id " + id + " foi removido com sucesso.";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            String message = "O id informado não existe";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @GetMapping("/amt")
    public int amtPets() {
        return petService.amtPets();
    }
}
