package core.models.storage.locationstorage;

import core.models.location.Location;
import core.models.storage.JsonTransformer;
import org.json.JSONObject;

public class LocationJSON implements JsonTransformer<Location> {
    
    @Override
    public Location fromJson(JSONObject obj)
    {
        String id = obj.getString("airportId");
        String airportName = obj.getString("airportName");
        String airportCity = obj.getString("airportCity");
        String airportCountry = obj.getString("airportCountry");
        Double airportLatitude = obj.getDouble("airportLatitude");
        Double airportLongitude = obj.getDouble("airportLongitude");
        
        
        return new Location(id, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
    }
    
}
