package core.models.plane;

import core.models.flight.Flight;
import java.util.ArrayList;

public interface PlaneFlightsManagerInterface {
    void addFlight(Flight flight);
    int getNumFlights();
    ArrayList<Flight> getFlights();
}
