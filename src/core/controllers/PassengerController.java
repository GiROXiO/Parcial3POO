package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.time.LocalDate;


public class PassengerController {

    public static Response PassengerRegistration(String id, String firstName, String lastName, String yearBirthday, String monthBirthday, String dayBirthday, String phoneCode, String phoneNumber, String country)
    {
        try
        {
            int idInt, yearInt, monthInt, dayInt, phoneCodeInt, phoneNumberInt;
            
            // Verifica si el ID tiene entre 1 y 15 digitos
             
            try
            {
                idInt = Integer.parseInt(id);
                
                if (idInt < 0 | String.valueOf(idInt).length() > 15)
                {
                    return new Response("ID must be between 1 to 15 digits.", Status.BAD_REQUEST);
                }
                
                // Hace falta añadir un condicional para ver si el ID es único dentro del storage
            }
            catch(Exception e)
            {
                return new Response("ID must be numeric", Status.BAD_REQUEST);
            }
            
            // Verifica si el año de nacimiento es numérico
            
            try
            {    
                
                yearInt = Integer.parseInt(yearBirthday);
                
                // Verifica si la persona no tiene más de 125 años (la persona más vieja del mundo vivio 122 años), no se si quitar esto la vd
                
                if (LocalDate.now().getYear() - yearInt > 125)
                {
                    return new Response("The year of birth must be valid", Status.BAD_REQUEST);
                }
                
            }
            catch(Exception e)
            {
                return new Response("The year of birth must be numeric", Status.BAD_REQUEST);
            }
            
            // Comprueba si la fecha es valida
            
            try
            {
                yearInt = Integer.parseInt(yearBirthday);
                monthInt = Integer.parseInt(monthBirthday);
                dayInt = Integer.parseInt(dayBirthday);
                
                LocalDate fecha = LocalDate.of(yearInt, monthInt, dayInt);
            }
            catch(Exception e)
            {
                return new Response("Date of Birth must be valid", Status.BAD_REQUEST);
            }
            
            // Comprueba si el codigo telefonico tiene ente 1 y 3 digitos
            
            try
            {
                phoneCodeInt = Integer.parseInt(phoneCode);
                if (phoneCodeInt < 0 | phoneCodeInt >= 1000)
                {
                    return new Response("Phone code must be between 1 and 3 digits.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("Phone code must be numeric", Status.BAD_REQUEST);
            }
            
            // Comprueba si el numero telefonico tiene ente 1 y 11 digitos
            
            try
            {
                phoneNumberInt = Integer.parseInt(phoneNumber);
                if (phoneNumberInt < 0 | String.valueOf(phoneNumberInt).length() > 11)
                {
                    return new Response("Phone number must be between 1 and 11 digits.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("Phone code must be numeric", Status.BAD_REQUEST);
            }
            
            
            // Comprueba si el campo de los paises fue llenado
            
            try
            {
                if (String.valueOf(country).length() <= 0)
                {
                    return new Response("The country field must be filled in", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
            }
            
            
        }
        catch(Exception e)
        {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
        return null;
    }
    
}
