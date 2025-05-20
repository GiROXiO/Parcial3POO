/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.passenger.Passenger;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author jecas
 */
public class PassengerStorage extends Storage<Passenger>{
    
    private static PassengerStorage instance;
    
    private ArrayList<String> prueba = new ArrayList<>();

    private PassengerStorage() {
        super(JsonPath.PASSENGERS.getPath());
    }
    
    public static PassengerStorage getInstance(){
        if(instance == null){
            instance = new PassengerStorage();
        }
        return instance;
    }
 
    @Override
    public boolean add(Passenger obj) {
        for(Passenger passenger : this.lista){
            if(passenger.getId() == obj.getId()) return false;
        }
        this.lista.add(obj);
        return true;
    }

    @Override
    public Passenger get(String id) {
        for(Passenger passenger:this.lista){
            if(String.valueOf(passenger.getId()).equalsIgnoreCase(id)) return passenger;
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
