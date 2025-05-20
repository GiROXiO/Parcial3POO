/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.utils;

/**
 *
 * @author jecas
 */
public enum JsonPath {
    FLIGHTS("json/flights.json"),
    LOCATIONS("json/locations.json"),
    PASSENGERS("json/passengers.json"),
    PLANES("json/planes.json");
    
    private final String path;
    
    private JsonPath(String path){
        this.path = path;
    }
    
    public String getPath(){
        return path;
    }
}
