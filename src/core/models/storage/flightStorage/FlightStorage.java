/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.flightStorage;

import core.models.storage.locationstorage.LocationStorage;
import core.models.storage.PlaneStorage.PlaneStorage;
import core.models.flight.Flight;
import core.models.location.Location;
import core.models.plane.Plane;
import core.models.storage.JsonTransformer;
import core.models.storage.Storage;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jecas
 */
public class FlightStorage extends Storage<Flight> {

    private static FlightStorage instance;
    private JsonTransformer<Flight> transformer;

    private FlightStorage() {
        super(JsonPath.FLIGHTS.getPath());
        this.transformer = new FlightJSON();
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
                Flight flight = transformer.fromJson(array.getJSONObject(i));
                this.add(flight);
            }
            
            return true;
        } catch (JSONException | NumberFormatException e) {
            return false;
        }
    }

}
