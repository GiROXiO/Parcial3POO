package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.flight.Flight;
import core.models.passenger.Passenger;
import core.models.passenger.PhoneNumber;
import core.models.storage.FlightStorage;
import core.models.storage.PassengerStorage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PassengerController {

    public static Response PassengerRegistration(String id, String firstName, String lastName, String yearBirthday, String monthBirthday, String dayBirthday, String phoneCode, String phoneNumber, String country) {
        try {
            PhoneNumber entPhoneNumber;
            long idL, phoneNumberL;
            int yearInt, monthInt, dayInt, phoneCodeInt;
            LocalDate fecha;
            PassengerStorage storage = PassengerStorage.getInstance();

            // Verifica si el ID tiene entre 1 y 15 digitos
            try {
                idL = Long.parseLong(id.trim());

                if (idL < 0 || String.valueOf(idL).length() > 15) {
                    return new Response("ID must be between 1 to 15 digits.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("ID must be numeric and between 1 to 15 digits.", Status.BAD_REQUEST);
            }

            // Verifica si el ID ya existe
            try {
                if (storage.get(id) != null) {
                    return new Response("ID already exist.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Database does not exist", Status.INTERNAL_SERVER_ERROR);
            }

            // Comprueba si el campo de nombre y apellido fueron llenados
            if (firstName.isBlank()) {
                return new Response("The first name field must be filled in", Status.BAD_REQUEST);
            }

            if (lastName.isBlank()) {
                return new Response("The last name field must be filled in", Status.BAD_REQUEST);
            }
            
            //Comprobamos que el nombre del usuario no contenga numeros
            for (char c : firstName.toCharArray()) {
                try {
                    Integer.valueOf(String.valueOf(c));
                    return new Response("Firstname must be valid", Status.BAD_REQUEST);
                } catch (NumberFormatException e) {
                }
            }
            
            //Comprobamos que el apellido del usuario no contenga numeros
            for (char c : lastName.toCharArray()) {
                try {
                    Integer.valueOf(String.valueOf(c));
                    return new Response("Lastname must be valid", Status.BAD_REQUEST);
                } catch (NumberFormatException e) {
                }
            }
            
            // Comprueba si la fecha es valida
            try {
                yearInt = Integer.parseInt(yearBirthday);
                monthInt = Integer.parseInt(monthBirthday);
                dayInt = Integer.parseInt(dayBirthday);

                fecha = LocalDate.of(yearInt, monthInt, dayInt);

                // Verifica si la persona no tiene más de 125 años (la persona más vieja del mundo vivio 122 años)
                if (LocalDate.now().getYear() - yearInt > 125 || fecha.isAfter(LocalDate.now())) {
                    return new Response("The year of birth must be valid", Status.BAD_REQUEST);
                }

            } catch (Exception e) {
                return new Response("Date of birth must be valid", Status.BAD_REQUEST);
            }

            // Comprueba si el codigo telefonico tiene ente 1 y 3 digitos
            try {
                phoneCodeInt = Integer.parseInt(phoneCode.trim());
                if (phoneCodeInt < 0 || phoneCodeInt >= 1000) {
                    return new Response("Phone code must be between 1 and 3 digits.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Phone code must be numeric and filled in", Status.BAD_REQUEST);
            }

            // Comprueba si el numero telefonico tiene ente 1 y 11 digitos
            try {
                phoneNumberL = Long.parseLong(phoneNumber);
                if (phoneNumberL < 0 || phoneNumber.trim().length() > 11) {
                    return new Response("Phone number must be between 1 and 11 digits.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Phone number must be numeric and filled in.", Status.BAD_REQUEST);
            }

            // Comprueba que el campo de pais fue llenado
            if (country.isBlank()) {
                return new Response("The country field must be filled in", Status.BAD_REQUEST);
            }
            
            //Comprobamos que el nombre del pais no contenga numeros
            for (char c : country.toCharArray()) {
                try {
                    Integer.valueOf(String.valueOf(c));
                    return new Response("Country name must be valid", Status.BAD_REQUEST);
                } catch (NumberFormatException e) {
                }
            }
            
            entPhoneNumber = new PhoneNumber(Integer.parseInt(phoneCode), Long.parseLong(phoneNumber));
            
            if (!storage.add(new Passenger(Long.parseLong(id), firstName, lastName, fecha, entPhoneNumber, country))) {
                return new Response("A passenger with that id already exists", Status.BAD_REQUEST);
            } else {
                return new Response("Passenger successfully registered", Status.CREATED);
            }
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }

    public static Response updatePassenger(String id, String firstName, String lastName, String yearBirthday, String monthBirthday, String dayBirthday, String phoneCode, String phoneNumber, String country) {
        try {
            long idL, phoneNumberL;
            int yearInt, monthInt, dayInt, phoneCodeInt;
            LocalDate fecha;
            PassengerStorage storage = PassengerStorage.getInstance();

            // Verificar si se selecciono el ID del usuario
            if (storage.get(id) == null) {
                return new Response("User ID not selected", Status.BAD_REQUEST);
            }

            // Comprueba si el campo de nombre y apellido fueron llenados
            if (firstName.isBlank()) {
                return new Response("The first name field must be filled in", Status.BAD_REQUEST);
            }

            if (lastName.isBlank()) {
                return new Response("The last name field must be filled in", Status.BAD_REQUEST);
            }

            //Comprobamos que el nombre del usuario no contenga numeros
            for (char c : firstName.toCharArray()) {
                try {
                    Integer.valueOf(String.valueOf(c));
                    return new Response("Firstname must be valid", Status.BAD_REQUEST);
                } catch (NumberFormatException e) {
                }
            }
            
            //Comprobamos que el apellido del usuario no contenga numeros
            for (char c : lastName.toCharArray()) {
                try {
                    Integer.valueOf(String.valueOf(c));
                    return new Response("Lastname must be valid", Status.BAD_REQUEST);
                } catch (NumberFormatException e) {
                }
            }

            // Comprueba si la fecha es valida
            try {
                yearInt = Integer.parseInt(yearBirthday);
                monthInt = Integer.parseInt(monthBirthday);
                dayInt = Integer.parseInt(dayBirthday);

                fecha = LocalDate.of(yearInt, monthInt, dayInt);

                // Verifica si la persona no tiene más de 125 años (la persona más vieja del mundo vivio 122 años)
                if (LocalDate.now().getYear() - yearInt > 125 || fecha.isAfter(LocalDate.now())) {
                    return new Response("The year of birth must be valid", Status.BAD_REQUEST);
                }

            } catch (Exception e) {
                return new Response("Date of birth must be valid", Status.BAD_REQUEST);
            }

            // Comprueba si el codigo telefonico tiene ente 1 y 3 digitos
            try {
                phoneCodeInt = Integer.parseInt(phoneCode.trim());
                if (phoneCodeInt < 0 || phoneCodeInt >= 1000) {
                    return new Response("Phone code must be between 1 and 3 digits.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Phone code must be numeric and filled in", Status.BAD_REQUEST);
            }

            // Comprueba si el numero telefonico tiene ente 1 y 11 digitos
            try {
                phoneNumberL = Long.parseLong(phoneNumber);
                if (phoneNumberL < 0 || phoneNumber.trim().length() > 11) {
                    return new Response("Phone number must be between 1 and 11 digits.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Phone number must be numeric and filled in.", Status.BAD_REQUEST);
            }
            
            // Comprueba si el campo del pais fue llenado
            if (country.isBlank()) {
                return new Response("The country field must be filled in", Status.BAD_REQUEST);
            }
            
            // Comprueba si el nombre del pais contiene numeros
            for(char c : country.toCharArray()){
                try{
                    Integer.valueOf(String.valueOf(c));
                    return new Response("Country name must be valid", Status.BAD_REQUEST);
                }catch(NumberFormatException e){
                    
                }
            }
            
            try {
                Passenger passenger = storage.get(id);
                passenger.setFirstname(firstName);
                passenger.setLastname(lastName);
                passenger.setBirthDate(fecha);
                PhoneNumber newPhoneNumber = new PhoneNumber(Integer.parseInt(phoneCode), Long.parseLong(phoneNumber));
                passenger.setPhoneNumber(newPhoneNumber);
                passenger.setCountry(country);

                return new Response("Passenger succesfully updated", Status.OK);
            } catch (Exception e) {
                return new Response("Error updating passenger", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }

    public static Response addFlight(String id, String flightId) {
        try {

            PassengerStorage passengerStorage = PassengerStorage.getInstance();
            FlightStorage flightStorage = FlightStorage.getInstance();

            Passenger passenger = passengerStorage.get(id);
            Flight flight = flightStorage.get(flightId);

            // Comprobar tanto si el id del usuario como el del vuelo fueron seleccionados
            if (passenger == null) {
                return new Response("User ID not selected", Status.BAD_REQUEST);
            }

            if (flight == null) {
                return new Response("Flight ID not selected", Status.BAD_REQUEST);
            }
            
            
            for(Flight passsengerFlight : passenger.getFlights())
            {
                if (flight==passsengerFlight)
                {
                    return new Response("Passenger has already registered for that flight.", Status.BAD_REQUEST);
                }
            }
            
            passenger.addFlight(flight);
            flight.addPassenger(passenger);
            return new Response("Flight succesfully added", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response loadPassengers(){
        try{
            PassengerStorage.getInstance().load();
            return new Response("Passengers loaded succesfully in the system", Status.CREATED);
        }catch(Exception e){
            return new Response ("Internal error loading passengers in the system", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getPassengers() {
        try {
            ArrayList<Passenger> lista = PassengerStorage.getInstance().getLista();

            if (lista.isEmpty()) {
                return new Response("There are no passengers in the storage", Status.NO_CONTENT);
            }

            ArrayList<Passenger> copia = new ArrayList<>();

            for (Passenger passenger : lista) {
                copia.add(passenger.clonePassenger());
            }

            Collections.sort(copia, Comparator.comparing(passenger -> {
                return passenger.getId();
            }));
            
            return new Response("Passengers information got succesfully", Status.OK, copia);
        } catch (Exception e) {
            return new Response("Internal error loading passengers information", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getPassengerId(Object obj) {
        try {
            if (!(obj instanceof Passenger)) {
                return new Response("The selection is not a passenger", Status.BAD_REQUEST);
            }
            String id = String.valueOf(((Passenger) obj).clonePassenger().getId());
            return new Response("Passenger ID got succesfully", Status.OK, id);
        } catch (Exception e) {
            return new Response("Internal error getting passenger ID", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getPassengerInfo(Object obj) {
        try {
            if (!(obj instanceof Passenger)) {
                return new Response("Item selected is not a passenger", Status.BAD_REQUEST);
            }
            Passenger passenger = ((Passenger) obj).clonePassenger();
            Object[] passengerRow = new Object[]{
                passenger.getId(),
                passenger.getFullName(),
                passenger.getBirthDate(),
                passenger.calculateAge(),
                passenger.getPhoneNumber(),
                passenger.getCountry(),
                passenger.getNumFlights()
            };
            return new Response("Passenger information got succesfully", Status.OK, passengerRow);
        } catch (Exception e) {
            return new Response("Internal error getting passenger information", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getPassengerFlights(String id) {
        try {
            long passengerID;
            try{
                passengerID = Long.parseLong(id);
            }catch(NumberFormatException e){
                return new Response("User ID not selected", Status.BAD_REQUEST);
            }
            
            ArrayList<Passenger> lista = PassengerStorage.getInstance().getLista();

            if (lista.isEmpty()) {
                return new Response("There is no passenger information", Status.NO_CONTENT);
            }

            ArrayList<Flight> passengerFlights = new ArrayList<>();

            for (Passenger passenger : lista) {
                if (passenger.getId() == passengerID) {
                    passengerFlights = passenger.getFlights();
                }
            }

            if (passengerFlights.isEmpty()) {
                return new Response("Passenger has not any flights", Status.NO_CONTENT);
            }

            Collections.sort(passengerFlights, Comparator.comparing(flight -> {
                return flight.getDepartureDate();
            }));
            return new Response("Passenger flights loaded succesfully", Status.OK, passengerFlights);
        } catch (Exception e) {
            return new Response("Internal error loading passengers information", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
