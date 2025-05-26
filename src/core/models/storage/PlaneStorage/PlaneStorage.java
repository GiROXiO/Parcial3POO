/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.PlaneStorage;

import core.models.plane.Plane;
import core.models.storage.JsonTransformer;
import core.models.storage.Storage;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import org.json.JSONArray;
import org.json.JSONException;

public class PlaneStorage extends Storage<Plane>{
     private static PlaneStorage instance;
     public JsonTransformer<Plane> transformer;
     
    private PlaneStorage() {
        super(JsonPath.PLANES.getPath());
        this.transformer = new PlaneJSON();
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
                Plane plane = transformer.fromJson(array.getJSONObject(i));
                this.add(plane);
            }
            
            return true;
        } catch (JSONException | NumberFormatException e) {
            return false;
        }
    }
}
