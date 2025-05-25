package core.models.passenger;

import core.models.flight.Flight;
import java.util.ArrayList;

public interface PFlightManagerInterface {
    
    void addFlight(Flight flight);
    int getNumFlights();
    ArrayList<Flight> getFlights();
    
}
