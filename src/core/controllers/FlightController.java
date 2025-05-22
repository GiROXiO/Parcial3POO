package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.plane.Plane;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class FlightController {
    
    public static Response FlightRegistration(String ruta, String rutaAviones, String id, String planeID)
    {
        try
        {
            
            // Se verifica si el ID sigue el formato XXXYYY
           
            try
            {
                
                if (id.length() == 6)
                {
                    for (int i = 0 ; i <= 2 ; i++)
                    {
                        
                        if (Character.isDigit(id.charAt(i)))
                        {
                            return new Response("ID must follow the XXXYYY format. X must correspond to a capital letter, not a numeric or blank value.", Status.BAD_REQUEST);
                        }
                        
                        if (Character.isLowerCase(id.charAt(i)))
                        {
                            return new Response("ID must follow the XXXYYY format. X must correspond to a capital letter, not a lower case letter.", Status.BAD_REQUEST);
                        }
                    }
                    
                    for (int i = 3 ; i <= 5 ; i++)
                    {
                        if (!Character.isDigit(id.charAt(i)))
                        {
                            return new Response("ID must follow the XXXYYY format. Y must correspond to a numeric value, not a letter or blank value.", Status.BAD_REQUEST);
                        }
                    }
                }
                else
                {
                    return new Response("ID must follow the XXXYYY format", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("ID must follow the XXXYYY format.", Status.BAD_REQUEST);
            }
            
            // Se verifica si el ID del vuelo es unico
            
            try
            {
                
            }
            catch (Exception e)
            {
                return new Response("Database does not exist", Status.INTERNAL_SERVER_ERROR);
            }
            
            // Verificar que el avion existe
            
            try
            {
                
            }

            catch(Exception e)
            {
                return new Response("ID must follow the XXXYYY format.", Status.BAD_REQUEST);
            }
            
        }
        catch(Exception e)
        {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
    
    
}
