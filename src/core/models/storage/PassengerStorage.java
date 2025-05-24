/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.passenger.Passenger;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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

    @Override
    public boolean load() {
        try {
            JSONArray array = JsonStorage.readJson(path);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                long id = obj.getLong("id");
                String firstname = obj.getString("firstname");
                String lastname = obj.getString("lastname");
                LocalDate birthDate = LocalDate.parse(obj.getString("birthDate"));
                int countryPhoneCode = obj.getInt("countryPhoneCode");
                long phone = obj.getLong("phone");
                String country = obj.getString("country");
                Passenger passenger = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
                this.add(passenger);
            }
            return true;
        } catch (JSONException | NumberFormatException e) {
            System.out.println("Error: "+e);
            return false;
        }
    }
    
}
