/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.flight;

import core.models.Clonable;
import core.models.location.Location;
import core.models.passenger.Passenger;
import core.models.plane.Plane;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public class Flight implements Clonable<Flight>, FlightInterface{
    
    private final String id;
    private Plane plane;
    private final Location departureLocation;
    private Location scaleLocation;
    private final Location arrivalLocation;
    private final FlightPassengerManager flightPM;
    private final FlightDateManager flightDM;

    public Flight(String id, Plane plane, Location departureLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        this.id = id;
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        
        this.flightPM = new FlightPassengerManager();
        this.flightDM = new FlightDateManager(departureDate, hoursDurationArrival, minutesDurationArrival);
        this.plane.addFlight(this);
    }

    public Flight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival, int hoursDurationScale, int minutesDurationScale) {
        this.id = id;
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.scaleLocation = scaleLocation;
        this.arrivalLocation = arrivalLocation;
        
        this.flightPM = new FlightPassengerManager();
        this.flightDM = new FlightDateManager(departureDate, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
        this.plane.addFlight(this);
    }

    public String getId() {
        return id;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public Location getScaleLocation() {
        return scaleLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public Plane getPlane() {
        return plane;
    }

    // SRP Pasajeros del vuelo
    @Override
    public void addPassenger(Passenger passenger){
        this.flightPM.addPassenger(passenger);
    }
    
    @Override
    public ArrayList<Passenger> getPassengers() {
        return this.flightPM.getPassengers();
    }

    @Override
    public int getNumPassengers() {
        return this.flightPM.getNumPassengers();
    }
    
    //SRP fechas de salida y llegada del vuelo
    @Override
    public LocalDateTime calculateArrivalDate() {
        return this.flightDM.calculateArrivalDate();
    }

    @Override
    public void delay(int hours, int minutes) {
        this.flightDM.delay(hours, minutes);
    }

    public FlightPassengerManager getFlightPM() {
        return flightPM;
    }

    public FlightDateManager getFlightDM() {
        return flightDM;
    }
    
    @Override
    public Flight clone(){
        return new Flight(
                this.id, 
                this.plane, 
                this.departureLocation, 
                this.scaleLocation, 
                this.arrivalLocation, 
                this.flightDM.getDepartureDate(),
                this.flightDM.getHoursDurationArrival(), 
                this.flightDM.getMinutesDurationArrival(), 
                this.flightDM.getHoursDurationScale(), 
                this.flightDM.getMinutesDurationScale());
    }
}
