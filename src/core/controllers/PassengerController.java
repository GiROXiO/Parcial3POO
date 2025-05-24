package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.flight.Flight;
import core.models.passenger.Passenger;
import core.models.storage.PassengerStorage;
import java.time.LocalDate;
import java.util.ArrayList;

public class PassengerController {

    public static Response PassengerRegistration(String id, String firstName, String lastName, String yearBirthday, String monthBirthday, String dayBirthday, String phoneCode, String phoneNumber, String country)
    {
        try
        {
            long idL, phoneNumberL;
            int yearInt, monthInt, dayInt, phoneCodeInt;
            LocalDate fecha;
            PassengerStorage storage = PassengerStorage.getInstance();
            
            // Verifica si el ID tiene entre 1 y 15 digitos
            
            try
            {   
                idL = Long.parseLong(id.trim());

                if (idL < 0 || String.valueOf(idL).length() > 15)
                {
                    return new Response("ID must be between 1 to 15 digits.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("ID must be numeric and between 1 to 15 digits.", Status.BAD_REQUEST);
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
            
           
            // Comprueba si el campo de los nombres y pais fueron llenados
        
            if (firstName.isBlank())
            {
                return new Response("The first name field must be filled in", Status.BAD_REQUEST);
            }

            if (lastName.isBlank())
            {
                return new Response("The last name field must be filled in", Status.BAD_REQUEST);
            }

            if (country.isBlank())
            {
                return new Response("The country field must be filled in", Status.BAD_REQUEST);
            }
            
            
            // Comprueba si la fecha es valida
            
            try
            {
                yearInt = Integer.parseInt(yearBirthday);
                monthInt = Integer.parseInt(monthBirthday);
                dayInt = Integer.parseInt(dayBirthday);
                
                fecha = LocalDate.of(yearInt, monthInt, dayInt);
                
                // Verifica si la persona no tiene más de 125 años (la persona más vieja del mundo vivio 122 años)
           
                if (LocalDate.now().getYear() - yearInt > 125 || fecha.isAfter(LocalDate.now()))
                {
                    return new Response("The year of birth must be valid", Status.BAD_REQUEST);
                }
                
            }
            catch(Exception e)
            {
                return new Response("Date of birth must be valid", Status.BAD_REQUEST);
            }
            
            
            // Comprueba si el codigo telefonico tiene ente 1 y 3 digitos
            
            try
            {
                phoneCodeInt = Integer.parseInt(phoneCode.trim());
                if (phoneCodeInt < 0 || phoneCodeInt >= 1000)
                {
                    return new Response("Phone code must be between 1 and 3 digits.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("Phone code must be numeric and filled in", Status.BAD_REQUEST);
            }
            
            
            // Comprueba si el numero telefonico tiene ente 1 y 11 digitos
            
            try
            {
                phoneNumberL = Long.parseLong(phoneNumber);
                if (phoneNumberL < 0 || phoneNumber.trim().length() > 11)
                {
                    return new Response("Phone number must be between 1 and 11 digits.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("Phone number must be numeric and filled in.", Status.BAD_REQUEST);
            }
            
            if (!storage.add(new Passenger(Long.parseLong(id), firstName, lastName, fecha, Integer.parseInt(phoneCode), Long.parseLong(phoneNumber), country))) 
            {
                return new Response("A passenger with that id already exists", Status.BAD_REQUEST);
            }
            else
            {
                return new Response("Passenger successfully registered", Status.CREATED);
            }            
        }
        catch(Exception e)
        {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    public static Response updatePassenger(String id, String firstName, String lastName, String yearBirthday, String monthBirthday, String dayBirthday, String phoneCode, String phoneNumber, String country)
    {
        try
        {
            long idL, phoneNumberL;
            int yearInt, monthInt, dayInt, phoneCodeInt;
            LocalDate fecha;
            PassengerStorage storage = PassengerStorage.getInstance();
            
            
            // Verificar si se selecciono el ID del usuario
            
            if(storage.get(id) == null)
            {
                return new Response("User ID not selected", Status.BAD_REQUEST);
            }
           
            // Comprueba si el campo de los nombres y pais fueron llenados
        
            if (firstName.isBlank())
            {
                return new Response("The first name field must be filled in", Status.BAD_REQUEST);
            }

            if (lastName.isBlank())
            {
                return new Response("The last name field must be filled in", Status.BAD_REQUEST);
            }

            if (country.isBlank())
            {
                return new Response("The country field must be filled in", Status.BAD_REQUEST);
            }
            
            
            // Comprueba si la fecha es valida
            
            try
            {
                yearInt = Integer.parseInt(yearBirthday);
                monthInt = Integer.parseInt(monthBirthday);
                dayInt = Integer.parseInt(dayBirthday);
                
                fecha = LocalDate.of(yearInt, monthInt, dayInt);
                
                // Verifica si la persona no tiene más de 125 años (la persona más vieja del mundo vivio 122 años)
           
                if (LocalDate.now().getYear() - yearInt > 125 || fecha.isAfter(LocalDate.now()))
                {
                    return new Response("The year of birth must be valid", Status.BAD_REQUEST);
                }
                
            }
            catch(Exception e)
            {
                return new Response("Date of birth must be valid", Status.BAD_REQUEST);
            }
            
            
            // Comprueba si el codigo telefonico tiene ente 1 y 3 digitos
            
            try
            {
                phoneCodeInt = Integer.parseInt(phoneCode.trim());
                if (phoneCodeInt < 0 || phoneCodeInt >= 1000)
                {
                    return new Response("Phone code must be between 1 and 3 digits.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("Phone code must be numeric and filled in", Status.BAD_REQUEST);
            }
            
            
            // Comprueba si el numero telefonico tiene ente 1 y 11 digitos
            
            try
            {
                phoneNumberL = Long.parseLong(phoneNumber);
                if (phoneNumberL < 0 || phoneNumber.trim().length() > 11)
                {
                    return new Response("Phone number must be between 1 and 11 digits.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("Phone number must be numeric and filled in.", Status.BAD_REQUEST);
            }
            
            if (!storage.modify(id, firstName, lastName, fecha, Integer.parseInt(phoneCode), Long.parseLong(phoneNumber), country))
            {
                return new Response("Error updating passenger", Status.INTERNAL_SERVER_ERROR);
            }
            else
            {
                return new Response("Passenger successfully updated", Status.CREATED);
            }
            
                   
        }
        catch(Exception e)
        {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    
    
    public static Response getPassengers(){
        try {
            boolean sw = PassengerStorage.getInstance().load();

            if (!sw) {
                return new Response("Error cargando los pasajeros", Status.INTERNAL_SERVER_ERROR);
            }

            ArrayList<Passenger> lista = PassengerStorage.getInstance().getLista();
            
            if(lista.isEmpty()){
                return new Response("No hay datos de pasajeros", Status.NO_CONTENT);
            }
            
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
            return new Response("Error interno obteniendo el ID del pasajero", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response getPassengerRow(Object obj) {
        try {
            if (!(obj instanceof Passenger)) {
                return new Response("El item seleccionado no es un pasajero", Status.BAD_REQUEST);
            }
            Passenger passenger = ((Passenger) obj).clone();
            Object[] passengerRow = new Object[]{
                passenger.getId(),
                passenger.getFullname(),
                passenger.getBirthDate(),
                passenger.calculateAge(),
                passenger.getPhone(),
                passenger.getCountry(),
                passenger.getNumFlights()
            };
            return new Response("Pasajero obtenido exitosamente", Status.OK, passengerRow);
        } catch (CloneNotSupportedException e) {
            return new Response("Error interno obteniendo el pasajero", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response getPassengerFlights(long id){
        try {
            boolean sw = PassengerStorage.getInstance().load();

            if (!sw) {
                return new Response("Error cargando los pasajeros", Status.INTERNAL_SERVER_ERROR);
            }

            ArrayList<Passenger> lista = PassengerStorage.getInstance().getLista();
            
            if(lista.isEmpty()){
                return new Response("La lista de pasajeros esta vacia", Status.NO_CONTENT);
            }
            
            ArrayList<Flight> passengerFlights = new ArrayList<>();

            for (Passenger passenger : lista) {
                if(passenger.getId()==id){
                    passengerFlights = passenger.clone().getFlights();
                }
            }
            
            if(passengerFlights.isEmpty()){
                return new Response("La lista de vuelos del pasajero seleccionado esta vacia", Status.NO_CONTENT);
            }
            
            return new Response("Vuelos del pasajero cargados correctamente", Status.OK, passengerFlights);
        } catch (CloneNotSupportedException e) {
            return new Response("Error interno obteniendo los pasajeros", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
