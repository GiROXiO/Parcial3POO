package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.passenger.Passenger;
import core.models.storage.PassengerStorage;
import java.time.LocalDate;
import java.util.ArrayList;

public class PassengerController {

    public static Response PassengerRegistration(String id, String firstName, String lastName, String yearBirthday, String monthBirthday, String dayBirthday, String phoneCode, String phoneNumber, String country)
    {
        try
        {
            int idInt, yearInt, monthInt, dayInt, phoneCodeInt, phoneNumberInt;
            LocalDate fecha;
            PassengerStorage storage = PassengerStorage.getInstance();
            // Verifica si el ID tiene entre 1 y 15 digitos
             
            try
            {
                idInt = Integer.parseInt(id);
                
                if (idInt < 0 | String.valueOf(idInt).length() > 15)
                {
                    return new Response("ID must be between 1 to 15 digits.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("ID must be numeric", Status.BAD_REQUEST);
            }
            
            // Verifica si el ID ya existe
            
           try
            {
                if(storage.get(id) != null)
                {
                    return new Response("ID already exist.", Status.BAD_REQUEST);
                }
            }
            catch (Exception e)
            {
                return new Response("Database does not exist", Status.INTERNAL_SERVER_ERROR);
            }
            
            
            // Verifica si el año de nacimiento es numérico
            
            try
            {    
                
                yearInt = Integer.parseInt(yearBirthday);
                monthInt = Integer.parseInt(monthBirthday);
                dayInt = Integer.parseInt(dayBirthday);
                
                LocalDate fechaBirthday = LocalDate.of(yearInt, monthInt, dayInt);
                // Verifica si la persona no tiene más de 125 años (la persona más vieja del mundo vivio 122 años), no se si quitar esto la vd
                
                if (LocalDate.now().getYear() - yearInt > 125 | fechaBirthday.isAfter(LocalDate.now()))
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
                
                fecha = LocalDate.of(yearInt, monthInt, dayInt);
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
                
                if (String.valueOf(firstName).isBlank())
                {
                    return new Response("The first name field must be filled in", Status.BAD_REQUEST);
                }
                
                if (String.valueOf(lastName).isBlank())
                {
                    return new Response("The last name field must be filled in", Status.BAD_REQUEST);
                }
                
                if (String.valueOf(country).isBlank())
                {
                    return new Response("The country field must be filled in", Status.BAD_REQUEST);
                }
            }
         
            catch(Exception e)
            {
                return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
            }
            
            yearInt = Integer.parseInt(yearBirthday);
            monthInt = Integer.parseInt(monthBirthday);
            dayInt = Integer.parseInt(dayBirthday);
            fecha = LocalDate.of(yearInt, monthInt, dayInt);
            
            
            if (!storage.add(new Passenger(Long.parseLong(id), firstName, lastName, fecha, Integer.parseInt(phoneCode), Long.parseLong(phoneNumber), country))) {
                return new Response("A passenger with that id already exists", Status.BAD_REQUEST);
            }
            else
            {
                return new Response("Passenger successfully registered", Status.CREATED);
            }
        }
        catch(NumberFormatException e)
        {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    public static Response getPassengers(){
        try {
            boolean sw = PassengerStorage.getInstance().load();

            if (!sw) {
                return new Response("Error cargando los vuelos", Status.INTERNAL_SERVER_ERROR);
            }

            ArrayList<Passenger> lista = PassengerStorage.getInstance().getLista();
            ArrayList<Passenger> copia = new ArrayList<>();

            for (Passenger passenger : lista) {
                copia.add(passenger.clone());
            }

            return new Response("Pasajeros cargados correctamente", Status.OK, copia);
        } catch (CloneNotSupportedException e) {
            return new Response("Error interno obteniendo los pasajeros", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getPassengerId(Object obj){
        try {
            if (!(obj instanceof Passenger)) {
                return new Response("El id no corresponde al de un pasajero", Status.BAD_REQUEST);
            }
            String id = String.valueOf(((Passenger)obj).clone().getId());
            return new Response("Id del vuelo obtenido exitosamente", Status.OK, id);
        } catch (CloneNotSupportedException e) {
            return new Response("Error interno obteniendo el ID del vuelo", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
