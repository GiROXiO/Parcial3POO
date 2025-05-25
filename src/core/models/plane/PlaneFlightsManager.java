package core.models.plane;

import core.models.flight.Flight;
import java.util.ArrayList;

public class PlaneFlightsManager implements PlaneFlightsManagerInterface{
    
    private final ArrayList<Flight> flights;
    
    public PlaneFlightsManager()
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
