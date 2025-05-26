/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.flightStorage;

import core.models.flight.Flight;
import core.models.location.Location;
import core.models.plane.Plane;
import core.models.storage.JsonTransformer;
import core.models.storage.PlaneStorage.PlaneStorage;
import core.models.storage.locationstorage.LocationStorage;
import java.time.LocalDateTime;
import org.json.JSONObject;

/**
 *
 * @author jecas
 */
public class FlightJSON implements JsonTransformer<Flight> {

    @Override
    public Flight fromJson(JSONObject obj) {
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
            return new Flight(id, plane, departureLocation, arrivalLocation, departureDate, hoursDurationArrival, minutesDurationArrival);
        } else {
            return new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
        }
    }

}
