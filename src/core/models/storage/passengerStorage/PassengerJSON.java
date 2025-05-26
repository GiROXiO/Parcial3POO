package core.models.storage.passengerStorage;

import core.models.passenger.Passenger;
import core.models.passenger.PhoneNumber;
import core.models.storage.JsonTransformer;
import java.time.LocalDate;
import org.json.JSONObject;

public class PassengerJSON implements JsonTransformer<Passenger>{
    
    @Override
    public Passenger fromJson(JSONObject obj)
    {
        long id = obj.getLong("id");
        String firstname = obj.getString("firstname");
        String lastname = obj.getString("lastname");
        LocalDate birthDate = LocalDate.parse(obj.getString("birthDate"));
        int countryPhoneCode = obj.getInt("countryPhoneCode");
        long phone = obj.getLong("phone");
        PhoneNumber phoneNumber = new PhoneNumber(countryPhoneCode, phone);
        String country = obj.getString("country");
        
        return new Passenger(id, firstname, lastname, birthDate, phoneNumber, country);
    }
}
