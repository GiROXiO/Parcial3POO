package core.models.storage.PlaneStorage;

import core.models.plane.Plane;
import core.models.storage.JsonTransformer;
import org.json.JSONObject;

public class PlaneJSON implements JsonTransformer<Plane>{
    
    @Override
    public Plane fromJson(JSONObject obj)
    {
        String id = obj.getString("id");
        String brand = obj.getString("brand");
        String model = obj.getString("model");
        int maxCapacity = obj.getInt("maxCapacity");
        String airline = obj.getString("airline");
        
        return new Plane(id, brand, model, maxCapacity, airline);       
    }
}
