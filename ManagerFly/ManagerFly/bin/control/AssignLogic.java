/*
 * Decompiled with CFR 0.152.
 */
package control;

import entity.AirAttendant;
import entity.Flight;
import entity.GroundAttendantInShift;
import entity.Shift;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import util.Consts;

public class AssignLogic {
    private static AssignLogic _instance;

    private AssignLogic() {
    }

    public static AssignLogic getInstance() {
        if (_instance == null) {
            _instance = new AssignLogic();
        }
        return _instance;
    }

    /*
     * Loose catch block
     */
    public boolean addAirAttendant(String id, String fName, String lName, LocalDate contractStart, LocalDate contractFinish) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block25: {
            block24: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var7_10 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("{ call qryInsAirAttendant(?,?,?,?,?) }");
                int i = 1;
                Date start = Date.valueOf(contractStart);
                stmt.setString(i++, id);
                stmt.setString(i++, fName);
                stmt.setString(i++, lName);
                stmt.setDate(i++, start);
                Date finish = null;
                if (contractFinish != null) {
                    finish = Date.valueOf(contractFinish);
                    stmt.setDate(i++, finish);
                } else {
                    stmt.setNull(i++, 91);
                }
                stmt.executeUpdate();
                if (stmt == null) break block24;
                stmt.close();
            }
            if (conn == null) break block25;
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
    public boolean addGroundAttendant(String id, String fName, String lName, LocalDate contractStart, LocalDate contractFinish) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block25: {
            block24: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var7_10 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("{ call qryInsGroundAttendant(?,?,?,?,?) }");
                int i = 1;
                Date start = Date.valueOf(contractStart);
                stmt.setString(i++, id);
                stmt.setString(i++, fName);
                stmt.setString(i++, lName);
                stmt.setDate(i++, start);
                Date finish = null;
                if (contractFinish != null) {
                    finish = Date.valueOf(contractFinish);
                    stmt.setDate(i++, finish);
                } else {
                    stmt.setNull(i++, 91);
                }
                stmt.executeUpdate();
                if (stmt == null) break block24;
                stmt.close();
            }
            if (conn == null) break block25;
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
    public boolean addPilot(String id, String fName, String lName, LocalDate contractStart, LocalDate contractFinish, String licenceID, LocalDate issuedDate) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block25: {
            block24: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var9_12 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("{ call qryInsPilot(?,?,?,?,?,?,?) }");
                int i = 1;
                Date start = Date.valueOf(contractStart);
                Date issued = Date.valueOf(issuedDate);
                stmt.setString(i++, id);
                stmt.setString(i++, fName);
                stmt.setString(i++, lName);
                stmt.setDate(i++, start);
                Date finish = null;
                if (contractFinish != null) {
                    finish = Date.valueOf(contractFinish);
                    stmt.setDate(i++, finish);
                } else {
                    stmt.setNull(i++, 91);
                }
                stmt.setString(i++, licenceID);
                stmt.setDate(i++, issued);
                stmt.executeUpdate();
                if (stmt == null) break block24;
                stmt.close();
            }
            if (conn == null) break block25;
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
    public boolean editEmployee(String id, String fName, String lName, LocalDate contractFinish, String query) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block25: {
            block24: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var7_10 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall(query);
                int i = 1;
                stmt.setString(i++, fName);
                stmt.setString(i++, lName);
                Date finish = null;
                if (contractFinish != null) {
                    finish = Date.valueOf(contractFinish);
                    stmt.setDate(i++, finish);
                } else {
                    stmt.setNull(i++, 91);
                }
                stmt.setString(i++, id);
                stmt.executeUpdate();
                if (stmt == null) break block24;
                stmt.close();
            }
            if (conn == null) break block25;
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
    public boolean deleteAirAttendantsFromFlight(Flight flight) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var3_6 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("{ call qryDelAttendantsInFlight(?) }");
                stmt.setString(1, flight.getFlightNum());
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
    public boolean addAirAttendantToFlight(AirAttendant aa, Flight f) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var4_7 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("INSERT INTO AttendantInFlightTbl VALUES(?,?);");
                int i = 1;
                stmt.setString(i++, f.getFlightNum());
                stmt.setString(i++, aa.getID());
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
    public boolean updateMainPilot(String pilotId, String flightNum) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var4_7 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("Update FlightTbl Set CheifPilotID=? WHERE SerialNum=?;");
                int i = 1;
                stmt.setString(i++, pilotId);
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
    public boolean updateSecondaryPilot(String pilotId, String flightNum) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var4_7 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("Update FlightTbl Set CoPilotID=? WHERE SerialNum=?;");
                int i = 1;
                stmt.setString(i++, pilotId);
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
    public boolean addShift(Shift shift) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var3_6 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("INSERT INTO ShiftTbl VALUES(?,?);");
                int i = 1;
                Timestamp depatureTimeStamp = Timestamp.valueOf(shift.getStart());
                Timestamp landingTimeStamp = Timestamp.valueOf(shift.getEnd());
                stmt.setTimestamp(i++, depatureTimeStamp);
                stmt.setTimestamp(i++, landingTimeStamp);
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
    public boolean addGroundAttendantToShift(GroundAttendantInShift gaos) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var3_6 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("INSERT INTO AttendantShift VALUES(?,?,?,?);");
                boolean i = true;
                Timestamp depatureTimeStamp = Timestamp.valueOf(gaos.getShift().getStart());
                Timestamp landingTimeStamp = Timestamp.valueOf(gaos.getShift().getEnd());
                stmt.setTimestamp(1, depatureTimeStamp);
                stmt.setTimestamp(4, landingTimeStamp);
                stmt.setString(2, gaos.getGroundAttendant().getID());
                stmt.setString(3, gaos.getRole());
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
