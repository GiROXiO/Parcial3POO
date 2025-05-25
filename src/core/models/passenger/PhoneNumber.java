package core.models.passenger;

public class PhoneNumber implements PhoneNumberInterface{
    private int countryCode;
    private long phoneNumber;
    
    public PhoneNumber(int countryCode, long phoneNumber)
    {
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }
    
    public int getCountryCode() {
        return countryCode;
    }
    
    @Override
    public String getFullPhone()
    {
        return "+" + countryCode + " " + phoneNumber;
    }

    
    
}
