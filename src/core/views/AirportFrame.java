/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package core.views;

import core.controllers.PlaneController;
import core.controllers.FlightController;
import core.controllers.LocationController;
import core.controllers.PassengerController;
import core.controllers.utils.Response;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edangulo
 */
public class AirportFrame extends javax.swing.JFrame {

    /**
     * Creates new form AirportFrame
     */
    private int x, y;

    public AirportFrame() {
        initComponents();

        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);

        this.generateMonths();
        this.generateDays();
        this.generateHours();
        this.generateMinutes();
        this.loadLocations();
        this.loadPlanes();
        this.loadFlights();
        this.loadPassengers();
        this.generateLocations();
        this.generatePlanes();
        this.generateFlights();
        this.generatePassengers();
        this.blockPanels();
    }

    private void blockPanels() {
        //9, 11
        for (int i = 1; i < Tabs.getTabCount(); i++) {
            if (i != 9 && i != 11) {
                Tabs.setEnabledAt(i, false);
            }
        }
    }

    private void generateMonths() {
        for (int i = 1; i < 13; i++) {
            PR_MonthComboBox.addItem("" + i);
            FR_MonthComboBox.addItem("" + i);
            UI_MonthComboBox.addItem("" + i);
        }
    }

    private void generateDays() {
        for (int i = 1; i < 32; i++) {
            PR_DayComboBox.addItem("" + i);
            FR_DayComboBox.addItem("" + i);
            UI_DayComboBox.addItem("" + i);
        }
    }

    private void generateHours() {
        for (int i = 0; i < 24; i++) {
            FR_DDHourComboBox.addItem("" + i);
            FR_D1HourComboBox.addItem("" + i);
            FR_D2HourComboBox.addItem("" + i);
            DF_HourComboBox.addItem("" + i);
        }
    }

    private void generateMinutes() {
        for (int i = 0; i < 60; i++) {
            FR_DDMinuteComboBox.addItem("" + i);
            FR_D1MinuteComboBox.addItem("" + i);
            FR_D2MinuteComboBox.addItem("" + i);
            DF_MinuteComboBox.addItem("" + i);
        }
    }

    private void loadLocations() {
        Response response = LocationController.loadLocations();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void loadPlanes() {
        Response response = PlaneController.loadPlanes();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void loadFlights() {
        Response response = FlightController.loadFlights();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void loadPassengers() {
        Response response = PassengerController.loadPassengers();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void generateLocations() {
        Response response = LocationController.getLocations();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else if (response.getStatus() == 204) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<Object> lista = (ArrayList<Object>) response.getObject();
            for (Object obj : lista) {
                Response airportId = LocationController.getLocationId(obj);
                if (airportId.getStatus() >= 500) {
                    JOptionPane.showMessageDialog(null, airportId.getMessage(), "Error " + airportId.getStatus(), JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (airportId.getStatus() >= 400) {
                    JOptionPane.showMessageDialog(null, airportId.getMessage(), "Error " + airportId.getStatus(), JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    FR_DepartureLocationComboBox.addItem((String) airportId.getObject());
                    FR_ArrivalLocationComboBox.addItem((String) airportId.getObject());
                    FR_ScaleLocationComboBox.addItem((String) airportId.getObject());
                }
            }
            JOptionPane.showMessageDialog(null, "Locations ID loaded succesfully in ComboBox", "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void generatePlanes() {
        Response response = PlaneController.getPlanes();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else if (response.getStatus() == 204) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<Object> lista = (ArrayList<Object>) response.getObject();
            for (Object obj : lista) {
                Response planeId = PlaneController.getPlaneId(obj);
                if (planeId.getStatus() >= 500) {
                    JOptionPane.showMessageDialog(null, planeId.getMessage(), "Error " + planeId.getStatus(), JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (planeId.getStatus() >= 400) {
                    JOptionPane.showMessageDialog(null, planeId.getMessage(), "Error " + planeId.getStatus(), JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    FR_PlaneComboBox.addItem((String) planeId.getObject());
                }
            }
            JOptionPane.showMessageDialog(null, "Planes ID loaded succesfully in ComboBox", "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void generateFlights() {
        Response response = FlightController.getFlights();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else if (response.getStatus() == 204) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<Object> lista = (ArrayList<Object>) response.getObject();
            for (Object obj : lista) {
                Response flightId = FlightController.getFlightId(obj);
                if (flightId.getStatus() >= 500) {
                    JOptionPane.showMessageDialog(null, flightId.getMessage(), "Error " + flightId.getStatus(), JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (flightId.getStatus() >= 400) {
                    JOptionPane.showMessageDialog(null, flightId.getMessage(), "Error " + flightId.getStatus(), JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    DF_IdComboBox.addItem((String) flightId.getObject());
                    ATF_FlightComboBox.addItem((String) flightId.getObject());
                }
            }
            JOptionPane.showMessageDialog(null, "Flights ID loaded succesfully in ComboBox", "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void generatePassengers() {
        Response response = PassengerController.getPassengers();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else if (response.getStatus() == 204) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<Object> lista = (ArrayList<Object>) response.getObject();
            for (Object obj : lista) {
                Response passengerId = PassengerController.getPassengerId(obj);
                if (passengerId.getStatus() >= 500) {
                    JOptionPane.showMessageDialog(null, passengerId.getMessage(), "Error " + passengerId.getStatus(), JOptionPane.ERROR_MESSAGE);
                } else if (passengerId.getStatus() >= 400) {
                    JOptionPane.showMessageDialog(null, passengerId.getMessage(), "Error " + passengerId.getStatus(), JOptionPane.WARNING_MESSAGE);
                } else {
                    userSelect.addItem((String) passengerId.getObject());
                }
            }
            JOptionPane.showMessageDialog(null, "Passengers ID loaded succesfully in ComboBox", "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showAllFlights() {
        DefaultTableModel model = (DefaultTableModel) SAFlights_Table.getModel();
        model.setRowCount(0);
        Response response = FlightController.getFlights();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else if (response.getStatus() == 204) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<Object> lista = (ArrayList<Object>) response.getObject();
            for (Object obj : lista) {
                Response flight = FlightController.getFlightInfo(obj);
                if (flight.getStatus() >= 500) {
                    JOptionPane.showMessageDialog(null, flight.getMessage(), "Error " + flight.getStatus(), JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (flight.getStatus() >= 400) {
                    JOptionPane.showMessageDialog(null, flight.getMessage(), "Error " + flight.getStatus(), JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    Object[] row = (Object[]) flight.getObject();
                    model.addRow(row);
                }
            }
            JOptionPane.showMessageDialog(null, "Flights information loaded succesfully in table", "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showAllPassengers() {
        DefaultTableModel model = (DefaultTableModel) SAPassengers_Table.getModel();
        model.setRowCount(0);
        Response response = PassengerController.getPassengers();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else if (response.getStatus() == 204) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<Object> lista = (ArrayList<Object>) response.getObject();
            for (Object obj : lista) {
                Response passenger = PassengerController.getPassengerInfo(obj);
                if (passenger.getStatus() >= 500) {
                    JOptionPane.showMessageDialog(null, passenger.getMessage(), "Error " + passenger.getStatus(), JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (passenger.getStatus() >= 400) {
                    JOptionPane.showMessageDialog(null, passenger.getMessage(), "Error " + passenger.getStatus(), JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    Object[] row = (Object[]) passenger.getObject();
                    model.addRow(row);
                }
            }
            JOptionPane.showMessageDialog(null, "Passengers information loaded succesfully in table", "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showAllPlanes() {
        DefaultTableModel model = (DefaultTableModel) SAPlanes_Table.getModel();
        model.setRowCount(0);
        Response response = PlaneController.getPlanes();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else if (response.getStatus() == 204) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<Object> lista = (ArrayList<Object>) response.getObject();
            for (Object obj : lista) {
                Response plane = PlaneController.getPlaneInfo(obj);
                if (plane.getStatus() >= 500) {
                    JOptionPane.showMessageDialog(null, plane.getMessage(), "Error " + plane.getStatus(), JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (plane.getStatus() >= 400) {
                    JOptionPane.showMessageDialog(null, plane.getMessage(), "Error " + plane.getStatus(), JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    Object[] row = (Object[]) plane.getObject();
                    model.addRow(row);
                }
            }
            JOptionPane.showMessageDialog(null, "Planes information loaded succesfully in table", "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showAllLocations() {
        DefaultTableModel model = (DefaultTableModel) SALocations_Table.getModel();
        model.setRowCount(0);
        Response response = LocationController.getLocations();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else if (response.getStatus() == 204) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<Object> lista = (ArrayList<Object>) response.getObject();
            for (Object obj : lista) {
                Response location = LocationController.getLocationInfo(obj);
                if (location.getStatus() >= 500) {
                    JOptionPane.showMessageDialog(null, location.getMessage(), "Error " + location.getStatus(), JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (location.getStatus() >= 400) {
                    JOptionPane.showMessageDialog(null, location.getMessage(), "Error " + location.getStatus(), JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    Object[] row = (Object[]) location.getObject();
                    model.addRow(row);
                }
            }
            JOptionPane.showMessageDialog(null, "Locations information loaded succesfully in table", "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showPassengerFlights(String passengerId) {
        DefaultTableModel model = (DefaultTableModel) SMFlights_Table.getModel();
        model.setRowCount(0);
        Response response = PassengerController.getPassengerFlights(passengerId);
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else if (response.getStatus() == 204) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<Object> lista = (ArrayList<Object>) response.getObject();
            for (Object obj : lista) {
                Response flight = FlightController.getPassengerFlightInfo(obj);
                if (flight.getStatus() >= 500) {
                    JOptionPane.showMessageDialog(null, flight.getMessage(), "Error " + flight.getStatus(), JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (flight.getStatus() >= 400) {
                    JOptionPane.showMessageDialog(null, flight.getMessage(), "Error " + flight.getStatus(), JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    Object[] row = (Object[]) flight.getObject();
                    model.addRow(row);
                }
            }
            JOptionPane.showMessageDialog(null, "Passenger flights loaded succesfully in table", "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new core.views.PanelRound();
        panelRound2 = new core.views.PanelRound();
        exitButton = new javax.swing.JButton();
        Tabs = new javax.swing.JTabbedPane();
        tabAdministration = new javax.swing.JPanel();
        user = new javax.swing.JRadioButton();
        administrator = new javax.swing.JRadioButton();
        userSelect = new javax.swing.JComboBox<>();
        tabPassengerRegistration = new javax.swing.JPanel();
        lblCountry = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PR_codigoPaisTextField = new javax.swing.JTextField();
        PR_IDTextField = new javax.swing.JTextField();
        PR_YearTextField = new javax.swing.JTextField();
        PR_PaisTextField = new javax.swing.JTextField();
        PR_NumeroTelefonicoTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PR_LastNameTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        PR_MonthComboBox = new javax.swing.JComboBox<>();
        PR_FirstNameTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        PR_DayComboBox = new javax.swing.JComboBox<>();
        PR_RegisterButton = new javax.swing.JButton();
        tabAirplaneRegistration = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        AR_IdTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        AR_BrandTextField = new javax.swing.JTextField();
        AR_ModelTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        AR_MaxCapacityTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        AR_AirlineTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        AR_CreateButton = new javax.swing.JButton();
        tabLocationRegistration = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        LR_AirportIdTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        LR_AirportNameTextField = new javax.swing.JTextField();
        LR_AirportCityTextField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        LR_AirportCountryTextField = new javax.swing.JTextField();
        LR_AirportLatitudeTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        LR_AirportLongitudeTextField = new javax.swing.JTextField();
        LR_CreateButton = new javax.swing.JButton();
        tabFlightRegistration = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        FR_IdTextField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        FR_PlaneComboBox = new javax.swing.JComboBox<>();
        FR_DepartureLocationComboBox = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        FR_ArrivalLocationComboBox = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        FR_ScaleLocationComboBox = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        FR_YearTextField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        FR_MonthComboBox = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        FR_DayComboBox = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        FR_DDHourComboBox = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        FR_DDMinuteComboBox = new javax.swing.JComboBox<>();
        FR_D1HourComboBox = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        FR_D1MinuteComboBox = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        FR_D2HourComboBox = new javax.swing.JComboBox<>();
        FR_D2MinuteComboBox = new javax.swing.JComboBox<>();
        FR_CreateButton = new javax.swing.JButton();
        tabUpdateInfo = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        UI_IdTextField = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        UI_FirstNameTextField = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        UI_LastNameTextField = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        UI_BirthdayTextField = new javax.swing.JTextField();
        UI_MonthComboBox = new javax.swing.JComboBox<>();
        UI_DayComboBox = new javax.swing.JComboBox<>();
        UI_NumeroTelefonicoTextField = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        UI_codigoPaisTextField = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        UI_PaisTextField = new javax.swing.JTextField();
        UF_Update = new javax.swing.JButton();
        tabAddToFlight = new javax.swing.JPanel();
        ATF_IdTextField = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        ATF_FlightComboBox = new javax.swing.JComboBox<>();
        ATF_AddButton = new javax.swing.JButton();
        tabShowFlights = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SMFlights_Table = new javax.swing.JTable();
        SMFlights_RefreshButton = new javax.swing.JButton();
        tabShowAllPassengers = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        SAPassengers_Table = new javax.swing.JTable();
        SMPassengers_RefreshButton = new javax.swing.JButton();
        tabShowAllFlights = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        SAFlights_Table = new javax.swing.JTable();
        SAFlights_RefreshButton = new javax.swing.JButton();
        tabShowAllPlanes = new javax.swing.JPanel();
        SAPlanes_RefreshButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        SAPlanes_Table = new javax.swing.JTable();
        tabShowAllLocations = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        SALocations_Table = new javax.swing.JTable();
        SALocations_RefreshButton = new javax.swing.JButton();
        tabDelayFlight = new javax.swing.JPanel();
        DF_HourComboBox = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        DF_IdComboBox = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        DF_MinuteComboBox = new javax.swing.JComboBox<>();
        DF_DelayButton = new javax.swing.JButton();
        panelRound3 = new core.views.PanelRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setRadius(40);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelRound2MouseDragged(evt);
            }
        });
        panelRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelRound2MousePressed(evt);
            }
        });

        exitButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        exitButton.setText("X");
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(1083, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addComponent(exitButton)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        Tabs.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N

        tabAdministration.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        user.setText("User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        tabAdministration.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        administrator.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        administrator.setText("Administrator");
        administrator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administratorActionPerformed(evt);
            }
        });
        tabAdministration.add(administrator, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 164, -1, -1));

        userSelect.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        userSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select User" }));
        userSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userSelectActionPerformed(evt);
            }
        });
        tabAdministration.add(userSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 130, -1));

        Tabs.addTab("Administration", tabAdministration);

        tabPassengerRegistration.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountry.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        lblCountry.setText("Country:");
        tabPassengerRegistration.add(lblCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel2.setText("ID:");
        tabPassengerRegistration.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel3.setText("First Name:");
        tabPassengerRegistration.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel4.setText("Last Name:");
        tabPassengerRegistration.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel5.setText("Birthdate:");
        tabPassengerRegistration.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setText("+");
        tabPassengerRegistration.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 20, -1));

        PR_codigoPaisTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabPassengerRegistration.add(PR_codigoPaisTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 50, -1));

        PR_IDTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabPassengerRegistration.add(PR_IDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 130, -1));

        PR_YearTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabPassengerRegistration.add(PR_YearTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 90, -1));

        PR_PaisTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabPassengerRegistration.add(PR_PaisTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 130, -1));

        PR_NumeroTelefonicoTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabPassengerRegistration.add(PR_NumeroTelefonicoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 130, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel7.setText("Phone:");
        tabPassengerRegistration.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("-");
        tabPassengerRegistration.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 30, -1));

        PR_LastNameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabPassengerRegistration.add(PR_LastNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 130, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("-");
        tabPassengerRegistration.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 30, -1));

        PR_MonthComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PR_MonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));
        PR_MonthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PR_MonthComboBoxActionPerformed(evt);
            }
        });
        tabPassengerRegistration.add(PR_MonthComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        PR_FirstNameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabPassengerRegistration.add(PR_FirstNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 130, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setText("-");
        tabPassengerRegistration.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 30, -1));

        PR_DayComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PR_DayComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));
        tabPassengerRegistration.add(PR_DayComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, -1));

        PR_RegisterButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PR_RegisterButton.setText("Register");
        PR_RegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PR_RegisterButtonActionPerformed(evt);
            }
        });
        tabPassengerRegistration.add(PR_RegisterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, -1, -1));

        Tabs.addTab("Passenger registration", tabPassengerRegistration);

        tabAirplaneRegistration.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setText("ID:");
        tabAirplaneRegistration.add(jLabel11);
        jLabel11.setBounds(53, 96, 22, 25);

        AR_IdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabAirplaneRegistration.add(AR_IdTextField);
        AR_IdTextField.setBounds(180, 93, 130, 31);

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel12.setText("Brand:");
        tabAirplaneRegistration.add(jLabel12);
        jLabel12.setBounds(53, 157, 52, 25);

        AR_BrandTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabAirplaneRegistration.add(AR_BrandTextField);
        AR_BrandTextField.setBounds(180, 154, 130, 31);

        AR_ModelTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabAirplaneRegistration.add(AR_ModelTextField);
        AR_ModelTextField.setBounds(180, 213, 130, 31);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setText("Model:");
        tabAirplaneRegistration.add(jLabel13);
        jLabel13.setBounds(53, 216, 57, 25);

        AR_MaxCapacityTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabAirplaneRegistration.add(AR_MaxCapacityTextField);
        AR_MaxCapacityTextField.setBounds(180, 273, 130, 31);

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setText("Max Capacity:");
        tabAirplaneRegistration.add(jLabel14);
        jLabel14.setBounds(53, 276, 114, 25);

        AR_AirlineTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tabAirplaneRegistration.add(AR_AirlineTextField);
        AR_AirlineTextField.setBounds(180, 333, 130, 31);

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setText("Airline:");
        tabAirplaneRegistration.add(jLabel15);
        jLabel15.setBounds(53, 336, 70, 25);

        AR_CreateButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AR_CreateButton.setText("Create");
        AR_CreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AR_CreateButtonActionPerformed(evt);
            }
        });
        tabAirplaneRegistration.add(AR_CreateButton);
        AR_CreateButton.setBounds(490, 480, 120, 40);

        Tabs.addTab("Airplane registration", tabAirplaneRegistration);

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setText("Airport ID:");

        LR_AirportIdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setText("Airport name:");

        LR_AirportNameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LR_AirportCityTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setText("Airport city:");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setText("Airport country:");

        LR_AirportCountryTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LR_AirportLatitudeTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setText("Airport latitude:");

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setText("Airport longitude:");

        LR_AirportLongitudeTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LR_CreateButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        LR_CreateButton.setText("Create");
        LR_CreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LR_CreateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabLocationRegistrationLayout = new javax.swing.GroupLayout(tabLocationRegistration);
        tabLocationRegistration.setLayout(tabLocationRegistrationLayout);
        tabLocationRegistrationLayout.setHorizontalGroup(
            tabLocationRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabLocationRegistrationLayout.createSequentialGroup()
                .addGroup(tabLocationRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabLocationRegistrationLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(tabLocationRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(80, 80, 80)
                        .addGroup(tabLocationRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LR_AirportLongitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LR_AirportIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LR_AirportNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LR_AirportCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LR_AirportCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LR_AirportLatitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tabLocationRegistrationLayout.createSequentialGroup()
                        .addGap(515, 515, 515)
                        .addComponent(LR_CreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(515, 515, 515))
        );
        tabLocationRegistrationLayout.setVerticalGroup(
            tabLocationRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabLocationRegistrationLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(tabLocationRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabLocationRegistrationLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel17)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel18)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel19)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel20))
                    .addGroup(tabLocationRegistrationLayout.createSequentialGroup()
                        .addComponent(LR_AirportIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(LR_AirportNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(LR_AirportCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(LR_AirportCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(LR_AirportLatitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(tabLocationRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(LR_AirportLongitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(LR_CreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        Tabs.addTab("Location registration", tabLocationRegistration);

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setText("ID:");

        FR_IdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setText("Plane:");

        FR_PlaneComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_PlaneComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plane" }));
        FR_PlaneComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FR_PlaneComboBoxActionPerformed(evt);
            }
        });

        FR_DepartureLocationComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_DepartureLocationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));
        FR_DepartureLocationComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FR_DepartureLocationComboBoxMouseClicked(evt);
            }
        });
        FR_DepartureLocationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FR_DepartureLocationComboBoxActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel24.setText("Departure location:");

        FR_ArrivalLocationComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_ArrivalLocationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));
        FR_ArrivalLocationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FR_ArrivalLocationComboBoxActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel25.setText("Arrival location:");

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel26.setText("Scale location:");

        FR_ScaleLocationComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_ScaleLocationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));
        FR_ScaleLocationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FR_ScaleLocationComboBoxActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel27.setText("Duration:");

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel28.setText("Duration:");

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel29.setText("Departure date:");

        FR_YearTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel30.setText("-");

        FR_MonthComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_MonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel31.setText("-");

        FR_DayComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_DayComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel32.setText("-");

        FR_DDHourComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_DDHourComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel33.setText("-");

        FR_DDMinuteComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_DDMinuteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        FR_D1HourComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_D1HourComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel34.setText("-");

        FR_D1MinuteComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_D1MinuteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel35.setText("-");

        FR_D2HourComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_D2HourComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        FR_D2MinuteComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_D2MinuteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        FR_CreateButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FR_CreateButton.setText("Create");
        FR_CreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FR_CreateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabFlightRegistrationLayout = new javax.swing.GroupLayout(tabFlightRegistration);
        tabFlightRegistration.setLayout(tabFlightRegistrationLayout);
        tabFlightRegistrationLayout.setHorizontalGroup(
            tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FR_ScaleLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabFlightRegistrationLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FR_ArrivalLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(46, 46, 46)
                        .addComponent(FR_DepartureLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                        .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FR_IdTextField)
                            .addComponent(FR_PlaneComboBox, 0, 130, Short.MAX_VALUE))))
                .addGap(45, 45, 45)
                .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                        .addComponent(FR_YearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(FR_MonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(FR_DayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(FR_DDHourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(FR_DDMinuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                        .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                                .addComponent(FR_D1HourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(FR_D1MinuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                                .addComponent(FR_D2HourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(FR_D2MinuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabFlightRegistrationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FR_CreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(530, 530, 530))
        );
        tabFlightRegistrationLayout.setVerticalGroup(
            tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22))
                    .addComponent(FR_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(FR_PlaneComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FR_DDHourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(FR_DDMinuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabFlightRegistrationLayout.createSequentialGroup()
                        .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(FR_DepartureLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29))
                            .addComponent(FR_YearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FR_MonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(FR_DayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(FR_ArrivalLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28))
                            .addComponent(FR_D1HourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(FR_D1MinuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FR_D2HourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(FR_D2MinuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabFlightRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(FR_ScaleLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(FR_CreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        Tabs.addTab("Flight registration", tabFlightRegistration);

        jLabel36.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel36.setText("ID:");

        UI_IdTextField.setEditable(false);
        UI_IdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UI_IdTextField.setEnabled(false);
        UI_IdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UI_IdTextFieldActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel37.setText("First Name:");

        UI_FirstNameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel38.setText("Last Name:");

        UI_LastNameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel39.setText("Birthdate:");

        UI_BirthdayTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        UI_MonthComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UI_MonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        UI_DayComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UI_DayComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        UI_NumeroTelefonicoTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel40.setText("-");

        UI_codigoPaisTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel41.setText("+");

        jLabel42.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel42.setText("Phone:");

        jLabel43.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel43.setText("Country:");

        UI_PaisTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        UF_Update.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UF_Update.setText("Update");
        UF_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UF_UpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabUpdateInfoLayout = new javax.swing.GroupLayout(tabUpdateInfo);
        tabUpdateInfo.setLayout(tabUpdateInfoLayout);
        tabUpdateInfoLayout.setHorizontalGroup(
            tabUpdateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUpdateInfoLayout.createSequentialGroup()
                .addGroup(tabUpdateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabUpdateInfoLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(tabUpdateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabUpdateInfoLayout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(108, 108, 108)
                                .addComponent(UI_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabUpdateInfoLayout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(41, 41, 41)
                                .addComponent(UI_FirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabUpdateInfoLayout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(43, 43, 43)
                                .addComponent(UI_LastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabUpdateInfoLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(55, 55, 55)
                                .addComponent(UI_BirthdayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(UI_MonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(UI_DayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabUpdateInfoLayout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(UI_codigoPaisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(UI_NumeroTelefonicoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabUpdateInfoLayout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(63, 63, 63)
                                .addComponent(UI_PaisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tabUpdateInfoLayout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(UF_Update)))
                .addContainerGap(586, Short.MAX_VALUE))
        );
        tabUpdateInfoLayout.setVerticalGroup(
            tabUpdateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUpdateInfoLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(tabUpdateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(UI_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(tabUpdateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(UI_FirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(tabUpdateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(UI_LastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(tabUpdateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(UI_BirthdayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UI_MonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UI_DayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(tabUpdateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41)
                    .addComponent(UI_codigoPaisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(UI_NumeroTelefonicoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(tabUpdateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(UI_PaisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(UF_Update)
                .addGap(113, 113, 113))
        );

        Tabs.addTab("Update info", tabUpdateInfo);

        ATF_IdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ATF_IdTextField.setEnabled(false);

        jLabel44.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel44.setText("ID:");

        jLabel45.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel45.setText("Flight:");

        ATF_FlightComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ATF_FlightComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flight" }));

        ATF_AddButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ATF_AddButton.setText("Add");
        ATF_AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATF_AddButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabAddToFlightLayout = new javax.swing.GroupLayout(tabAddToFlight);
        tabAddToFlight.setLayout(tabAddToFlightLayout);
        tabAddToFlightLayout.setHorizontalGroup(
            tabAddToFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddToFlightLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(tabAddToFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addGap(79, 79, 79)
                .addGroup(tabAddToFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ATF_FlightComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ATF_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(860, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabAddToFlightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ATF_AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(509, 509, 509))
        );
        tabAddToFlightLayout.setVerticalGroup(
            tabAddToFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddToFlightLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(tabAddToFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabAddToFlightLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel44))
                    .addComponent(ATF_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(tabAddToFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(ATF_FlightComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(ATF_AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );

        Tabs.addTab("Add to flight", tabAddToFlight);

        SMFlights_Table.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        SMFlights_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Departure Date", "Arrival Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(SMFlights_Table);

        SMFlights_RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        SMFlights_RefreshButton.setText("Refresh");
        SMFlights_RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SMFlights_RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabShowFlightsLayout = new javax.swing.GroupLayout(tabShowFlights);
        tabShowFlights.setLayout(tabShowFlightsLayout);
        tabShowFlightsLayout.setHorizontalGroup(
            tabShowFlightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabShowFlightsLayout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(322, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabShowFlightsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SMFlights_RefreshButton)
                .addGap(527, 527, 527))
        );
        tabShowFlightsLayout.setVerticalGroup(
            tabShowFlightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabShowFlightsLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(SMFlights_RefreshButton)
                .addContainerGap())
        );

        Tabs.addTab("Show my flights", tabShowFlights);

        SAPassengers_Table.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        SAPassengers_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Birthdate", "Age", "Phone", "Country", "Num Flight"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(SAPassengers_Table);

        SMPassengers_RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        SMPassengers_RefreshButton.setText("Refresh");
        SMPassengers_RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SMPassengers_RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabShowAllPassengersLayout = new javax.swing.GroupLayout(tabShowAllPassengers);
        tabShowAllPassengers.setLayout(tabShowAllPassengersLayout);
        tabShowAllPassengersLayout.setHorizontalGroup(
            tabShowAllPassengersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabShowAllPassengersLayout.createSequentialGroup()
                .addGroup(tabShowAllPassengersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabShowAllPassengersLayout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(SMPassengers_RefreshButton))
                    .addGroup(tabShowAllPassengersLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        tabShowAllPassengersLayout.setVerticalGroup(
            tabShowAllPassengersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabShowAllPassengersLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SMPassengers_RefreshButton)
                .addContainerGap())
        );

        Tabs.addTab("Show all passengers", tabShowAllPassengers);

        SAFlights_Table.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        SAFlights_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Departure Airport ID", "Arrival Airport ID", "Scale Airport ID", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(SAFlights_Table);

        SAFlights_RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        SAFlights_RefreshButton.setText("Refresh");
        SAFlights_RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAFlights_RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabShowAllFlightsLayout = new javax.swing.GroupLayout(tabShowAllFlights);
        tabShowAllFlights.setLayout(tabShowAllFlightsLayout);
        tabShowAllFlightsLayout.setHorizontalGroup(
            tabShowAllFlightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabShowAllFlightsLayout.createSequentialGroup()
                .addGroup(tabShowAllFlightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabShowAllFlightsLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabShowAllFlightsLayout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(SAFlights_RefreshButton)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        tabShowAllFlightsLayout.setVerticalGroup(
            tabShowAllFlightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabShowAllFlightsLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SAFlights_RefreshButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        Tabs.addTab("Show all flights", tabShowAllFlights);

        SAPlanes_RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        SAPlanes_RefreshButton.setText("Refresh");
        SAPlanes_RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAPlanes_RefreshButtonActionPerformed(evt);
            }
        });

        SAPlanes_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Brand", "Model", "Max Capacity", "Airline", "Number Flights"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(SAPlanes_Table);

        javax.swing.GroupLayout tabShowAllPlanesLayout = new javax.swing.GroupLayout(tabShowAllPlanes);
        tabShowAllPlanes.setLayout(tabShowAllPlanesLayout);
        tabShowAllPlanesLayout.setHorizontalGroup(
            tabShowAllPlanesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabShowAllPlanesLayout.createSequentialGroup()
                .addGroup(tabShowAllPlanesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabShowAllPlanesLayout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(SAPlanes_RefreshButton))
                    .addGroup(tabShowAllPlanesLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(220, Short.MAX_VALUE))
        );
        tabShowAllPlanesLayout.setVerticalGroup(
            tabShowAllPlanesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabShowAllPlanesLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(SAPlanes_RefreshButton)
                .addGap(17, 17, 17))
        );

        Tabs.addTab("Show all planes", tabShowAllPlanes);

        SALocations_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Airport ID", "Airport Name", "City", "Country"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(SALocations_Table);

        SALocations_RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        SALocations_RefreshButton.setText("Refresh");
        SALocations_RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALocations_RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabShowAllLocationsLayout = new javax.swing.GroupLayout(tabShowAllLocations);
        tabShowAllLocations.setLayout(tabShowAllLocationsLayout);
        tabShowAllLocationsLayout.setHorizontalGroup(
            tabShowAllLocationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabShowAllLocationsLayout.createSequentialGroup()
                .addGroup(tabShowAllLocationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabShowAllLocationsLayout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(SALocations_RefreshButton))
                    .addGroup(tabShowAllLocationsLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(303, Short.MAX_VALUE))
        );
        tabShowAllLocationsLayout.setVerticalGroup(
            tabShowAllLocationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabShowAllLocationsLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(SALocations_RefreshButton)
                .addGap(17, 17, 17))
        );

        Tabs.addTab("Show all locations", tabShowAllLocations);

        DF_HourComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DF_HourComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel46.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel46.setText("Hours:");

        jLabel47.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel47.setText("ID:");

        DF_IdComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DF_IdComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID" }));

        jLabel48.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel48.setText("Minutes:");

        DF_MinuteComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DF_MinuteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        DF_DelayButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DF_DelayButton.setText("Delay");
        DF_DelayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DF_DelayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabDelayFlightLayout = new javax.swing.GroupLayout(tabDelayFlight);
        tabDelayFlight.setLayout(tabDelayFlightLayout);
        tabDelayFlightLayout.setHorizontalGroup(
            tabDelayFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDelayFlightLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(tabDelayFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabDelayFlightLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DF_MinuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabDelayFlightLayout.createSequentialGroup()
                        .addGroup(tabDelayFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel46))
                        .addGap(79, 79, 79)
                        .addGroup(tabDelayFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DF_HourComboBox, 0, 136, Short.MAX_VALUE)
                            .addComponent(DF_IdComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(820, 820, 820))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabDelayFlightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DF_DelayButton)
                .addGap(531, 531, 531))
        );
        tabDelayFlightLayout.setVerticalGroup(
            tabDelayFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDelayFlightLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(tabDelayFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(DF_IdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(tabDelayFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(DF_HourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(tabDelayFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(DF_MinuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addComponent(DF_DelayButton)
                .addGap(33, 33, 33))
        );

        Tabs.addTab("Delay flight", tabDelayFlight);

        panelRound1.add(Tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 41, 1150, 620));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        panelRound1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 660, 1150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void panelRound2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panelRound2MousePressed

    private void panelRound2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_panelRound2MouseDragged

    private void administratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administratorActionPerformed
        if (user.isSelected()) {
            user.setSelected(false);
            userSelect.setSelectedIndex(0);

        }
        for (int i = 1; i < Tabs.getTabCount(); i++) {
            Tabs.setEnabledAt(i, true);
        }
        Tabs.setEnabledAt(5, false);
        Tabs.setEnabledAt(6, false);
        Tabs.setEnabledAt(7, false);
    }//GEN-LAST:event_administratorActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        if (administrator.isSelected()) {
            administrator.setSelected(false);
        }
        for (int i = 1; i < Tabs.getTabCount(); i++) {

            Tabs.setEnabledAt(i, false);

        }
        Tabs.setEnabledAt(9, true);
        Tabs.setEnabledAt(5, true);
        Tabs.setEnabledAt(6, true);
        Tabs.setEnabledAt(7, true);
        Tabs.setEnabledAt(11, true);
    }//GEN-LAST:event_userActionPerformed

    private void PR_RegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PR_RegisterButtonActionPerformed
        // TODO add your handling code here:
        String id = PR_IDTextField.getText();
        String firstname = PR_FirstNameTextField.getText();
        String lastname = PR_LastNameTextField.getText();
        String year = PR_YearTextField.getText();
        String month = PR_MonthComboBox.getItemAt(PR_MonthComboBox.getSelectedIndex());
        String day = PR_DayComboBox.getItemAt(PR_DayComboBox.getSelectedIndex());
        String phoneCode = PR_codigoPaisTextField.getText();
        String phone = PR_NumeroTelefonicoTextField.getText();
        String country = PR_PaisTextField.getText();

        Response response = PassengerController.PassengerRegistration(id, firstname, lastname, year, month, day, phoneCode, phone, country);

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            userSelect.removeAllItems();
            userSelect.addItem("Select User");
            this.generatePassengers();

            PR_IDTextField.setText("");
            PR_FirstNameTextField.setText("");
            PR_LastNameTextField.setText("");
            PR_YearTextField.setText("");
            PR_codigoPaisTextField.setText("");
            PR_NumeroTelefonicoTextField.setText("");
            PR_PaisTextField.setText("");
            PR_MonthComboBox.setSelectedIndex(0);
            PR_DayComboBox.setSelectedIndex(0);

        }
    }//GEN-LAST:event_PR_RegisterButtonActionPerformed

    private void AR_CreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AR_CreateButtonActionPerformed
        // TODO add your handling code here:
        String id = AR_IdTextField.getText();
        String brand = AR_BrandTextField.getText();
        String model = AR_ModelTextField.getText();
        String maxCapacity = AR_MaxCapacityTextField.getText();
        String airline = AR_AirlineTextField.getText();

        Response response = PlaneController.planeRegistration(id, brand, model, maxCapacity, airline);
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            FR_PlaneComboBox.removeAllItems();
            FR_PlaneComboBox.addItem("Plane");
            this.generatePlanes();

            AR_IdTextField.setText("");
            AR_BrandTextField.setText("");
            AR_ModelTextField.setText("");
            AR_MaxCapacityTextField.setText("");
            AR_AirlineTextField.setText("");

        }
    }//GEN-LAST:event_AR_CreateButtonActionPerformed

    private void LR_CreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LR_CreateButtonActionPerformed
        // TODO add your handling code here:
        String id = LR_AirportIdTextField.getText();
        String name = LR_AirportNameTextField.getText();
        String city = LR_AirportCityTextField.getText();
        String country = LR_AirportCountryTextField.getText();
        String latitude = LR_AirportLatitudeTextField.getText();
        String longitude = LR_AirportLongitudeTextField.getText();

        Response response = LocationController.LocationRegistration(id, name, city, country, latitude, longitude);

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            FR_DepartureLocationComboBox.removeAllItems();
            FR_ArrivalLocationComboBox.removeAllItems();
            FR_ScaleLocationComboBox.removeAllItems();
            FR_DepartureLocationComboBox.addItem("Location");
            FR_ArrivalLocationComboBox.addItem("Location");
            FR_ScaleLocationComboBox.addItem("Location");
            this.generateLocations();

            LR_AirportIdTextField.setText("");
            LR_AirportNameTextField.setText("");
            LR_AirportCityTextField.setText("");
            LR_AirportCountryTextField.setText("");
            LR_AirportLatitudeTextField.setText("");
            LR_AirportLongitudeTextField.setText("");

        }
    }//GEN-LAST:event_LR_CreateButtonActionPerformed

    private void FR_CreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FR_CreateButtonActionPerformed
        // TODO add your handling code here:
        String id = FR_IdTextField.getText();
        String planeId = FR_PlaneComboBox.getItemAt(FR_PlaneComboBox.getSelectedIndex());
        String departureLocationId = FR_DepartureLocationComboBox.getItemAt(FR_DepartureLocationComboBox.getSelectedIndex());
        String arrivalLocationId = FR_ArrivalLocationComboBox.getItemAt(FR_ArrivalLocationComboBox.getSelectedIndex());
        String scaleLocationId = FR_ScaleLocationComboBox.getItemAt(FR_ScaleLocationComboBox.getSelectedIndex());
        String year = FR_YearTextField.getText();
        String month = FR_MonthComboBox.getItemAt(FR_MonthComboBox.getSelectedIndex());
        String day = FR_DayComboBox.getItemAt(FR_DayComboBox.getSelectedIndex());
        String hour = FR_DDHourComboBox.getItemAt(FR_DDHourComboBox.getSelectedIndex());
        String minutes = FR_DDMinuteComboBox.getItemAt(FR_DDMinuteComboBox.getSelectedIndex());
        String hoursDurationsArrival = FR_D1HourComboBox.getItemAt(FR_D1HourComboBox.getSelectedIndex());
        String minutesDurationsArrival = FR_D1MinuteComboBox.getItemAt(FR_D1MinuteComboBox.getSelectedIndex());
        String hoursDurationsScale = FR_D2HourComboBox.getItemAt(FR_D2HourComboBox.getSelectedIndex());
        String minutesDurationsScale = FR_D2MinuteComboBox.getItemAt(FR_D2MinuteComboBox.getSelectedIndex());

        Response response = FlightController.FlightRegistration(id, planeId, departureLocationId, arrivalLocationId, scaleLocationId, year, month, day, hour, minutes, hoursDurationsArrival, minutesDurationsArrival, hoursDurationsScale, minutesDurationsScale);
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            DF_IdComboBox.removeAllItems();
            ATF_FlightComboBox.removeAllItems();
            DF_IdComboBox.addItem("ID");
            ATF_FlightComboBox.addItem("Flight");
            this.generateFlights();

            FR_IdTextField.setText("");
            FR_PlaneComboBox.setSelectedIndex(0);
            FR_DepartureLocationComboBox.setSelectedIndex(0);
            FR_ArrivalLocationComboBox.setSelectedIndex(0);
            FR_ScaleLocationComboBox.setSelectedIndex(0);
            FR_YearTextField.setText("");
            FR_MonthComboBox.setSelectedIndex(0);
            FR_DayComboBox.setSelectedIndex(0);
            FR_DDHourComboBox.setSelectedIndex(0);
            FR_DDMinuteComboBox.setSelectedIndex(0);
            FR_D1HourComboBox.setSelectedIndex(0);
            FR_D1MinuteComboBox.setSelectedIndex(0);
            FR_D2HourComboBox.setSelectedIndex(0);
            FR_D2MinuteComboBox.setSelectedIndex(0);

        }

    }//GEN-LAST:event_FR_CreateButtonActionPerformed

    private void UF_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UF_UpdateActionPerformed
        // TODO add your handling code here:
        String id = userSelect.getItemAt(userSelect.getSelectedIndex());
        String firstname = UI_FirstNameTextField.getText();
        String lastname = UI_LastNameTextField.getText();
        String year = UI_BirthdayTextField.getText();
        String month = PR_MonthComboBox.getItemAt(UI_MonthComboBox.getSelectedIndex());
        String day = PR_DayComboBox.getItemAt(UI_DayComboBox.getSelectedIndex());
        String phoneCode = UI_codigoPaisTextField.getText();
        String phone = UI_NumeroTelefonicoTextField.getText();
        String country = UI_PaisTextField.getText();

        Response response = PassengerController.updatePassenger(id, firstname, lastname, year, month, day, phoneCode, phone, country);

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);

            UI_FirstNameTextField.setText("");
            UI_LastNameTextField.setText("");
            UI_BirthdayTextField.setText("");
            UI_codigoPaisTextField.setText("");
            UI_NumeroTelefonicoTextField.setText("");
            UI_PaisTextField.setText("");
            UI_MonthComboBox.setSelectedIndex(0);
            UI_DayComboBox.setSelectedIndex(0);

        }

    }//GEN-LAST:event_UF_UpdateActionPerformed

    private void ATF_AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATF_AddButtonActionPerformed
        // TODO add your handling code here:
        String passengerId = userSelect.getItemAt(userSelect.getSelectedIndex());
        String flightId = ATF_FlightComboBox.getItemAt(ATF_FlightComboBox.getSelectedIndex());

        Response response = PassengerController.addFlight(passengerId, flightId);

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            ATF_FlightComboBox.setSelectedIndex(0);
        }

    }//GEN-LAST:event_ATF_AddButtonActionPerformed

    private void DF_DelayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF_DelayButtonActionPerformed
        // TODO add your handling code here:
        String flightId = DF_IdComboBox.getItemAt(DF_IdComboBox.getSelectedIndex());
        String hours = DF_HourComboBox.getItemAt(DF_HourComboBox.getSelectedIndex());
        String minutes = DF_MinuteComboBox.getItemAt(DF_MinuteComboBox.getSelectedIndex());

        Response response = FlightController.delayFlight(flightId, hours, minutes);
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            DF_IdComboBox.setSelectedIndex(0);
            DF_HourComboBox.setSelectedIndex(0);
            DF_MinuteComboBox.setSelectedIndex(0);
            
            DF_IdComboBox.removeAllItems();
            ATF_FlightComboBox.removeAllItems();
            DF_IdComboBox.addItem("ID");
            ATF_FlightComboBox.addItem("Flight");
            this.generateFlights();           
        }
    }//GEN-LAST:event_DF_DelayButtonActionPerformed

    private void SMFlights_RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SMFlights_RefreshButtonActionPerformed
        // TODO add your handling code here:
        try {
            String id = userSelect.getSelectedItem().toString();
            this.showPassengerFlights(id);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_SMFlights_RefreshButtonActionPerformed

    private void SMPassengers_RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SMPassengers_RefreshButtonActionPerformed
        // TODO add your handling code here:
        this.showAllPassengers();
    }//GEN-LAST:event_SMPassengers_RefreshButtonActionPerformed

    private void SAFlights_RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAFlights_RefreshButtonActionPerformed
        // TODO add your handling code here:
        this.showAllFlights();
    }//GEN-LAST:event_SAFlights_RefreshButtonActionPerformed

    private void SAPlanes_RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAPlanes_RefreshButtonActionPerformed
        // TODO add your handling code here:
        this.showAllPlanes();
    }//GEN-LAST:event_SAPlanes_RefreshButtonActionPerformed

    private void SALocations_RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALocations_RefreshButtonActionPerformed
        // TODO add your handling code here:
        this.showAllLocations();
    }//GEN-LAST:event_SALocations_RefreshButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void userSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userSelectActionPerformed
        try {
            String id = userSelect.getSelectedItem().toString();
            if (!id.equals(userSelect.getItemAt(0))) {
                UI_IdTextField.setText(id);
                ATF_IdTextField.setText(id);
            } else {
                UI_IdTextField.setText("");
                ATF_IdTextField.setText("");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_userSelectActionPerformed

    private void PR_MonthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PR_MonthComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PR_MonthComboBoxActionPerformed

    private void FR_PlaneComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FR_PlaneComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FR_PlaneComboBoxActionPerformed

    private void FR_DepartureLocationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FR_DepartureLocationComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FR_DepartureLocationComboBoxActionPerformed

    private void FR_ScaleLocationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FR_ScaleLocationComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FR_ScaleLocationComboBoxActionPerformed

    private void FR_ArrivalLocationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FR_ArrivalLocationComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FR_ArrivalLocationComboBoxActionPerformed

    private void FR_DepartureLocationComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FR_DepartureLocationComboBoxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FR_DepartureLocationComboBoxMouseClicked

    private void UI_IdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UI_IdTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UI_IdTextFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AR_AirlineTextField;
    private javax.swing.JTextField AR_BrandTextField;
    private javax.swing.JButton AR_CreateButton;
    private javax.swing.JTextField AR_IdTextField;
    private javax.swing.JTextField AR_MaxCapacityTextField;
    private javax.swing.JTextField AR_ModelTextField;
    private javax.swing.JButton ATF_AddButton;
    private javax.swing.JComboBox<String> ATF_FlightComboBox;
    private javax.swing.JTextField ATF_IdTextField;
    private javax.swing.JButton DF_DelayButton;
    private javax.swing.JComboBox<String> DF_HourComboBox;
    private javax.swing.JComboBox<String> DF_IdComboBox;
    private javax.swing.JComboBox<String> DF_MinuteComboBox;
    private javax.swing.JComboBox<String> FR_ArrivalLocationComboBox;
    private javax.swing.JButton FR_CreateButton;
    private javax.swing.JComboBox<String> FR_D1HourComboBox;
    private javax.swing.JComboBox<String> FR_D1MinuteComboBox;
    private javax.swing.JComboBox<String> FR_D2HourComboBox;
    private javax.swing.JComboBox<String> FR_D2MinuteComboBox;
    private javax.swing.JComboBox<String> FR_DDHourComboBox;
    private javax.swing.JComboBox<String> FR_DDMinuteComboBox;
    private javax.swing.JComboBox<String> FR_DayComboBox;
    private javax.swing.JComboBox<String> FR_DepartureLocationComboBox;
    private javax.swing.JTextField FR_IdTextField;
    private javax.swing.JComboBox<String> FR_MonthComboBox;
    private javax.swing.JComboBox<String> FR_PlaneComboBox;
    private javax.swing.JComboBox<String> FR_ScaleLocationComboBox;
    private javax.swing.JTextField FR_YearTextField;
    private javax.swing.JTextField LR_AirportCityTextField;
    private javax.swing.JTextField LR_AirportCountryTextField;
    private javax.swing.JTextField LR_AirportIdTextField;
    private javax.swing.JTextField LR_AirportLatitudeTextField;
    private javax.swing.JTextField LR_AirportLongitudeTextField;
    private javax.swing.JTextField LR_AirportNameTextField;
    private javax.swing.JButton LR_CreateButton;
    private javax.swing.JComboBox<String> PR_DayComboBox;
    private javax.swing.JTextField PR_FirstNameTextField;
    private javax.swing.JTextField PR_IDTextField;
    private javax.swing.JTextField PR_LastNameTextField;
    private javax.swing.JComboBox<String> PR_MonthComboBox;
    private javax.swing.JTextField PR_NumeroTelefonicoTextField;
    private javax.swing.JTextField PR_PaisTextField;
    private javax.swing.JButton PR_RegisterButton;
    private javax.swing.JTextField PR_YearTextField;
    private javax.swing.JTextField PR_codigoPaisTextField;
    private javax.swing.JButton SAFlights_RefreshButton;
    private javax.swing.JTable SAFlights_Table;
    private javax.swing.JButton SALocations_RefreshButton;
    private javax.swing.JTable SALocations_Table;
    private javax.swing.JTable SAPassengers_Table;
    private javax.swing.JButton SAPlanes_RefreshButton;
    private javax.swing.JTable SAPlanes_Table;
    private javax.swing.JButton SMFlights_RefreshButton;
    private javax.swing.JTable SMFlights_Table;
    private javax.swing.JButton SMPassengers_RefreshButton;
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JButton UF_Update;
    private javax.swing.JTextField UI_BirthdayTextField;
    private javax.swing.JComboBox<String> UI_DayComboBox;
    private javax.swing.JTextField UI_FirstNameTextField;
    private javax.swing.JTextField UI_IdTextField;
    private javax.swing.JTextField UI_LastNameTextField;
    private javax.swing.JComboBox<String> UI_MonthComboBox;
    private javax.swing.JTextField UI_NumeroTelefonicoTextField;
    private javax.swing.JTextField UI_PaisTextField;
    private javax.swing.JTextField UI_codigoPaisTextField;
    private javax.swing.JRadioButton administrator;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblCountry;
    private core.views.PanelRound panelRound1;
    private core.views.PanelRound panelRound2;
    private core.views.PanelRound panelRound3;
    private javax.swing.JPanel tabAddToFlight;
    private javax.swing.JPanel tabAdministration;
    private javax.swing.JPanel tabAirplaneRegistration;
    private javax.swing.JPanel tabDelayFlight;
    private javax.swing.JPanel tabFlightRegistration;
    private javax.swing.JPanel tabLocationRegistration;
    private javax.swing.JPanel tabPassengerRegistration;
    private javax.swing.JPanel tabShowAllFlights;
    private javax.swing.JPanel tabShowAllLocations;
    private javax.swing.JPanel tabShowAllPassengers;
    private javax.swing.JPanel tabShowAllPlanes;
    private javax.swing.JPanel tabShowFlights;
    private javax.swing.JPanel tabUpdateInfo;
    private javax.swing.JRadioButton user;
    private javax.swing.JComboBox<String> userSelect;
    // End of variables declaration//GEN-END:variables
}
