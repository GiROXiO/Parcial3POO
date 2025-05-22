package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.plane.Plane;
import core.models.storage.PlaneStorage;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class AirplaneController {
    
    public static Response airplaneRegistration(String ruta, String id, String brand, String model, String maxCapacity, String airline)
    {
        try
        {
            // Se verifica si el ID sigue el formato XXYYYYY
           
            try
            {
                
                if (id.length() == 7)
                {
                    for (int i = 0 ; i <= 1 ; i++)
                    {
                        
                        if (Character.isDigit(id.charAt(i)))
                        {
                            return new Response("ID must follow the XXYYYYY format. X must correspond to a capital letter, not a numeric or blank value.", Status.BAD_REQUEST);
                        }
                        
                        if (Character.isLowerCase(id.charAt(i)))
                        {
                            return new Response("ID must follow the XXYYYYY format. X must correspond to a capital letter, not a lower case letter.", Status.BAD_REQUEST);
                        }
                    }
                    
                    for (int i = 2 ; i <= 6 ; i++)
                    {
                        if (!Character.isDigit(id.charAt(i)))
                        {
                            return new Response("ID must follow the XXYYYYY format. Y must correspond to a numeric value, not a letter or blank value.", Status.BAD_REQUEST);
                        }
                    }
                }
                else
                {
                    return new Response("ID must follow the XXYYYYY format", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("ID must follow the XXYYYYY format.", Status.BAD_REQUEST);
            }
            
          
            
            // Se verifica si Max Capacity es un valor numérico
            
            try
            {
                int verfInt = Integer.parseInt(maxCapacity);
            }
            catch(Exception e)
            {
                return new Response("Max capacity must be a integer.", Status.BAD_REQUEST);
            }
            
            // Se verifica si los demás campos no estan vacios
            
            try
            {
                if (brand.isBlank())
                {
                    return new Response("Brand field must be filled in.", Status.BAD_REQUEST);
                }
                
                if (model.isBlank())
                {
                    return new Response("Model field must be filled in.", Status.BAD_REQUEST);
                }
                
                if (maxCapacity.isBlank())
                {
                    return new Response("Max capacity field must be filled in.", Status.BAD_REQUEST);
                }
                
                if (airline.isBlank())
                {
                    return new Response("Airline field must be filled in.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("All fields must be filled in.", Status.BAD_REQUEST);
            }
            
            PlaneStorage storage = PlaneStorage.getInstance();
            
            if (!storage.add(new Plane(id, brand, model, Integer.parseInt(maxCapacity), airline))) {
                return new Response("A plane with that id already exists", Status.BAD_REQUEST);
            }
            else
            {
                return new Response("Plane successfully registered", Status.CREATED);
            }
        }
        catch(Exception e)
        {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }

    }
    
}
