package com.aygo.service;

import com.aygo.model.Passenger;
import com.aygo.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository repository;

    public List<Passenger> getAll() {
        return repository.findAll();
    }

    public Passenger create(Passenger passenger) {
        return repository.save(passenger);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
