/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.plane.Plane;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaneStorage extends Storage<Plane>{
     private static PlaneStorage instance;

    private PlaneStorage() {
        super(JsonPath.PLANES.getPath());
    }
    
    public static PlaneStorage getInstance(){
        if(instance == null){
            instance = new PlaneStorage();
        }
        return instance;
    }
 
    @Override
    public boolean add(Plane obj) {
        for(Plane plane : this.lista){
            if(plane.getId().equals(obj.getId())) return false;
        }
        this.lista.add(obj);
        return true;
    }

    @Override
    public Plane get(String id) {
        for(Plane plane:this.lista){
            if(String.valueOf(plane.getId()).equalsIgnoreCase(id)) return plane;
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
                JSONObject obj = array.getJSONObject(i);
                String id = obj.getString("id");
                String brand = obj.getString("brand");
                String model = obj.getString("model");
                int maxCapacity = obj.getInt("maxCapacity");
                String airline = obj.getString("airline");
                Plane plane = new Plane(id, brand, model, maxCapacity, airline);           
                this.add(plane);
            }
            Collections.sort(this.lista, Comparator.comparing(plane -> {
                return plane.getId();
            }));
            return true;
        } catch (JSONException | NumberFormatException e) {
            return false;
        }
    }
}
