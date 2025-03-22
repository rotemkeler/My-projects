/*
 * Decompiled with CFR 0.152.
 */
package control;

import entity.AirPlane;
import entity.AirPort;
import entity.Flight;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.Consts;

public class ImportXML {
    private static ImportXML _instance;

    private ImportXML() {
    }

    public static ImportXML getInstance() {
        if (_instance == null) {
            _instance = new ImportXML();
        }
        return _instance;
    }

    public HashMap<String, String> importFlightsFromXML() {
        HashMap<String, String> results = new HashMap<String, String>();
        boolean f = true;
        String path = "xml/flightStatus.xml";
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(path));
            doc.getDocumentElement().normalize();
            NodeList nl = doc.getElementsByTagName("flight");
            int i = 0;
            while (i < nl.getLength()) {
                if (nl.item(i).getNodeType() == 1) {
                    Element el = (Element)nl.item(i);
                    String flightNum = el.getAttribute("Id");
                    String status = el.getElementsByTagName("status").item(0).getTextContent();
                    boolean flag = this.vertiftFlight(flightNum);
                    if (!flag) {
                        JOptionPane.showMessageDialog(null, "Flight doesn't exists");
                    } else {
                        results.put(flightNum, status);
                        f = this.updateFlightStatus(flightNum, status);
                    }
                }
                ++i;
            }
            if (f) {
                JOptionPane.showMessageDialog(null, "flights data imported successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "flights data imported with errors!");
            }
        }
        catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return results;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean vertiftFlight(String flightNum) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var3_6 = null;
                try {
                    CallableStatement stmt;
                    Connection conn;
                    block16: {
                        conn = DriverManager.getConnection(Consts.CONN_STR);
                        try {
                            block17: {
                                stmt = conn.prepareCall("SELECT * FROM FlightTbl WHERE SerialNum=?;");
                                try {
                                    stmt.setString(1, flightNum);
                                    ResultSet rs = stmt.executeQuery();
                                    if (!rs.next()) break block16;
                                    if (stmt == null) break block17;
                                }
                                catch (Throwable throwable2) {
                                    if (stmt == null) throw throwable2;
                                    stmt.close();
                                    throw throwable2;
                                }
                                stmt.close();
                            }
                            if (conn == null) return true;
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
                        return true;
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn == null) return false;
                    conn.close();
                    return false;
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
                return false;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * Loose catch block
     */
    public boolean updateFlightStatus(String flightNum, String status) {
        CallableStatement stmt;
        Connection conn;
        Throwable throwable;
        block23: {
            block22: {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                throwable = null;
                Object var4_7 = null;
                conn = DriverManager.getConnection(Consts.CONN_STR);
                stmt = conn.prepareCall("Update FlightTbl Set Status=? WHERE SerialNum=?;");
                int i = 1;
                stmt.setString(i++, status);
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
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList<Flight> getCanceledFlights() {
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
                            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FlightTbl WHERE Status='cancelled';");
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
}
