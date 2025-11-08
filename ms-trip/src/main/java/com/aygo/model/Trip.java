package com.aygo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trips")
public class Trip {
    @Id
    private String id;
    private String passengerId;
    private String driverId;
    private String origin;
    private String destination;
    private double price;
    private String status;

    // Getters y Setters
    public String getId() { 
        return id; 
    }
    public void setId(String id) { 
        this.id = id; 
    }

    public String getPassengerId() { 
        return passengerId; 
    }
    public void setPassengerId(String passengerId) { 
        this.passengerId = passengerId; 
    }

    public String getDriverId() { 
        return driverId; 
    }
    public void setDriverId(String driverId) { 
        this.driverId = driverId; 
    }

    public String getOrigin() { 
        return origin; 
    }

    public void setOrigin(String origin) { 
        this.origin = origin; 
    }

    public String getDestination() { 
        return destination; 
    }

    public void setDestination(String destination) { 
        this.destination = destination; 
    }

    public double getPrice() { 
        return price; 
    }

    public void setPrice(double price) { 
        this.price = price; 
    }

    public String getStatus() { 
        return status; 
    
    }
    public void setStatus(String status) { 
        this.status = status; 
    }
}
