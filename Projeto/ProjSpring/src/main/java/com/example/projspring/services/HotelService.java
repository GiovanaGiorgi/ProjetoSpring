package com.example.projspring.services;

import com.example.projspring.model.Hotel;
import com.example.projspring.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    Hotel hotel;

    public void checkIn(Pet pet) {
        hotel.addPet(pet);
    }

    public void checkOut(Pet pet) {
        hotel.removePet(pet);
    }
}
