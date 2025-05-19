package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class AirplaneController {
    
    public static Response airplaneRegistration(String ruta, String id, String brand, String model, String maxCapacity, String airline)
    {
        try
        {
            char[] verfArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '};
            int verfInt;
            
            
            // Se verifica si el ID sigue el formato XXXYYY
            
            try
            {
                
                if (id.length() == 7)
                {
                    for (int i = 0 ; i <= 1 ; i++)
                    {
                        for (int j = 0 ; j < verfArr.length ; j++)
                        {
                            if (id.charAt(i) == verfArr[j])
                            {
                                return new Response("ID must follow the XXYYYYY format. X must correspond to a capital letter, not a numeric or blank value.", Status.BAD_REQUEST);
                            }
                        }
                        if (Character.isLowerCase(id.charAt(i)))
                        {
                            return new Response("ID must follow the XXYYYYY format. X must correspond to a capital letter, not a lower case letter.", Status.BAD_REQUEST);
                        }
                    }
                    try
                    {
                        for (int i = 2 ; i <= 6 ; i++)
                        {
                            verfInt = Integer.parseInt(String.valueOf(id.charAt(i)));
                        }
                    }
                    catch(Exception e)
                    {
                        return new Response("ID must follow the XXYYYYY format. Y must correspond to a numeric value, not a letter.", Status.BAD_REQUEST);
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
            
            // Se verifica si el ID del avion es unico
            
            try
            {
                String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
                JSONArray airplanesJson = new JSONArray(contenido);
                
                for (int i = 0 ; i < airplanesJson.length() ; i++)
                {
                    JSONObject airplane = airplanesJson.getJSONObject(i);
                    
                    if(id.equals(airplane.getString("id")))
                    {
                        return new Response("The ID already exists", Status.BAD_REQUEST);
                    }
                }
            }
            catch (Exception e)
            {
                return new Response("Database does not exist", Status.INTERNAL_SERVER_ERROR);
            }
            
            // Se verifica si Max Capacity es un valor numérico
            
            try
            {
                verfInt = Integer.parseInt(maxCapacity);
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
        }
        catch(Exception e)
        {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
    
}
