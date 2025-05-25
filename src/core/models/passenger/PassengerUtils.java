package core.models.passenger;

import java.time.LocalDate;
import java.time.Period;

public class PassengerUtils {
    
    public static int calculateAge(Passenger p)
    {
        return Period.between(p.getBirthDate(), LocalDate.now()).getYears();
    }
    
    public static String getFullName(Passenger p)
    {
        return p.getFirstname() + " " + p.getLastname();
    }
    
}
