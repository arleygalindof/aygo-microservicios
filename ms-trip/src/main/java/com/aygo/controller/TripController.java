package com.aygo.controller;

import com.aygo.model.Trip;
import com.aygo.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService service;

    @GetMapping
    public List<Trip> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Trip create(@RequestBody Trip passenger) {
        return service.create(passenger);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/")
    public String home() {
        return "Microservicio de pasajeros activo en puerto 4081 ✅";
    }
}
