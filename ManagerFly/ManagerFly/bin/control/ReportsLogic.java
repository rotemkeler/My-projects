/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.sf.jasperreports.engine.JasperFillManager
 *  net.sf.jasperreports.engine.JasperPrint
 *  net.sf.jasperreports.swing.JRViewer
 */
package control;

import java.awt.Component;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import util.Consts;

public class ReportsLogic {
    private static ReportsLogic _instance;

    private ReportsLogic() {
    }

    public static ReportsLogic getInstance() {
        if (_instance == null) {
            _instance = new ReportsLogic();
        }
        return _instance;
    }

    public JFrame compileBiggestFlights(int seatNum, LocalDate from, LocalDate until) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var5_8 = null;
                try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);){
                    HashMap<String, Comparable<Date>> params = new HashMap<String, Comparable<Date>>();
                    LocalDateTime fromWwithTime = from.atTime(0, 0, 1);
                    LocalDateTime toWwithTime = until.atTime(23, 59, 59);
                    Timestamp sqlFrom = Timestamp.valueOf(fromWwithTime);
                    Timestamp sqlyeTo = Timestamp.valueOf(toWwithTime);
                    params.put("p1", sqlFrom);
                    params.put("p2", sqlyeTo);
                    params.put("p3", Integer.valueOf(seatNum));
                    JasperPrint print = JasperFillManager.fillReport((InputStream)this.getClass().getResourceAsStream("/boundery/BiggestFlightsReport.jasper"), params, (Connection)conn);
                    JFrame frame = new JFrame("Show Report for " + LocalDate.now());
                    frame.getContentPane().add((Component)new JRViewer(print));
                    frame.setExtendedState(6);
                    frame.pack();
                    return frame;
                }
                catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
