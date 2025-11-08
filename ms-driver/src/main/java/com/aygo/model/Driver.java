package com.aygo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "drivers")
public class Driver {

    @Id
    private String id;
    private String name;
    private String vehicle;
    private String licenseNumber;

    public Driver() {}

    public Driver(String name, String vehicle, String licenseNumber) {
        this.name = name;
        this.vehicle = vehicle;
        this.licenseNumber = licenseNumber;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getVehicle() { return vehicle; }
    public String getLicenseNumber() { return licenseNumber; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setVehicle(String vehicle) { this.vehicle = vehicle; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
}
