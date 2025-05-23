/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.FlatDarkLaf;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PassengerStorage;
import core.models.storage.PlaneStorage;
import core.models.storage.utils.JsonStorage;
import core.views.AirportFrame;
import javax.swing.UIManager;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jecas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.out.println("LocationStorage: "+LocationStorage.getInstance().load());
        System.out.println("PlaneStorage: "+PlaneStorage.getInstance().load());
        System.out.println("PassengerStorage: "+PassengerStorage.getInstance().load());
        System.out.println("FlightStorage: "+FlightStorage.getInstance().load());

        System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AirportFrame().setVisible(true);
            }
        });
    }
}
