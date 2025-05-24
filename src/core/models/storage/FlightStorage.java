/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.flight.Flight;
import core.models.location.Location;
import core.models.plane.Plane;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jecas
 */
public class FlightStorage extends Storage<Flight> {

    private static FlightStorage instance;

    private FlightStorage() {
        super(JsonPath.FLIGHTS.getPath());
    }

    public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }

    @Override
    public boolean add(Flight obj) {
        for (Flight flight : this.lista) {
            if (flight.getId().equalsIgnoreCase(obj.getId())) {
                return false;
            }
        }
        this.lista.add(obj);
        return true;
    }

    @Override
    public Flight get(String id) {
        for (Flight flight : this.lista) {
            if (flight.getId().equalsIgnoreCase(id)) {
                return flight;
            }
        }
        return null;
    }
    
    @Override
    public boolean upd(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean updateDeparture(String id, int hoursDelay, int minutesDelay) {
        for (Flight flight : this.lista) {
            if (flight.getId().equalsIgnoreCase(id)) {
                flight.delay(hoursDelay, minutesDelay);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean load() {
        try {
            JSONArray array = JsonStorage.readJson(path);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String id = obj.getString("id");
                Plane plane = PlaneStorage.getInstance().get(obj.getString("plane"));
                Location departureLocation = LocationStorage.getInstance().get(obj.getString("departureLocation"));
                Location arrivalLocation = LocationStorage.getInstance().get(obj.getString("arrivalLocation"));
                Location scaleLocation;
                if (obj.isNull("scaleLocation")) {
                    scaleLocation = null;
                } else {
                    scaleLocation = LocationStorage.getInstance().get(obj.getString("scaleLocation"));
                }
                LocalDateTime departureDate = LocalDateTime.parse(obj.getString("departureDate"));
                int hoursDurationArrival = obj.getInt("hoursDurationArrival");
                int minutesDurationArrival = obj.getInt("minutesDurationArrival");
                int hoursDurationScale = obj.getInt("hoursDurationScale");
                int minutesDurationScale = obj.getInt("minutesDurationScale");
                if (scaleLocation == null) {
                    Flight flight = new Flight(id, plane, departureLocation, arrivalLocation, departureDate, hoursDurationArrival, minutesDurationArrival);
                    this.add(flight);
                } else {
                    Flight flight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
                    this.add(flight);
                }
            }
            Collections.sort(this.lista, Comparator.comparing(flight -> {
                return flight.getDepartureDate();
            }));
            return true;
        } catch (JSONException | NumberFormatException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

}
