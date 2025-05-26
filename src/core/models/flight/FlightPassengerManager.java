/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.flight;

import core.models.passenger.Passenger;
import java.util.ArrayList;

/**
 *
 * @author jecas
 */
public class FlightPassengerManager implements FlightPassengerManagerInterface{
    private final ArrayList<Passenger> passengers;

    public FlightPassengerManager() {
        this.passengers = new ArrayList<>();
    }
    
    @Override
    public void addPassenger(Passenger passenger){
        this.passengers.add(passenger);
    }
    
    @Override
    public int getNumPassengers(){
        return this.passengers.size();
    }

    @Override
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
}
