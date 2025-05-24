package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.plane.Plane;
import core.models.storage.PlaneStorage;
import java.util.ArrayList;

public class PlaneController {

    public static Response planeRegistration(String id, String brand, String model, String maxCapacity, String airline) {
        try {
            PlaneStorage storage = PlaneStorage.getInstance();
            int maxCapacityInt;
            // Se verifica si el ID sigue el formato XXYYYYY
            try {

                if (id.length() == 7) {
                    for (int i = 0; i <= 1; i++) {

                        if (Character.isDigit(id.charAt(i))) {
                            return new Response("ID must follow the XXYYYYY format. X must correspond to a capital letter, not a numeric or blank value.", Status.BAD_REQUEST);
                        }

                        if (Character.isLowerCase(id.charAt(i))) {
                            return new Response("ID must follow the XXYYYYY format. X must correspond to a capital letter, not a lower case letter.", Status.BAD_REQUEST);
                        }
                    }

                    for (int i = 2; i <= 6; i++) {
                        if (!Character.isDigit(id.charAt(i))) {
                            return new Response("ID must follow the XXYYYYY format. Y must correspond to a numeric value, not a letter or blank value.", Status.BAD_REQUEST);
                        }
                    }
                } else {
                    return new Response("ID must follow the XXYYYYY format", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("ID must follow the XXYYYYY format.", Status.BAD_REQUEST);
            }

            // Verificar si el ID es unico
            try {
                if (storage.get(id) != null) {
                    return new Response("Plane ID already exist.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Database does not exist", Status.INTERNAL_SERVER_ERROR);
            }

            // Se verifica si los demás campos no estan vacios
          
            if (brand.isBlank()) {
                return new Response("Brand field must be filled in.", Status.BAD_REQUEST);
            }

            if (model.isBlank()) {
                return new Response("Model field must be filled in.", Status.BAD_REQUEST);
            }

            if (maxCapacity.isBlank()) {
                return new Response("Max capacity field must be filled in.", Status.BAD_REQUEST);
            }

            if (airline.isBlank()) {
                return new Response("Airline field must be filled in.", Status.BAD_REQUEST);
            }
             
            
            // Se verifica si Max Capacity es un valor numérico
            try {
                maxCapacityInt = Integer.parseInt(maxCapacity);
            } catch (NumberFormatException e) {
                return new Response("Max capacity must be a integer.", Status.BAD_REQUEST);
            }

            if (!storage.add(new Plane(id, brand, model, maxCapacityInt, airline))) {
                return new Response("A plane with that id already exists", Status.BAD_REQUEST);
            } else {
                return new Response("Plane successfully registered", Status.CREATED);
            }
        } catch (Exception e) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }

    }

    public static Response getPlanes(){
        try {
            boolean sw = PlaneStorage.getInstance().load();

            if (!sw) {
                return new Response("Error en el cargado de los aviones", Status.INTERNAL_SERVER_ERROR);
            }

            ArrayList<Plane> lista = PlaneStorage.getInstance().getLista();
            
            if(lista.isEmpty()){
                return new Response("No hay datos de aviones", Status.NO_CONTENT);
            }
            
            ArrayList<Plane> copia = new ArrayList<>();
            for (Plane plane : lista) {
                copia.add(plane.clone());
            }

            return new Response("Aviones cargados correctamente", Status.OK, copia);
        } catch (CloneNotSupportedException e) {
            return new Response("Error interno obteniendo los aviones", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getPlaneId(Object obj){
        try {
            if (!(obj instanceof Plane)) {
                return new Response("Avión no válido", Status.BAD_REQUEST);
            }
            String id = ((Plane) obj).clone().getId();
            return new Response("Id del avión obtenida exitosamente", Status.OK, id);
        } catch (CloneNotSupportedException e) {
            return new Response("Error interno obteniendo id del avion", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response getPlaneRow(Object obj) {
        try {
            if (!(obj instanceof Plane)) {
                return new Response("El item seleccionado no es un avion", Status.BAD_REQUEST);
            }
            Plane plane = ((Plane) obj).clone();
            Object[] planeRow = new Object[]{
                plane.getId(),
                plane.getBrand(),
                plane.getModel(),
                plane.getMaxCapacity(),
                plane.getAirline(),
                plane.getNumFlights()
            };
            return new Response("Avion obtenido exitosamente", Status.OK, planeRow);
        } catch (CloneNotSupportedException e) {
            return new Response("Error interno obteniendo el avion", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
