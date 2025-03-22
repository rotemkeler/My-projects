/*
 * Decompiled with CFR 0.152.
 */
package control;

import entity.AirAttendant;
import entity.AirPlane;
import entity.AirPort;
import entity.Flight;
import entity.FlightSeat;
import entity.GroundAttendant;
import entity.GroundAttendantInShift;
import entity.Pilot;
import entity.Shift;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.stream.Collectors;
import util.Consts;

public class Getters {
    private static Getters _instance;

    private Getters() {
    }

    public static Getters getInstance() {
        if (_instance == null) {
            _instance = new Getters();
        }
        return _instance;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<Flight> getFlights() {
        ArrayList<Flight> results = new ArrayList<Flight>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var3_6 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        block23: {
                            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FlightTbl");
                            try {
                                try (ResultSet rs = stmt.executeQuery();){
                                    while (rs.next()) {
                                        int i = 1;
                                        String flightID = rs.getString(i++);
                                        LocalDateTime depTime = rs.getTimestamp(i++).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                        LocalDateTime arrTime = rs.getTimestamp(i++).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                        results.add(new Flight(flightID, depTime, arrTime, rs.getString(9), new AirPort(rs.getInt(i++)), new AirPort(rs.getInt(i++)), new AirPlane(rs.getString(i++)), rs.getString(i++), rs.getString(i++), rs.getString(10)));
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
                        if (conn == null) return results;
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
                    return results;
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
                return results;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<AirPort> getAirports() {
        ArrayList<AirPort> results = new ArrayList<AirPort>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var3_6 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        block23: {
                            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM AirPortTbl");
                            try {
                                try (ResultSet rs = stmt.executeQuery();){
                                    while (rs.next()) {
                                        int i = 1;
                                        results.add(new AirPort(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getInt(i++), rs.getBoolean(i++)));
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
                        if (conn == null) return results;
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
                    return results;
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
                return results;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<AirPlane> getAirplanes() {
        ArrayList<AirPlane> results = new ArrayList<AirPlane>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var3_6 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        block23: {
                            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM AirPlaneTbl");
                            try {
                                try (ResultSet rs = stmt.executeQuery();){
                                    while (rs.next()) {
                                        int i = 1;
                                        results.add(new AirPlane(rs.getString(i++), rs.getInt(i++)));
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
                        if (conn == null) return results;
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
                    return results;
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
                return results;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<FlightSeat> getFlightSeats() {
        ArrayList<FlightSeat> results = new ArrayList<FlightSeat>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var3_6 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        block23: {
                            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FlightSeatTbl");
                            try {
                                try (ResultSet rs = stmt.executeQuery();){
                                    while (rs.next()) {
                                        int i = 1;
                                        results.add(new FlightSeat(rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getString(i++), new AirPlane(rs.getString(i++))));
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
                        if (conn == null) return results;
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
                    return results;
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
                return results;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<AirAttendant> getAirAttendants() {
        ArrayList<AirAttendant> results = new ArrayList<AirAttendant>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var3_6 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        block24: {
                            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM AirAttendantTbl");
                            try {
                                try (ResultSet rs = stmt.executeQuery();){
                                    while (rs.next()) {
                                        int i = 1;
                                        Date contractFinishDate = rs.getDate(5);
                                        LocalDate contractFinishLocalDate = null;
                                        if (contractFinishDate != null) {
                                            contractFinishLocalDate = contractFinishDate.toLocalDate();
                                        }
                                        results.add(new AirAttendant(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++).toLocalDate(), contractFinishLocalDate));
                                    }
                                }
                                if (stmt == null) break block24;
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
                        if (conn == null) return results;
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
                    return results;
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
                return results;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<GroundAttendant> getGroundAttendants() {
        ArrayList<GroundAttendant> results = new ArrayList<GroundAttendant>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var3_6 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        block24: {
                            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM GroundAttendantTbl");
                            try {
                                try (ResultSet rs = stmt.executeQuery();){
                                    while (rs.next()) {
                                        int i = 1;
                                        Date contractFinishDate = rs.getDate(5);
                                        LocalDate contractFinishLocalDate = null;
                                        if (contractFinishDate != null) {
                                            contractFinishLocalDate = contractFinishDate.toLocalDate();
                                        }
                                        results.add(new GroundAttendant(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++).toLocalDate(), contractFinishLocalDate));
                                    }
                                }
                                if (stmt == null) break block24;
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
                        if (conn == null) return results;
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
                    return results;
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
                return results;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<Pilot> getPilots() {
        ArrayList<Pilot> results = new ArrayList<Pilot>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var3_6 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        block24: {
                            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PilotTbl");
                            try {
                                try (ResultSet rs = stmt.executeQuery();){
                                    while (rs.next()) {
                                        int i = 1;
                                        Date contractFinishDate = rs.getDate(5);
                                        LocalDate contractFinishLocalDate = null;
                                        if (contractFinishDate != null) {
                                            contractFinishLocalDate = contractFinishDate.toLocalDate();
                                        }
                                        results.add(new Pilot(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++).toLocalDate(), contractFinishLocalDate, rs.getString(6), rs.getDate(7).toLocalDate()));
                                    }
                                }
                                if (stmt == null) break block24;
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
                        if (conn == null) return results;
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
                    return results;
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
                return results;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ArrayList<AirAttendant> getAirAttendantsByFlight(Flight flight) {
        ArrayList<AirAttendant> results = new ArrayList<AirAttendant>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var4_7 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        try (CallableStatement callst = conn.prepareCall("{ call qryGetFlightAttendantsByFlightID(?) }");){
                            int k = 1;
                            callst.setString(k++, flight.getFlightNum());
                            ResultSet rs = callst.executeQuery();
                            while (rs.next()) {
                                int i = 1;
                                Date contractFinishDate = rs.getDate(5);
                                LocalDate contractFinishLocalDate = null;
                                if (contractFinishDate != null) {
                                    contractFinishLocalDate = contractFinishDate.toLocalDate();
                                }
                                results.add(new AirAttendant(rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++).toLocalDate(), contractFinishLocalDate));
                            }
                        }
                        if (conn == null) return results;
                    }
                    catch (Throwable throwable2) {
                        if (throwable == null) {
                            throwable = throwable2;
                        } else if (throwable != throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        if (conn == null) throw throwable;
                        conn.close();
                        throw throwable;
                    }
                    conn.close();
                    return results;
                }
                catch (Throwable throwable3) {
                    if (throwable == null) {
                        throwable = throwable3;
                        throw throwable;
                    } else {
                        if (throwable == throwable3) throw throwable;
                        throwable.addSuppressed(throwable3);
                    }
                    throw throwable;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return results;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<GroundAttendantInShift> getGroundAttendantShifts() {
        ArrayList<GroundAttendant> gal = this.getGroundAttendants();
        ArrayList<GroundAttendantInShift> results = new ArrayList<GroundAttendantInShift>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var4_7 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        block23: {
                            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM AttendantShift");
                            try {
                                try (ResultSet rs = stmt.executeQuery();){
                                    while (rs.next()) {
                                        boolean i = true;
                                        LocalDateTime depTime = rs.getTimestamp(1).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                        LocalDateTime arrTime = rs.getTimestamp(4).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                        Shift shift = new Shift(depTime, arrTime);
                                        String gaid = rs.getString(2);
                                        GroundAttendant ga = (GroundAttendant)gal.stream().filter(g -> g.getID().equals(gaid)).collect(Collectors.toList()).get(0);
                                        results.add(new GroundAttendantInShift(shift, ga, rs.getString(3)));
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
                        if (conn == null) return results;
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
                    return results;
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
                return results;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }
}
