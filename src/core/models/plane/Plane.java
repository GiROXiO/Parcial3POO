/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.plane;

import core.models.Clonable;
import core.models.flight.Flight;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public class Plane implements Clonable<Plane>{
    
    private final String id;
    private String brand;
    private String model;
    private final int maxCapacity;
    private String airline;
    private PlaneFlightsManagerInterface flightManager;

    public Plane(String id, String brand, String model, int maxCapacity, String airline) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.maxCapacity = maxCapacity;
        this.airline = airline;
        this.flightManager = new PlaneFlightsManager();
    }
    
    
    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getAirline() {
        return airline;
    }
    
    public void addFlight(Flight flight) {
        flightManager.addFlight(flight);
    }

    public ArrayList<Flight> getFlights() {
        return flightManager.getFlights();
    }
    
    public int getNumFlights() {
        return flightManager.getNumFlights();
    }
    
    @Override
    public Plane clone() 
    {
        return new Plane(id, brand, model, maxCapacity, airline);
    }

    @Override
    public String toString() {
        return "Plane{" + "id=" + id + ", brand=" + brand + ", model=" + model + ", maxCapacity=" + maxCapacity + ", airline=" + airline + ", flights=" + flightManager.getFlights() + '}';
    }
}
