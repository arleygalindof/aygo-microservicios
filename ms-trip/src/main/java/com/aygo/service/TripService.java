package com.aygo.service;

import com.aygo.model.Trip;
import com.aygo.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    
    @Autowired
    private TripRepository repository;

    public List<Trip> getAll() {
        return repository.findAll();
    }

    public Trip create(Trip trip) {
        return repository.save(trip);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
