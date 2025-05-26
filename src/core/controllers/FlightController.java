package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.flight.Flight;
import core.models.location.Location;
import core.models.plane.Plane;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.Plane.PlaneStorage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FlightController {

    public static Response FlightRegistration(String id, String planeID, String salidaID, String llegadaID, String scaleID, String departureYear, String departureMonth, String departureDay, String departureHour, String departureMinute, String hourDuration, String minuteDuration, String hourScale, String minuteScale) {
        try {

            // Se verifica si el ID sigue el formato XXXYYY
            FlightStorage flightStorage = FlightStorage.getInstance();
            PlaneStorage planeStorage = PlaneStorage.getInstance();
            LocationStorage locationStorage = LocationStorage.getInstance();

            int yearInt, monthInt, dayInt, hourInt, minuteInt, hourDurationInt, minuteDurationInt, hourScaleInt, minuteScaleInt;

            try {

                if (id.length() == 6) {
                    for (int i = 0; i <= 2; i++) {

                        if (Character.isDigit(id.charAt(i))) {
                            return new Response("ID must follow the XXXYYY format. X must correspond to a capital letter, not a numeric or blank value.", Status.BAD_REQUEST);
                        }

                        if (Character.isLowerCase(id.charAt(i))) {
                            return new Response("ID must follow the XXXYYY format. X must correspond to a capital letter, not a lower case letter.", Status.BAD_REQUEST);
                        }
                    }

                    for (int i = 3; i <= 5; i++) {
                        if (!Character.isDigit(id.charAt(i))) {
                            return new Response("ID must follow the XXXYYY format. Y must correspond to a numeric value, not a letter or blank value.", Status.BAD_REQUEST);
                        }
                    }
                } else {
                    return new Response("ID must follow the XXXYYY format", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("ID must follow the XXXYYY format.", Status.BAD_REQUEST);
            }

            // Se verifica si el ID del vuelo es unico
            try {
                if (flightStorage.get(id) != null) {
                    return new Response("Flight ID already exist.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Database does not exist", Status.INTERNAL_SERVER_ERROR);
            }

            // Verificar que el avion existe
            try {
                if (planeStorage.get(planeID) == null) {
                    return new Response("Plane ID does not exist.", Status.BAD_REQUEST);
                }

            } catch (Exception e) {
                return new Response("Database does not exist.", Status.BAD_REQUEST);
            }

            // Verificar si las localizaciones de salida y llegada existen
            try {
                if (locationStorage.get(salidaID) == null) {
                    return new Response("Departure location ID does not exist.", Status.BAD_REQUEST);
                }

            } catch (Exception e) {
                return new Response("Database does not exist.", Status.BAD_REQUEST);
            }

            // Verificar si el scale location existe siempre y cuando no sea nulo
            try {
                if (!(scaleID.equalsIgnoreCase("Location"))) {
                    if (locationStorage.get(scaleID) == null) {
                        return new Response("Scale location ID does not exist.", Status.BAD_REQUEST);
                    }
                }
            } catch (Exception e) {
                return new Response("Database does not exist.", Status.BAD_REQUEST);
            }

            try {
                if (locationStorage.get(llegadaID) == null) {
                    return new Response("Arrival location ID does not exist.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Database does not exist.", Status.BAD_REQUEST);
            }

            //Validando que id de salida, llegada y escala (si existe) no sean iguales
            try {
                if (!(scaleID.equalsIgnoreCase("Location"))) {
                    if (salidaID.equalsIgnoreCase(llegadaID) || salidaID.equalsIgnoreCase(scaleID) || llegadaID.equalsIgnoreCase(scaleID)) {
                        return new Response("Locations must be different", Status.BAD_REQUEST);
                    }
                } else {
                    if (salidaID.equalsIgnoreCase(llegadaID)) {
                        return new Response("Locations must be different", Status.BAD_REQUEST);
                    }
                }
            } catch (Exception e) {
                return new Response("Locations must be different", Status.BAD_REQUEST);
            }

            //Validando que si no hay escala, no haya tiempo de escala
            if (scaleID.equalsIgnoreCase("Location")) {
                try {
                    int h = Integer.parseInt(hourScale);
                    
                    if(h>0){
                        return new Response("There must no be scale hours on a non-stop flight", Status.BAD_REQUEST);
                    }
                } catch (NumberFormatException e) {
                    hourScale = "0";
                }
                
                try {
                    int m = Integer.parseInt(minuteScale);
                    
                    if(m>0){
                        return new Response("There must no be scale minutes on a non-stop flight", Status.BAD_REQUEST);
                    }
                } catch (NumberFormatException e) {
                    minuteScale = "0";
                }
            }

            // Validando fecha de salida
            try {
                yearInt = Integer.parseInt(departureYear);
                monthInt = Integer.parseInt(departureMonth);
                dayInt = Integer.parseInt(departureDay);
                hourInt = Integer.parseInt(departureHour);
                minuteInt = Integer.parseInt(departureMinute);

                LocalDateTime fecha = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minuteInt);

                if (fecha.isBefore(LocalDateTime.now())) {
                    return new Response("Departure date is not valid. The departure date cannot be earlier than the current date.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Departure date is not valid.", Status.BAD_REQUEST);
            }

            // Validando la duración del vuelo
            try {
                hourDurationInt = Integer.parseInt(hourDuration);
                minuteDurationInt = Integer.parseInt(minuteDuration);

                if (hourDurationInt <= 0 && minuteDurationInt <= 0) {
                    return new Response("Invalid flight duration. The duration has to be longer than 00:00", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Invalid flight duration.", Status.BAD_REQUEST);
            }

            // Validando la duración del scale si es que existe
            try {
                if (!(scaleID.equalsIgnoreCase("Location"))) {
                    hourScaleInt = Integer.parseInt(hourScale);
                    minuteScaleInt = Integer.parseInt(minuteScale);

                    if (hourScaleInt <= 0 && minuteScaleInt <= 0) {
                        return new Response("Invalid scale duration. The duration has to be longer than 00:00", Status.BAD_REQUEST);
                    }
                }
            } catch (NumberFormatException e) {
                return new Response("Invalid scale duration.", Status.BAD_REQUEST);
            }

            Plane plane = planeStorage.get(planeID);
            Location departureLocation = locationStorage.get(salidaID);
            Location arrivalLocation = locationStorage.get(llegadaID);

            LocalDateTime departureDate = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minuteInt);

            if (scaleID.equalsIgnoreCase("Location")) {
                if (!flightStorage.add(new Flight(id, plane, departureLocation, null, arrivalLocation, departureDate, hourDurationInt, minuteDurationInt, 0, 0))) {
                    return new Response("A flight with that id already exists", Status.BAD_REQUEST);
                } else {
                    return new Response("Flight successfully registered", Status.CREATED);
                }
            } else {
                Location scaleLocation = locationStorage.get(scaleID);
                hourScaleInt = Integer.parseInt(hourScale);
                minuteScaleInt = Integer.parseInt(minuteScale);

                if (!flightStorage.add(new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hourDurationInt, minuteDurationInt, hourScaleInt, minuteScaleInt))) {
                    return new Response("A flight with that id already exists", Status.BAD_REQUEST);
                } else {
                    return new Response("Flight successfully registered", Status.CREATED);
                }
            }

        } catch (Exception e) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response delayFlight(String flightID, String hoursDelay, String minutesDelay) {
        int hDelay, mDelay;

        // Verificamos que el vuelo exista
        Flight flight = FlightStorage.getInstance().get(flightID);
        if (flight == null) {
            return new Response("Flight does not exist", Status.NOT_FOUND);
        }

        try {
            hDelay = Integer.parseInt(hoursDelay);
        } catch (NumberFormatException e) {
            return new Response("Hours delay must be numeric", Status.BAD_REQUEST);
        }

        try {
            mDelay = Integer.parseInt(minutesDelay);
        } catch (NumberFormatException e) {
            return new Response("Minutes delay must be numeric", Status.BAD_REQUEST);
        }

        flight.delay(hDelay, mDelay);
        return new Response("Flight delayed succesfully", Status.OK);
    }

    public static Response loadFlights() {
        try {
            FlightStorage.getInstance().load();
            return new Response("Flights loaded succesfully in the system", Status.CREATED);
        } catch (Exception e) {
            return new Response("Internal error loading flights in the system", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getFlights() {
        try {

            ArrayList<Flight> lista = FlightStorage.getInstance().getLista();

            if (lista.isEmpty()) {
                return new Response("There are no flights in the storage", Status.NO_CONTENT);
            }

            ArrayList<Flight> copia = new ArrayList<>();

            for (Flight flight : lista) {
                copia.add(flight.clone());
            }

            Collections.sort(copia, Comparator.comparing(flight -> {
                return flight.getFlightDM().getDepartureDate();
            }));

            return new Response("Flights information got succesfully", Status.OK, copia);
        } catch (RuntimeException e) {
            return new Response("Internal error loading flights information", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getFlightId(Object obj) {
        try {
            if (!(obj instanceof Flight)) {
                return new Response("The selection is not a flight", Status.BAD_REQUEST);
            }
            String id = ((Flight) obj).clone().getId();
            return new Response("Flight ID got succesfully", Status.OK, id);
        } catch (RuntimeException e) {
            return new Response("Internal error getting flight ID", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getFlightInfo(Object obj) {
        try {
            if (!(obj instanceof Flight)) {
                return new Response("The selection is not a flight", Status.BAD_REQUEST);
            }
            Flight flight = ((Flight) obj).clone();
            Object[] flightRow;
            if (flight.getScaleLocation() != null) {
                flightRow = new Object[]{
                    flight.getId(),
                    flight.getDepartureLocation().getAirportId(),
                    flight.getArrivalLocation().getAirportId(),
                    flight.getScaleLocation().getAirportId(),
                    flight.getFlightDM().getDepartureDate().toLocalDate(),
                    flight.getFlightDM().calculateArrivalDate().toLocalDate(),
                    flight.getPlane().getId(),
                    flight.getNumPassengers()
                };
            } else {
                flightRow = new Object[]{
                    flight.getId(),
                    flight.getDepartureLocation().getAirportId(),
                    flight.getArrivalLocation().getAirportId(),
                    "Non-Stop",
                    flight.getFlightDM().getDepartureDate().toLocalDate(),
                    flight.getFlightDM().calculateArrivalDate().toLocalDate(),
                    flight.getPlane().getId(),
                    flight.getNumPassengers()
                };
            }
            return new Response("Flight information got succesfully", Status.OK, flightRow);
        } catch (NullPointerException e) {
            return new Response("Internal error getting flight information", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getPassengerFlightInfo(Object obj) {
        try {
            if (!(obj instanceof Flight)) {
                return new Response("The selection is not a flight", Status.BAD_REQUEST);
            }
            Flight flight = ((Flight) obj).clone();
            Object[] flightRow;
            if (flight.getScaleLocation() != null) {
                flightRow = new Object[]{
                    flight.getId(),
                    flight.getFlightDM().getDepartureDate().toLocalDate(),
                    flight.getFlightDM().getDepartureDate().toLocalDate()
                };
            } else {
                flightRow = new Object[]{
                    flight.getId(),
                    flight.getFlightDM().getDepartureDate().toLocalDate(),
                    flight.getFlightDM().getDepartureDate().toLocalDate()
                };
            }
            return new Response("Passenger flight information got succesfully", Status.OK, flightRow);
        } catch (NullPointerException e) {
            return new Response("Internal error getting passenger flight information", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
