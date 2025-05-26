/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.Location;

import core.models.location.Location;
import core.models.storage.JsonTransformer;
import core.models.storage.Storage;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationStorage extends Storage<Location> {
    
    private static LocationStorage instance;
    private JsonTransformer<Location> transformer;
    
    private LocationStorage() {
        super(JsonPath.LOCATIONS.getPath());
        this.transformer = new LocationJSON();
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
    public boolean upd(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean load() {
        try {
            JSONArray array = JsonStorage.readJson(path);
            for (int i = 0; i < array.length(); i++) {
                Location location = transformer.fromJson(array.getJSONObject(i));
                this.add(location);
            }
            
            return true;
        } catch (JSONException | NumberFormatException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
}
