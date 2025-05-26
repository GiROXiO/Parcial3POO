/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.flight;

import core.models.passenger.Passenger;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author jecas
 */
public interface FlightInterface {
    public void addPassenger(Passenger passenger);
    public ArrayList<Passenger> getPassengers();
    public int getNumPassengers();
    public LocalDateTime calculateArrivalDate();
    public void delay(int hours, int minutes);
}
