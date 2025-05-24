package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.location.Location;
import core.models.storage.LocationStorage;
import java.util.ArrayList;

public class LocationController {

    public static Response LocationRegistration(String id, String name, String city, String country, String latitud, String longitud) {
        try {

            float latitudFl, longitudFl;
            LocationStorage storage = LocationStorage.getInstance();

            // Verificar si el ID tiene 3 letras mayusculas
            try {
                if (id.length() == 3) {
                    for (int i = 0; i < id.length(); i++) {
                        if (Character.isLowerCase(id.charAt(i))) {
                            return new Response("ID must contain 3 uppercase letters.", Status.BAD_REQUEST);
                        }
                    }
                } else {
                    return new Response("ID must contain 3 uppercase letters.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
            }

            // Verificar si el ID es unico
            try {
                if (storage.get(id) != null) {
                    return new Response("Location ID already exist.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Database does not exist", Status.INTERNAL_SERVER_ERROR);
            }

            // Verificar latitud
            try {
                latitudFl = Float.parseFloat(latitud);

                int decimales = Float.toString(latitudFl).length() - Float.toString(latitudFl).indexOf('.') - 1;

                if (decimales > 4) {
                    return new Response("Latitude must have a maximum of 4 decimal places.", Status.BAD_REQUEST);
                }

                if (latitudFl < -90.0 | latitudFl > 90.0) {
                    return new Response("Valid latitudes are in the range [-90, 90].", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException e) {
                return new Response("Latitude must be a numeric value", Status.BAD_REQUEST);
            }

            // Verificar longitud
            try {
                longitudFl = Float.parseFloat(longitud);

                int decimales = Float.toString(longitudFl).length() - Float.toString(longitudFl).indexOf('.') - 1;

                if (decimales > 4) {
                    return new Response("Longitude must have a maximum of 4 decimal places.", Status.BAD_REQUEST);
                }

                if (longitudFl < -180.0 | longitudFl > 180.0) {
                    return new Response("Valid Longitudes are in the range [-180, 180].", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException e) {
                return new Response("Longitude must be a numeric value", Status.BAD_REQUEST);
            }

            // Verificar si el resto de campos no estan vacios
            try {
                if (name.isBlank()) {
                    return new Response("Airport name field must be filled in.", Status.BAD_REQUEST);
                }

                if (city.isBlank()) {
                    return new Response("Airport city field must be filled in.", Status.BAD_REQUEST);
                }

                if (country.isBlank()) {
                    return new Response("Airport country field must be filled in.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("All fields must be filled in.", Status.BAD_REQUEST);
            }

            try {

                if (!storage.add(new Location(id, name, city, country, Double.parseDouble(latitud), Double.parseDouble(longitud)))) {
                    return new Response("A location with that id already exists", Status.BAD_REQUEST);
                } else {
                    return new Response("Location successfully registered", Status.CREATED);
                }

            } catch (NumberFormatException e) {
                return new Response("Database does not exist.", Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }

    }

    public static Response getLocations() {
        try {
            boolean sw = LocationStorage.getInstance().load();

            if (!sw) {
                return new Response("Error en el cargado de las ubicaciones", Status.INTERNAL_SERVER_ERROR);
            }

            ArrayList<Location> lista = LocationStorage.getInstance().getLista();
            ArrayList<Location> copia = new ArrayList<>();
            for (Location location : lista) {
                copia.add(location.clone());
            }

            return new Response("Ubicaciones cargadas correctamente", Status.OK, copia);
        } catch (CloneNotSupportedException e) {
            return new Response("Error interno obteniendo las ubicaciones", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getLocationId(Object obj) {
        try {
            if (!(obj instanceof Location)) {
                return new Response("Ubicacion no válida", Status.BAD_REQUEST);
            }
            String id = ((Location) obj).clone().getAirportId();
            return new Response("Id de la ubicación obtenida exitosamente", Status.OK, id);
        } catch (CloneNotSupportedException e) {
            return new Response("Error interno obteniendo el id de las ubicaciones", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
