package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.location.Location;
import core.models.storage.LocationStorage;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class LocationController {
    
    public static Response LocationRegistration(String ruta, String id, String name, String city, String country, String latitud, String longitud)
    {
        try
        {
            
            float latitudFl, longitudFl;
            
            // Verificar si el ID tiene 3 letras mayusculas
            
            try{
                if (id.length() == 3)
                {
                    for (int i = 0 ; i < id.length() ; i++)
                    {
                        if (Character.isLowerCase(id.charAt(i)))
                        {
                            return new Response("ID must contain 3 uppercase letters.", Status.BAD_REQUEST);
                        }
                    }
                }
                else
                {
                    return new Response("ID must contain 3 uppercase letters.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
            }
         
            
            // Verificar latitud
            
            try
            {
                latitudFl = Float.parseFloat(latitud);
                
                int decimales = Float.toString(latitudFl).length() - Float.toString(latitudFl).indexOf('.') - 1;
                
                if (decimales > 4)
                {
                    return new Response("Latitude must have a maximum of 4 decimal places.", Status.BAD_REQUEST);
                }
                
                if (latitudFl < -90.0 | latitudFl > 90.0)
                {
                    return new Response("Valid latitudes are in the range [-90, 90].", Status.BAD_REQUEST);
                }
                
            }
            catch(Exception e)
            {
                return new Response("Latitude must be a numeric value", Status.BAD_REQUEST);
            }
            
            // Verificar longitud
            
            try
            {
                longitudFl = Float.parseFloat(longitud);
                
                int decimales = Float.toString(longitudFl).length() - Float.toString(longitudFl).indexOf('.') - 1;
                
                if (decimales > 4)
                {
                    return new Response("Longitude must have a maximum of 4 decimal places.", Status.BAD_REQUEST);
                }
                
                if (longitudFl < -180.0 | longitudFl > 180.0)
                {
                    return new Response("Valid Longitudes are in the range [-180, 180].", Status.BAD_REQUEST);
                }
                
            }
            catch(Exception e)
            {
                return new Response("Longitude must be a numeric value", Status.BAD_REQUEST);
            }
            
            // Verificar si el resto de campos no estan vacios
            
            try
            {
                if (name.isBlank())
                {
                    return new Response("Airport name field must be filled in.", Status.BAD_REQUEST);
                }
                
                if (city.isBlank())
                {
                    return new Response("Airport city field must be filled in.", Status.BAD_REQUEST);
                }
                
                if (country.isBlank())
                {
                    return new Response("Airport country field must be filled in.", Status.BAD_REQUEST);
                }
            }
            
            catch(Exception e)
            {
                return new Response("All fields must be filled in.", Status.BAD_REQUEST);
            }
            
            try{
            
                LocationStorage storage = LocationStorage.getInstance();

                if (!storage.add(new Location(id, name, city, country, Double.parseDouble(latitud), Double.parseDouble(longitud)))) {
                    return new Response("A location with that id already exists", Status.BAD_REQUEST);
                }
                else
                {
                    return new Response("Location successfully registered", Status.CREATED);
                }
                
            }
            
            catch(Exception e)
            {
                return new Response("Database does not exist.", Status.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e)
        {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }

    }
}
