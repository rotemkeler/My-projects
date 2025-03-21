/*
 * Decompiled with CFR 0.152.
 */
package util;

import java.net.URLDecoder;

public class Consts {
    protected static final String DB_FILEPATH = Consts.getDBPath();
    public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
    public static final String JDBC_STR = "net.ucanaccess.jdbc.UcanaccessDriver";
    public static final String[] FLIGHT_STATUS = new String[]{"on time", "delayed", "cancelled"};
    public static final String[] FLIGHT_ORDER_STATUS = new String[]{"Init", "Pre Sale", "Regular Sale"};
    public static final String[] ORDER_STATUS = new String[]{"initialize", "pre-sale", "regular-sale"};
    public static final String[] SEAT_TYPES = new String[]{"first class", "business", "tourists"};
    public static final String[] SHIFT_ROLE = new String[]{"validate tickets", "allocate seats", "tag and send luggage"};
    public static final int MIN_PILOTS = 2;
    public static final int MIN_AIR_ATTENDANTS = 2;
    public static final int MAX_COL_NUM = 1;
    public static final int MAX_CITY_CHARS = 40;
    public static final int MAX_COUNTRY_CHARS = 40;
    public static final int MAX_TAIL_NUM = 15;
    public static final String SQL_SEL_FLIGHT = "SELECT * FROM FlightTbl";
    public static final String SQL_DEL_FLIGHT = "{ call qryDelFlight(?) }";
    public static final String SQL_INS_FLIGHT = "{ call qryInsFlight(?,?,?,?,?,?,?,?,?,?) }";
    public static final String SQL_UPD_FLIGHT = "{ call qryUpdFlight(?,?,?,?) }";
    public static final String SQL_UPD_FLIGHT_STATUS = "{ call qryUpdFlightStatus(?,?,?) }";
    public static final String SQL_CHECK_FLIGHT = "SELECT * FROM FlightTbl WHERE SerialNum=?;";
    public static final String SQL_UPDATE_FLIGHT_STATUS = "Update FlightTbl Set Status=? WHERE SerialNum=?;";
    public static final String SQL_CANCELES_FLIGHTS = "SELECT * FROM FlightTbl WHERE Status='cancelled';";
    public static final String SQL_SEL_AIRPORT = "SELECT * FROM AirPortTbl";
    public static final String SQL_INS_AIRPORT = "{ call qryInsAirPort(?,?,?,?,?) }";
    public static final String SQL_UPD_AIRPORT_STATUS = "{ call qryUpdAirPortStatus(?,?) }";
    public static final String SQL_SEL_AIRPLANE = "SELECT * FROM AirPlaneTbl";
    public static final String SQL_INS_AIRPLANE = "{ call qryInsAirPlane(?,?) }";
    public static final String SQL_SEL_FLIGHTSEATS = "SELECT * FROM FlightSeatTbl";
    public static final String SQL_INS_FLIGHTSEATS = "{ call qryInsFlightSeat(?,?,?,?,?) }";
    public static final String SQL_SEL_AIRATTENDANTS = "SELECT * FROM AirAttendantTbl";
    public static final String SQL_SEL_GROUNDATTENDANTS = "SELECT * FROM GroundAttendantTbl";
    public static final String SQL_SEL_GROUNDATTENDANTS_SHIFT = "SELECT * FROM AttendantShift";
    public static final String SQL_SEL_PILOTS = "SELECT * FROM PilotTbl";
    public static final String SQL_INS_AIRATTENDANT = "{ call qryInsAirAttendant(?,?,?,?,?) }";
    public static final String SQL_INS_GROUNDATTENDANT = "{ call qryInsGroundAttendant(?,?,?,?,?) }";
    public static final String SQL_INS_PILOT = "{ call qryInsPilot(?,?,?,?,?,?,?) }";
    public static final String SQL_UPD_AIRATTENDANT = "{ call qryUpdAirAttendant(?,?,?,?) }";
    public static final String SQL_UPD_GROUNDATTENDANT = "{ call qryUpdGroundAttendant(?,?,?,?) }";
    public static final String SQL_UPD_PILOT = "{ call qryUpdPilot(?,?,?,?) }";
    public static final String SQL_GET_AIRATTENDANT_BY_FLIGHT = "{ call qryGetFlightAttendantsByFlightID(?) }";
    public static final String SQL_DELETE_AIRATTENDANT_BY_FLIGHT = "{ call qryDelAttendantsInFlight(?) }";
    public static final String SQL_INSERT_AIR_ATTENDANT_FLIGHT = "INSERT INTO AttendantInFlightTbl VALUES(?,?);";
    public static final String SQL_UPDATE_MAIN_PILOT = "Update FlightTbl Set CheifPilotID=? WHERE SerialNum=?;";
    public static final String SQL_UPDATE_SECONDARY_PILOT = "Update FlightTbl Set CoPilotID=? WHERE SerialNum=?;";
    public static final String SQL_INSERT_SHIFT = "INSERT INTO ShiftTbl VALUES(?,?);";
    public static final String SQL_INSERT_GROUD_ATTENDANT_SHIFT = "INSERT INTO AttendantShift VALUES(?,?,?,?);";
    public static final String SQL_EXPORT_DATA_TEST = "SELECT FlightTbl.SerialNum, FlightTbl.DepatureAirportID, FlightTbl.DepatureTime, FlightTbl.DestinationAirportID, FlightTbl.LandingTime, FlightTbl.Status, FlightTbl.AirPlaneTailNum, AirPortTbl.Country, AirPortTbl.City, AirPortTbl.Country, AirPortTbl.City\r\nFROM AirPortTbl INNER JOIN FlightTbl ON (AirPortTbl.airportCode = FlightTbl.DepatureAirportID) AND (AirPortTbl.airportCode = FlightTbl.DestinationAirportID)\r\nWHERE (((FlightTbl.UpdateDate)=?));\r\n";

    private Consts() {
        throw new AssertionError();
    }

    private static String getDBPath() {
        try {
            String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decoded = URLDecoder.decode(path, "UTF-8");
            if (decoded.contains(".jar")) {
                decoded = decoded.substring(0, decoded.lastIndexOf(47));
                return String.valueOf(decoded) + "/src/entity/DB_ManagerFly.accdb";
            }
            decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
            System.out.println(decoded);
            return String.valueOf(decoded) + "src/entity/DB_ManagerFly.accdb";
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
