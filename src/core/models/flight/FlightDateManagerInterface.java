/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.flight;

import java.time.LocalDateTime;

/**
 *
 * @author jecas
 */
public interface FlightDateManagerInterface {
    public LocalDateTime calculateArrivalDate();
    public void delay(int hours, int minutes);
}
