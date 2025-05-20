/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.flight.Flight;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jecas
 */
public class FlightStorage extends Storage<Flight>{
    
    private static FlightStorage instance;
    
    private ArrayList<String> prueba = new ArrayList<>();

    private FlightStorage() {
        super(JsonPath.FLIGHTS.getPath());
    }
    
    public static FlightStorage getInstance(){
        if(instance == null){
            instance = new FlightStorage();
        }
        return instance;
    }
 
    @Override
    public boolean add(Flight obj) {
        for(Flight flight : this.lista){
            if(flight.getId().equalsIgnoreCase(obj.getId())) return false;
        }
        this.lista.add(obj);
        return true;
    }

    @Override
    public Flight get(String id) {
        for(Flight flight:this.lista){
            if(flight.getId().equalsIgnoreCase(id)) return flight;
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
    
    public boolean updateDeparture(String id, int hoursDelay, int minutesDelay) {
        for(Flight flight : this.lista){
            if(flight.getId().equalsIgnoreCase(id)){
                flight.delay(hoursDelay, minutesDelay);
                return true;
            }
        }
        return false;
    }
    
    
}
