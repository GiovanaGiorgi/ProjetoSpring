package com.example.projspring.controller;

import com.example.projspring.model.Owner;
import com.example.projspring.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {

   private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<Owner> listOwners() {
        return ownerService.listOwners();
    }

    @PostMapping
    public Owner create(@Valid @RequestBody Owner owner) {
        return ownerService.create(owner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Owner owner, @PathVariable Long id) {
        Optional<Owner> ownerOptional = ownerService.searchById(id);
        if (!ownerOptional.isPresent()) {
            String message = "O id informado não existe";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            Owner updatedOwner = ownerService.update(owner, id);
            return ResponseEntity.ok(updatedOwner);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(ownerService.delete(id)) {
            String message = "O id " + id + " foi removido com sucesso.";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            String message = "O id informado não existe";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @GetMapping("/amt")
    public int amtOwners() {
        return ownerService.amtOwners();
    }

    @GetMapping("/{id}")
    public Optional<Owner> searchById(@PathVariable Long id) {
        return ownerService.searchById(id);
    }
}
