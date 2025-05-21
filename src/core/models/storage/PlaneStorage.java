/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.plane.Plane;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class PlaneStorage extends Storage<Plane>{
     private static PlaneStorage instance;
    
    private ArrayList<String> prueba = new ArrayList<>();

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
    public boolean del(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void pruebaJsonStorage(){
        JSONArray array = JsonStorage.readJson(path);
        for (int i = 0; i < array.length();i++) {
            JSONObject obj = array.getJSONObject(i);
            String id = obj.getString("id");
            this.prueba.add(id);
        }
        
        for(String num : this.prueba){
            System.out.println(num);
        }
    }
}
