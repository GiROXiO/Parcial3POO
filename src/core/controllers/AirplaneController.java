package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

public class AirplaneController {
    
    public static Response airplaneRegistration(String id, String brand, String model, String maxCapacity, String airline)
    {
        try
        {
            char[] verfArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '};
            int verfInt;
            
            
            // Se verifica si el ID sigue el formato XXXYYY
            
            try
            {
                
                if (String.valueOf(id).length() == 6)
                {
                    for (int i = 0 ; i <= 2 ; i++)
                    {
                        for (int j = 0 ; j < verfArr.length ; j++)
                        {
                            if (id.charAt(i) == verfArr[j])
                            {
                                return new Response("ID must follow the XXXYYY format. X must correspond to a capital letter, not a numeric or blank value.", Status.BAD_REQUEST);
                            }
                        }
                        if (Character.isLowerCase(id.charAt(i)))
                        {
                            return new Response("ID must follow the XXXYYY format. X must correspond to a capital letter, not a lower case letter.", Status.BAD_REQUEST);
                        }
                    }
                    try
                    {
                        for (int i = 3 ; i <= 5 ; i++)
                        {
                            verfInt = Integer.parseInt(String.valueOf(id.charAt(i)));
                        }
                    }
                    catch(Exception e)
                    {
                        return new Response("ID must follow the XXXYYY format. Y must correspond to a numeric value, not a letter.", Status.BAD_REQUEST);
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
                return new Response("All fields must be filled in.", Status.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e)
        {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
    
}
