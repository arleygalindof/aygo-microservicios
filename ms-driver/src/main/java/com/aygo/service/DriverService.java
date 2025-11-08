package com.aygo.service;

import com.aygo.model.Driver;
import com.aygo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    
    @Autowired
    private DriverRepository repository;

    public List<Driver> getAll() {
        return repository.findAll();
    }

    public Driver create(Driver Driver) {
        return repository.save(Driver);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
