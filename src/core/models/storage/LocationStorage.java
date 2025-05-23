/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.location.Location;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationStorage extends Storage<Location> {

    private static LocationStorage instance;

    private ArrayList<String> prueba = new ArrayList<>();

    private LocationStorage() {
        super(JsonPath.LOCATIONS.getPath());
    }

    public static LocationStorage getInstance() {
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }

    @Override
    public boolean add(Location obj) {
        for (Location location : this.lista) {
            if (location.getAirportId().equals(obj.getAirportId())) {
                return false;
            }
        }
        this.lista.add(obj);
        return true;
    }

    @Override
    public Location get(String id) {
        for (Location location : this.lista) {
            if (String.valueOf(location.getAirportId()).equalsIgnoreCase(id)) {
                return location;
            }
        }
        return null;
    }

    @Override
    public boolean del(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean load() {
        try {
            JSONArray array = JsonStorage.readJson(path);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String id = obj.getString("airportId");
                String airportName = obj.getString("airportName");
                String airportCity = obj.getString("airportCity");
                String airportCountry = obj.getString("airportCountry");
                Double airportLatitude = obj.getDouble("airportLatitude");
                Double airportLongitude = obj.getDouble("airportLongitude");
                Location location = new Location(id, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
                this.add(location);
            }
            return true;
        } catch (JSONException | NumberFormatException e) {
            System.out.println("Error: "+e);
            return false;
        }
    }
}
