package com.example.projspring.controller;

import com.example.projspring.model.Pet;
import com.example.projspring.services.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/checkin")
    public ResponseEntity<?> checkIn(@Valid @RequestBody Pet pet) {
        try {
            hotelService.checkIn(pet);
            return ResponseEntity.status(HttpStatus.OK).body("Pet checked in successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkOut(@Valid @RequestBody Pet pet) {
        try {
            hotelService.checkOut(pet);
            return ResponseEntity.status(HttpStatus.OK).body("Pet checked out successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}