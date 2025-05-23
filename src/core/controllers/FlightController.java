package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.flight.Flight;
import core.models.location.Location;
import core.models.plane.Plane;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PlaneStorage;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FlightController {
    
    public static Response FlightRegistration(String id, String planeID, String salidaID, String llegadaID, String scaleID, String departureYear, String departureMonth, String departureDay, String departureHour, String departureMinute, String hourDuration, String minuteDuration, String hourScale, String minuteScale)
    {
        try
        {
            
            // Se verifica si el ID sigue el formato XXXYYY
            FlightStorage flightStorage = FlightStorage.getInstance();
            PlaneStorage planeStorage = PlaneStorage.getInstance();
            LocationStorage locationStorage = LocationStorage.getInstance();
            
            int yearInt, monthInt, dayInt, hourInt, minuteInt, hourDurationInt, minuteDurationInt, hourScaleInt, minuteScaleInt;
            
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
                if(flightStorage.get(id) != null)
                {
                    return new Response("Flight ID already exist.", Status.BAD_REQUEST);
                }
            }
            catch (Exception e)
            {
                return new Response("Database does not exist", Status.INTERNAL_SERVER_ERROR);
            }
            
            
            // Verificar que el avion existe
            
            try
            {
                if(planeStorage.get(planeID) == null)
                {
                    return new Response("Plane ID does not exist.", Status.BAD_REQUEST);
                }
                
            }

            catch(Exception e)
            {
                return new Response("Database does not exist.", Status.BAD_REQUEST);
            }
            
            
            
            // Verificar si las localizaciones de salida y llegada existen
            
            try
            {
                if (locationStorage.get(salidaID) == null)
                {
                    return new Response("Departure location ID does not exist.", Status.BAD_REQUEST);
                }
                
            }
            
            

            catch(Exception e)
            {
                return new Response("Database does not exist.", Status.BAD_REQUEST);
            }
            
            // Verificar si el scale location existe siempre y cuando no sea nulo
            
            try
            {
                if(scaleID != null)
                {
                    if (locationStorage.get(scaleID) == null)
                    {
                        return new Response("Departure location ID does not exist.", Status.BAD_REQUEST);
                    }
                }
            }
            
            

            catch(Exception e)
            {
                return new Response("Database does not exist.", Status.BAD_REQUEST);
            }
            
            try
            {
                if (locationStorage.get(llegadaID) == null)
                {
                    return new Response("Arrival location ID does not exist.", Status.BAD_REQUEST);
                }
            }

            catch(Exception e)
            {
                return new Response("Database does not exist.", Status.BAD_REQUEST);
            }
            
            // Validando fecha de salida
            
            try
            {
                yearInt = Integer.parseInt(departureYear);
                monthInt = Integer.parseInt(departureMonth);
                dayInt = Integer.parseInt(departureDay);
                hourInt = Integer.parseInt(departureHour);
                minuteInt = Integer.parseInt(departureMinute);
                
                LocalDateTime fecha = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minuteInt);
                
                if (fecha.isAfter(LocalDateTime.now()))
                {
                    return new Response("Departure date is not valid. The date cannot be greater than the current date.", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("Departure date is not valid.", Status.BAD_REQUEST);
            }
            
            // Validando la duración del vuelo
            
            try
            {
                hourInt = Integer.parseInt(hourDuration);
                minuteInt = Integer.parseInt(minuteDuration);
                
                LocalTime duracion = LocalTime.of(hourInt, minuteInt);
                
                if(hourInt <= 0 & minuteInt <= 0)
                {
                    return new Response("Invalid flight duration. The duration has to be longer than 00:00", Status.BAD_REQUEST);
                }
            }
            catch(Exception e)
            {
                return new Response("Invalid flight duration.", Status.BAD_REQUEST);
            }
            
            // Validando la duración del scale si es que existe
            
            try
            {
                if(scaleID != null)
                {
                    hourInt = Integer.parseInt(hourScale);
                    minuteInt = Integer.parseInt(minuteScale);

                    LocalTime duracion = LocalTime.of(hourInt, minuteInt);

                    if(hourInt <= 0 & minuteInt <= 0)
                    {
                        return new Response("Invalid scale duration. The duration has to be longer than 00:00", Status.BAD_REQUEST);
                    }
                }
            }
            catch(Exception e)
            {
                return new Response("Invalid scale duration.", Status.BAD_REQUEST);
            }
            
            yearInt = Integer.parseInt(departureYear);
            monthInt = Integer.parseInt(departureMonth);
            dayInt = Integer.parseInt(departureDay);
            hourInt = Integer.parseInt(departureHour);
            minuteInt = Integer.parseInt(departureMinute);
            hourDurationInt = Integer.parseInt(hourDuration);
            minuteDurationInt = Integer.parseInt(hourDuration);
            
            
            Plane plane = planeStorage.get(planeID);
            Location departureLocation = locationStorage.get(salidaID);
            Location arrivalLocation = locationStorage.get(llegadaID);
            
            
            LocalDateTime departureDate = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minuteInt);
            
            if (scaleID == null)
            {
                if (!flightStorage.add(new Flight(id, plane, departureLocation, null, arrivalLocation, departureDate, hourDurationInt, minuteDurationInt, 0, 0))) 
                {
                    return new Response("A flight with that id already exists", Status.BAD_REQUEST);
                }
                else
                {
                    return new Response("Flight successfully registered", Status.CREATED);
                }
            }
            
            else
            {
                Location scaleLocation = locationStorage.get(scaleID);
                hourScaleInt = Integer.parseInt(hourScale);
                minuteScaleInt = Integer.parseInt(minuteScale);
                
                if (!flightStorage.add(new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hourDurationInt, minuteDurationInt, hourScaleInt, minuteScaleInt))) 
                {
                    return new Response("A flight with that id already exists", Status.BAD_REQUEST);
                }
                else
                {
                    return new Response("Flight successfully registered", Status.CREATED);
                }
            }
            
        }
        catch(Exception e)
        {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
