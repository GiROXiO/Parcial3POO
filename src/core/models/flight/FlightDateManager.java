/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.flight;

import java.time.LocalDateTime;

/**
 *
 * @author jecas
 */
public class FlightDateManager implements FlightDateManagerInterface{
    private LocalDateTime departureDate;
    private int hoursDurationArrival;
    private int minutesDurationArrival;
    private int hoursDurationScale;
    private int minutesDurationScale;

    public FlightDateManager(LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival, int hoursDurationScale, int minutesDurationScale) {
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        this.hoursDurationScale = hoursDurationScale;
        this.minutesDurationScale = minutesDurationScale;
    }
    
    public FlightDateManager(LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
    }
    
    @Override
    public LocalDateTime calculateArrivalDate() {
        return this.departureDate.plusHours(hoursDurationScale).plusHours(hoursDurationArrival).plusMinutes(minutesDurationScale).plusMinutes(minutesDurationArrival);
    }

    @Override
    public void delay(int hours, int minutes) {
        this.departureDate = this.departureDate.plusHours(hours).plusMinutes(minutes);
    }
    
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public int getHoursDurationArrival() {
        return hoursDurationArrival;
    }

    public void setHoursDurationArrival(int hoursDurationArrival) {
        this.hoursDurationArrival = hoursDurationArrival;
    }

    public int getMinutesDurationArrival() {
        return minutesDurationArrival;
    }

    public void setMinutesDurationArrival(int minutesDurationArrival) {
        this.minutesDurationArrival = minutesDurationArrival;
    }

    public int getHoursDurationScale() {
        return hoursDurationScale;
    }

    public void setHoursDurationScale(int hoursDurationScale) {
        this.hoursDurationScale = hoursDurationScale;
    }

    public int getMinutesDurationScale() {
        return minutesDurationScale;
    }

    public void setMinutesDurationScale(int minutesDurationScale) {
        this.minutesDurationScale = minutesDurationScale;
    }
}
