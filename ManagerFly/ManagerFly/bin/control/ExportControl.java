/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.ButtonType
 *  org.json.simple.JsonArray
 *  org.json.simple.JsonObject
 *  org.json.simple.Jsoner
 */
package control;

import control.Getters;
import entity.Flight;
import entity.FlightSeat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

public class ExportControl {
    private static ExportControl instance;

    public static ExportControl getInstance() {
        if (instance == null) {
            instance = new ExportControl();
        }
        return instance;
    }

    public void exportFlightsToJSON() {
        try {
            Throwable seats;
            ArrayList<Flight> flights = Getters.getInstance().getFlights();
            JsonArray data = new JsonArray();
            int i = 0;
            while (i < flights.size()) {
                JsonObject flight = new JsonObject();
                flight.put((Object)"FlightID", (Object)flights.get(i).getFlightNum().toString());
                flight.put((Object)"DepartureAirportCode", (Object)flights.get(i).getDepatureAirportID().toString());
                flight.put((Object)"DepartureDateTime", (Object)flights.get(i).getDepatureTime().toString());
                flight.put((Object)"DestinationAirportCode", (Object)flights.get(i).getDestinationAirportID().toString());
                flight.put((Object)"DestinationDateTime", (Object)flights.get(i).getLandingTime().toString());
                flight.put((Object)"Status", (Object)flights.get(i).getFlightStatus());
                flight.put((Object)"AirplaneID", (Object)flights.get(i).getAirPlaneTailNum().toString());
                flight.put((Object)"DepartureCountry", (Object)flights.get(i).getDepatureAirportID().getCountry());
                flight.put((Object)"DepartureCity", (Object)flights.get(i).getDepatureAirportID().getCity());
                flight.put((Object)"DestinationCountry", (Object)flights.get(i).getDestinationAirportID().getCountry());
                flight.put((Object)"DestinationCity", (Object)flights.get(i).getDestinationAirportID().getCity());
                seats = new JsonArray();
                ArrayList<FlightSeat> seatList = Getters.getInstance().getFlightSeats();
                int j = 0;
                while (j < seatList.size()) {
                    JsonObject seat = new JsonObject();
                    seat.put((Object)"Row", (Object)seatList.get(j).getRowNum());
                    seat.put((Object)"Seat", (Object)seatList.get(j).getColNum());
                    seat.put((Object)"Class", (Object)seatList.get(j).getSeatType());
                    seats.add(seat);
                    ++j;
                }
                flight.put((Object)"Seats", (Object)seats);
                data.add((Object)flight);
                ++i;
            }
            JsonObject doc = new JsonObject();
            doc.put((Object)"flights", (Object)data);
            File file = new File("json/flights.json");
            file.getParentFile().mkdir();
            try {
                seats = null;
                Object var6_10 = null;
                try (FileWriter writer = new FileWriter(file);){
                    writer.write(Jsoner.prettyPrint((String)doc.toJson()));
                    writer.flush();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Flights data exported successfully!", new ButtonType[0]);
                    alert.setHeaderText("Success");
                    alert.setTitle("Success Data Export");
                    alert.showAndWait();
                }
                catch (Throwable throwable) {
                    if (seats == null) {
                        seats = throwable;
                    } else if (seats != throwable) {
                        seats.addSuppressed(throwable);
                    }
                    throw seats;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
