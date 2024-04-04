package com.example.projspring.services;

import com.example.projspring.model.Owner;
import com.example.projspring.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    public List<Owner> listOwners() {
        return ownerRepository.findAll();
    }

    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner update(Owner owner, Long id) {
        if(verifyId(id)) {
            owner.setId(id);
            return ownerRepository.save(owner);
        }
        return null;
    }

    private boolean verifyId(Long id) {
        if(ownerRepository.existsById(id)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(Long id) {
        if(verifyId(id)) {
            ownerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public int amtOwners() {
        return ownerRepository.findAll().size();
    }

    public Optional<Owner> searchById(Long id) {
        return ownerRepository.findById(id);
    }
}
