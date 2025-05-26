/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.passengerStorage;

import core.models.passenger.Passenger;
import core.models.storage.JsonTransformer;
import core.models.storage.Storage;
import core.models.storage.utils.JsonPath;
import core.models.storage.utils.JsonStorage;
import org.json.JSONArray;
import org.json.JSONException;

public class PassengerStorage extends Storage<Passenger> {

    private static PassengerStorage instance;
    private JsonTransformer<Passenger> transformer;

    private PassengerStorage() {
        super(JsonPath.PASSENGERS.getPath());
        this.transformer = new PassengerJSON();
    }

    public static PassengerStorage getInstance() {
        if (instance == null) {
            instance = new PassengerStorage();
        }
        return instance;
    }

    @Override
    public boolean add(Passenger obj) {
        for (Passenger passenger : this.lista) {
            if (passenger.getId() == obj.getId()) {
                return false;
            }
        }
        this.lista.add(obj);
        return true;
    }

    @Override
    public Passenger get(String id) {
        for (Passenger passenger : this.lista) {
            if (String.valueOf(passenger.getId()).equalsIgnoreCase(id)) {
                return passenger;
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
                Passenger passenger = transformer.fromJson(array.getJSONObject(i));
                this.add(passenger);
            }
            
            return true;
        } catch (JSONException | NumberFormatException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
}
