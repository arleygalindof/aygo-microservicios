package com.aygo.controller;

import com.aygo.model.Driver;
import com.aygo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService service;

    @GetMapping
    public List<Driver> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Driver create(@RequestBody Driver driver) {
        return service.create(driver);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/")
    public String home() {
        return "Microservicio de conductores activo en puerto 4082 ✅";
    }
}
