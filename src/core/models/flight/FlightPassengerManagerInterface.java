/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.flight;

import core.models.passenger.Passenger;
import java.util.ArrayList;

/**
 *
 * @author jecas
 */
public interface FlightPassengerManagerInterface {
    public void addPassenger(Passenger passenger);
    public int getNumPassengers();
    public ArrayList<Passenger> getPassengers();
}
