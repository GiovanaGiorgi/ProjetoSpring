package com.example.projspring.repository;

import com.example.projspring.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long>{} //Abstratos