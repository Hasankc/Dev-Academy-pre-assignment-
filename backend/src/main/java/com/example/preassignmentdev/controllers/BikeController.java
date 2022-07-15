package com.example.preassignmentdev.controllers;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.preassignmentdev.entities.Bike;
import com.example.preassignmentdev.repository.BikeRepository;

@RestController // specifies that this class will determine REST API endpoints
@CrossOrigin(origins = "http://localhost:3000")
public class BikeController {

    @Autowired
    BikeRepository bikeRepository;

    @GetMapping("/bike")
    Bike getRandomBike() {
        Random random = new Random();
        Long id = random.nextLong((10 - 1) + 1) + 1;
        return bikeRepository.findById(id).get();
    }

    @GetMapping("/allbikes")
    public Page<Bike> getBikes(@RequestParam(defaultValue = "0") String page) {
        // always request a page with 10 elements
        Pageable pageable = PageRequest.of(Integer.parseInt(page), 10);
        return bikeRepository.findAll(pageable);
    }
}
