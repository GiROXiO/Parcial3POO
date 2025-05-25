package core.models.passenger;

import core.models.flight.Flight;
import java.util.ArrayList;

public class PFlightManager implements PFlightManagerInterface{
    
    private ArrayList<Flight> flights;
    
    public PFlightManager()
    {
        this.flights = new ArrayList<>();
    }
    
    @Override
    public void addFlight(Flight flight)
    {
        flights.add(flight);
    }
    
    @Override
    public int getNumFlights()
    {
        return flights.size();
    }
    
    @Override
    public ArrayList<Flight> getFlights()
    {
        return flights;
    }
    
}
