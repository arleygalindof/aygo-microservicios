package com.aygo.controller;

import com.aygo.model.Passenger;
import com.aygo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService service;

    @GetMapping
    public List<Passenger> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Passenger create(@RequestBody Passenger passenger) {
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
