/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  boundery.FlightManagmentFrm
 */
package control;

import boundery.FlightManagmentFrm;
import entity.AirPlane;
import entity.AirPort;
import exceptions.InvalidInputException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import util.Consts;

public class FlightsLogic {
    private static FlightsLogic _instance;

    private FlightsLogic() {
    }

    public static FlightsLogic getInstance() {
        if (_instance == null) {
            _instance = new FlightsLogic();
        }
        return _instance;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean isPlaneOverlapping(AirPlane airplane, LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<String> results;
        block24: {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String depatureDateStr = sdf.format(Timestamp.valueOf(startDate));
            String landingDateStr = sdf.format(Timestamp.valueOf(endDate));
            String query = "SELECT SerialNum FROM FlightTbl WHERE (((FlightTbl.AirPlaneTailNum)='" + airplane.getTailNum() + "') AND ((DateValue(FlightTbl.DepatureTime))<=#" + landingDateStr + "#) AND ((DateValue(FlightTbl.LandingTime))>=#" + depatureDateStr + "#))";
            results = new ArrayList<String>();
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                try {
                    Throwable throwable = null;
                    Object var10_13 = null;
                    try {
                        Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                        try {
                            block23: {
                                PreparedStatement stmt = conn.prepareStatement(query);
                                try {
                                    try (ResultSet rs = stmt.executeQuery();){
                                        while (rs.next()) {
                                            int i = 1;
                                            String flightID = rs.getString(i++);
                                            if (FlightManagmentFrm.getCurrentFlight() != null && FlightManagmentFrm.getCurrentFlight().getFlightNum().equals(flightID)) continue;
                                            results.add(flightID);
                                        }
                                    }
                                    if (stmt == null) break block23;
                                }
                                catch (Throwable throwable2) {
                                    if (throwable == null) {
                                        throwable = throwable2;
                                    } else if (throwable != throwable2) {
                                        throwable.addSuppressed(throwable2);
                                    }
                                    if (stmt == null) throw throwable;
                                    stmt.close();
                                    throw throwable;
                                }
                                stmt.close();
                            }
                            if (conn == null) break block24;
                        }
                        catch (Throwable throwable3) {
                            if (throwable == null) {
                                throwable = throwable3;
                            } else if (throwable != throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                            if (conn == null) throw throwable;
                            conn.close();
                            throw throwable;
                        }
                        conn.close();
                    }
                    catch (Throwable throwable4) {
                        if (throwable == null) {
                            throwable = throwable4;
                            throw throwable;
                        }
                        if (throwable == throwable4) throw throwable;
                        throwable.addSuppressed(throwable4);
                        throw throwable;
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!results.isEmpty()) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean isAirportsOverlapping(AirPort airport, LocalDateTime dateTime, boolean isDeparture) {
        ArrayList<String> results;
        block26: {
            String timeType;
            String airportType;
            if (isDeparture) {
                airportType = "FlightTbl.DepatureAirportID";
                timeType = "FlightTbl.DepatureTime";
            } else {
                airportType = "FlightTbl.DestinationAirportID";
                timeType = "FlightTbl.LandingTime";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy KK:mm:ss a", Locale.ENGLISH);
            String timeStampPlusHalfHour = sdf.format(Timestamp.valueOf(dateTime.plusMinutes(30L)));
            String timeStampMinusHalfHour = sdf.format(Timestamp.valueOf(dateTime.minusMinutes(30L)));
            String query = "SELECT FlightTbl.SerialNum FROM FlightTbl WHERE (((" + airportType + ")=" + airport.getAirportCode() + ") " + "AND ((" + timeType + ")>=#" + timeStampMinusHalfHour + "#) " + "AND ((" + timeType + ")<=#" + timeStampPlusHalfHour + "#));";
            results = new ArrayList<String>();
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                try {
                    Throwable throwable = null;
                    Object var12_15 = null;
                    try {
                        Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                        try {
                            block25: {
                                PreparedStatement stmt = conn.prepareStatement(query);
                                try {
                                    try (ResultSet rs = stmt.executeQuery();){
                                        while (rs.next()) {
                                            int i = 1;
                                            String flightID = rs.getString(i++);
                                            if (FlightManagmentFrm.getCurrentFlight() != null && FlightManagmentFrm.getCurrentFlight().getFlightNum().equals(flightID)) continue;
                                            results.add(flightID);
                                        }
                                    }
                                    if (stmt == null) break block25;
                                }
                                catch (Throwable throwable2) {
                                    if (throwable == null) {
                                        throwable = throwable2;
                                    } else if (throwable != throwable2) {
                                        throwable.addSuppressed(throwable2);
                                    }
                                    if (stmt == null) throw throwable;
                                    stmt.close();
                                    throw throwable;
                                }
                                stmt.close();
                            }
                            if (conn == null) break block26;
                        }
                        catch (Throwable throwable3) {
                            if (throwable == null) {
                                throwable = throwable3;
                            } else if (throwable != throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                            if (conn == null) throw throwable;
                            conn.close();
                            throw throwable;
                        }
                        conn.close();
                    }
                    catch (Throwable throwable4) {
                        if (throwable == null) {
                            throwable = throwable4;
                            throw throwable;
                        }
                        if (throwable == throwable4) throw throwable;
                        throwable.addSuppressed(throwable4);
                        throw throwable;
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!results.isEmpty()) return false;
        return true;
    }

    /*
     * Loose catch block
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean addFlight(String flightNum, LocalDateTime depatureTime, LocalDateTime landingTime, AirPort depatureAirport, AirPort destinationAirport, AirPlane airplane, String cheifPilotID, String coPilotID, String flightStatus) throws InvalidInputException {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block25: {
            if (!this.isAirportsOverlapping(depatureAirport, depatureTime, true)) throw new InvalidInputException("Please select a different Departue airport - flights collison");
            if (!this.isAirportsOverlapping(destinationAirport, landingTime, false)) throw new InvalidInputException("Please select a different Landing airport - flights collison");
            if (!this.isPlaneOverlapping(airplane, depatureTime, landingTime)) throw new InvalidInputException("Airplane is already taken by another flight");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            throwable = null;
            Object var11_14 = null;
            conn = DriverManager.getConnection(Consts.CONN_STR);
            stmt = conn.prepareCall("{ call qryInsFlight(?,?,?,?,?,?,?,?,?,?) }");
            int i = 1;
            Timestamp depatureTimeStamp = Timestamp.valueOf(depatureTime);
            Timestamp landingTimeStamp = Timestamp.valueOf(landingTime);
            stmt.setString(i++, flightNum);
            stmt.setTimestamp(i++, depatureTimeStamp);
            stmt.setTimestamp(i++, landingTimeStamp);
            stmt.setInt(i++, depatureAirport.getAirportCode());
            stmt.setInt(i++, destinationAirport.getAirportCode());
            stmt.setString(i++, airplane.getTailNum());
            if (cheifPilotID != null) {
                stmt.setString(i++, cheifPilotID);
            } else {
                stmt.setNull(i++, 12);
            }
            if (coPilotID != null) {
                stmt.setString(i++, coPilotID);
            } else {
                stmt.setNull(i++, 12);
            }
            if (flightStatus != null) {
                stmt.setString(i++, flightStatus);
            } else {
                stmt.setNull(i++, 12);
            }
            stmt.setString(i++, "Init");
            stmt.executeUpdate();
            if (stmt == null) break block25;
            stmt.close();
        }
        if (conn == null) return true;
        conn.close();
        return true;
        {
            catch (Throwable throwable2) {
                try {
                    try {
                        try {
                            try {
                                if (stmt == null) throw throwable2;
                                stmt.close();
                                throw throwable2;
                            }
                            catch (Throwable throwable3) {
                                if (throwable == null) {
                                    throwable = throwable3;
                                } else if (throwable != throwable3) {
                                    throwable.addSuppressed(throwable3);
                                }
                                if (conn == null) throw throwable;
                                conn.close();
                                throw throwable;
                            }
                        }
                        catch (Throwable throwable4) {
                            if (throwable == null) {
                                throwable = throwable4;
                                throw throwable;
                            } else {
                                if (throwable == throwable4) throw throwable;
                                throwable.addSuppressed(throwable4);
                            }
                            throw throwable;
                        }
                    }
                    catch (SQLException sQLException) {
                        return false;
                    }
                }
                catch (ClassNotFoundException classNotFoundException) {}
            }
        }
        return false;
    }

    /*
     * Loose catch block
     */
    public boolean addAirPort(int id, String city, String country, int GMT) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var6_9 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("{ call qryInsAirPort(?,?,?,?,?) }");
                int i = 1;
                stmt.setInt(i++, id);
                stmt.setString(i++, city);
                stmt.setString(i++, country);
                stmt.setInt(i++, GMT);
                stmt.setBoolean(i++, true);
                stmt.executeUpdate();
                if (stmt == null) break block22;
                stmt.close();
            }
            if (conn == null) break block23;
            conn.close();
        }
        return true;
        {
            catch (Throwable throwable2) {
                try {
                    try {
                        try {
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                                throw throwable2;
                            }
                            catch (Throwable throwable3) {
                                if (throwable == null) {
                                    throwable = throwable3;
                                } else if (throwable != throwable3) {
                                    throwable.addSuppressed(throwable3);
                                }
                                if (conn != null) {
                                    conn.close();
                                }
                                throw throwable;
                            }
                        }
                        catch (Throwable throwable4) {
                            if (throwable == null) {
                                throwable = throwable4;
                            } else if (throwable != throwable4) {
                                throwable.addSuppressed(throwable4);
                            }
                            throw throwable;
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /*
     * Loose catch block
     */
    public boolean addAirPlane(String tailNum, int attendantsNum) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var4_7 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("{ call qryInsAirPlane(?,?) }");
                int i = 1;
                stmt.setString(i++, tailNum);
                stmt.setInt(i++, attendantsNum);
                stmt.executeUpdate();
                if (stmt == null) break block22;
                stmt.close();
            }
            if (conn == null) break block23;
            conn.close();
        }
        return true;
        {
            catch (Throwable throwable2) {
                try {
                    try {
                        try {
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                                throw throwable2;
                            }
                            catch (Throwable throwable3) {
                                if (throwable == null) {
                                    throwable = throwable3;
                                } else if (throwable != throwable3) {
                                    throwable.addSuppressed(throwable3);
                                }
                                if (conn != null) {
                                    conn.close();
                                }
                                throw throwable;
                            }
                        }
                        catch (Throwable throwable4) {
                            if (throwable == null) {
                                throwable = throwable4;
                            } else if (throwable != throwable4) {
                                throwable.addSuppressed(throwable4);
                            }
                            throw throwable;
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /*
     * Loose catch block
     */
    public boolean addFlightSeat(int id, int row, String col, String type, String tailNum) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var7_10 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("{ call qryInsFlightSeat(?,?,?,?,?) }");
                int i = 1;
                stmt.setInt(i++, id);
                stmt.setInt(i++, row);
                stmt.setString(i++, col);
                stmt.setString(i++, type);
                stmt.setString(i++, tailNum);
                stmt.executeUpdate();
                if (stmt == null) break block22;
                stmt.close();
            }
            if (conn == null) break block23;
            conn.close();
        }
        return true;
        {
            catch (Throwable throwable2) {
                try {
                    try {
                        try {
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                                throw throwable2;
                            }
                            catch (Throwable throwable3) {
                                if (throwable == null) {
                                    throwable = throwable3;
                                } else if (throwable != throwable3) {
                                    throwable.addSuppressed(throwable3);
                                }
                                if (conn != null) {
                                    conn.close();
                                }
                                throw throwable;
                            }
                        }
                        catch (Throwable throwable4) {
                            if (throwable == null) {
                                throwable = throwable4;
                            } else if (throwable != throwable4) {
                                throwable.addSuppressed(throwable4);
                            }
                            throw throwable;
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /*
     * Loose catch block
     */
    public boolean editFlight(String flightNum, LocalDateTime depatureTime, LocalDateTime landingTime, String airPlaneTailNum) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var6_9 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("{ call qryUpdFlight(?,?,?,?) }");
                int i = 1;
                Timestamp depatureTimeStamp = Timestamp.valueOf(depatureTime);
                Timestamp landingTimeStamp = Timestamp.valueOf(landingTime);
                stmt.setTimestamp(i++, depatureTimeStamp);
                stmt.setTimestamp(i++, landingTimeStamp);
                stmt.setString(i++, airPlaneTailNum);
                stmt.setString(i++, flightNum);
                stmt.executeUpdate();
                if (stmt == null) break block22;
                stmt.close();
            }
            if (conn == null) break block23;
            conn.close();
        }
        return true;
        {
            catch (Throwable throwable2) {
                try {
                    try {
                        try {
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                                throw throwable2;
                            }
                            catch (Throwable throwable3) {
                                if (throwable == null) {
                                    throwable = throwable3;
                                } else if (throwable != throwable3) {
                                    throwable.addSuppressed(throwable3);
                                }
                                if (conn != null) {
                                    conn.close();
                                }
                                throw throwable;
                            }
                        }
                        catch (Throwable throwable4) {
                            if (throwable == null) {
                                throwable = throwable4;
                            } else if (throwable != throwable4) {
                                throwable.addSuppressed(throwable4);
                            }
                            throw throwable;
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /*
     * Loose catch block
     */
    public boolean editAirPortStatus(boolean isOpen, int airPortId) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var4_7 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("{ call qryUpdAirPortStatus(?,?) }");
                int i = 1;
                stmt.setBoolean(i++, isOpen);
                stmt.setInt(i++, airPortId);
                stmt.executeUpdate();
                if (stmt == null) break block22;
                stmt.close();
            }
            if (conn == null) break block23;
            conn.close();
        }
        return true;
        {
            catch (Throwable throwable2) {
                try {
                    try {
                        try {
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                                throw throwable2;
                            }
                            catch (Throwable throwable3) {
                                if (throwable == null) {
                                    throwable = throwable3;
                                } else if (throwable != throwable3) {
                                    throwable.addSuppressed(throwable3);
                                }
                                if (conn != null) {
                                    conn.close();
                                }
                                throw throwable;
                            }
                        }
                        catch (Throwable throwable4) {
                            if (throwable == null) {
                                throwable = throwable4;
                            } else if (throwable != throwable4) {
                                throwable.addSuppressed(throwable4);
                            }
                            throw throwable;
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /*
     * Loose catch block
     */
    public boolean editFlightStatus(String status, int airportCode) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var4_7 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("{ call qryUpdFlightStatus(?,?,?) }");
                int i = 1;
                stmt.setString(i++, status);
                stmt.setInt(i++, airportCode);
                stmt.setInt(i++, airportCode);
                stmt.executeUpdate();
                if (stmt == null) break block22;
                stmt.close();
            }
            if (conn == null) break block23;
            conn.close();
        }
        return true;
        {
            catch (Throwable throwable2) {
                try {
                    try {
                        try {
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                                throw throwable2;
                            }
                            catch (Throwable throwable3) {
                                if (throwable == null) {
                                    throwable = throwable3;
                                } else if (throwable != throwable3) {
                                    throwable.addSuppressed(throwable3);
                                }
                                if (conn != null) {
                                    conn.close();
                                }
                                throw throwable;
                            }
                        }
                        catch (Throwable throwable4) {
                            if (throwable == null) {
                                throwable = throwable4;
                            } else if (throwable != throwable4) {
                                throwable.addSuppressed(throwable4);
                            }
                            throw throwable;
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
