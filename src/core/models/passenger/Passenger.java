/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.passenger;

import core.models.Clonable;
import core.models.flight.Flight;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public class Passenger implements Clonable<Passenger>{
    
    private final long id;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private PhoneNumberInterface phoneNumber;
    private String country;
    private PFlightManagerInterface flightManager;

    public Passenger(long id, String firstname, String lastname, LocalDate birthDate, PhoneNumberInterface phoneNumber, String country) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.flightManager = new PFlightManager(); // Para que inicie vacio
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public PhoneNumberInterface getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNumber(PhoneNumberInterface phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addFlight(Flight flight)
    {
        flightManager.addFlight(flight);
    }
    
    public int getNumFlights()
    {
        return flightManager.getNumFlights();
    }
    
    public ArrayList<Flight> getFlights()
    {
        return flightManager.getFlights();
    }
    
    public String getFullName()
    {
        return PassengerUtils.getFullName(this);
    }
    
    public int calculateAge()
    {
        return PassengerUtils.calculateAge(this);
    }
    
    public String getFormattedPhone()
    {
        return phoneNumber.getFullPhone();
    }
    
    
    @Override
    public Passenger clone(){
        Passenger passenger = new Passenger(id, firstname, lastname, birthDate, phoneNumber, country);
        for (Flight flight: this.getFlights())
        {
            passenger.addFlight(flight);
        }
        return passenger;
    }

}
