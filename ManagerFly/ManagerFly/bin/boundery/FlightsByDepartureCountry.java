/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jfree.chart.ChartFactory
 *  org.jfree.chart.ChartPanel
 *  org.jfree.chart.JFreeChart
 *  org.jfree.chart.labels.CategoryItemLabelGenerator
 *  org.jfree.chart.labels.ItemLabelAnchor
 *  org.jfree.chart.labels.ItemLabelPosition
 *  org.jfree.chart.labels.StandardCategoryItemLabelGenerator
 *  org.jfree.chart.plot.CategoryPlot
 *  org.jfree.chart.plot.PlotOrientation
 *  org.jfree.chart.renderer.category.CategoryItemRenderer
 *  org.jfree.data.category.CategoryDataset
 *  org.jfree.data.category.DefaultCategoryDataset
 *  org.jfree.ui.TextAnchor
 */
package boundery;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import util.Consts;

public class FlightsByDepartureCountry
extends JFrame {
    private static String applicationTitle = "Flight precentage by destination country in the last month vs all time";
    private static String chartTitle = "Flight precentage by destination country in the last month vs all time";

    public FlightsByDepartureCountry(String country) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart((String)chartTitle, (String)"Destination Airport Code", (String)"Flights Precentage", (CategoryDataset)this.createDataset(country), (PlotOrientation)PlotOrientation.VERTICAL, (boolean)true, (boolean)true, (boolean)false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        barChart.getPlot().setBackgroundPaint((Paint)Color.WHITE);
        CategoryPlot plot = barChart.getCategoryPlot();
        Font font3 = new Font("Tahoma", 0, 16);
        plot.getDomainAxis().setLabelFont(font3);
        plot.getRangeAxis().setLabelFont(font3);
        chartPanel.setPreferredSize(new Dimension(700, 700));
        this.setContentPane((Container)chartPanel);
        CategoryItemRenderer renderer = ((CategoryPlot)barChart.getPlot()).getRenderer();
        StandardCategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        renderer.setBaseItemLabelGenerator((CategoryItemLabelGenerator)generator);
        renderer.setBaseItemLabelFont(new Font("Tahoma", 1, 16));
        renderer.setBaseItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
        renderer.setBasePositiveItemLabelPosition(position);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private LinkedHashMap<Integer, Double> getTotalFlightPrecentage(String country) {
        String query = "select DestinationAirportID, (Count(DestinationAirportID)* 100 / (Select Count(*) From FlightTbl)) as total\r\nfrom FlightTbl\r\nwhere DestinationAirportID in (select airportCode\r\nfrom AirPortTbl\r\nwhere Country like '" + country + "')\r\n" + "group by DestinationAirportID;";
        LinkedHashMap<Integer, Double> res = new LinkedHashMap<Integer, Double>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var5_8 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        block23: {
                            PreparedStatement stmt = conn.prepareStatement(query);
                            try {
                                try (ResultSet rs = stmt.executeQuery();){
                                    while (rs.next()) {
                                        int i = 1;
                                        res.put(rs.getInt(i++), rs.getDouble(i++));
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
                        if (conn == null) return res;
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
                    return res;
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
                return res;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private LinkedHashMap<Integer, Double> getMonthlyFlightPrecentage(String country) {
        String query = "select DestinationAirportID, (Count(DestinationAirportID)* 100 / (Select Count(*) From FlightTbl)) as monthly\r\nfrom FlightTbl\r\nwhere Month(DepatureTime) = Month(Date()) AND Year(DepatureTime) = Year(Date())\r\nAND DestinationAirportID in (select airportCode\r\nfrom AirPortTbl\r\nwhere Country like '" + country + "')\r\n" + "group by DestinationAirportID;";
        LinkedHashMap<Integer, Double> res = new LinkedHashMap<Integer, Double>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try {
                Throwable throwable = null;
                Object var5_8 = null;
                try {
                    Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    try {
                        block23: {
                            PreparedStatement stmt = conn.prepareStatement(query);
                            try {
                                try (ResultSet rs = stmt.executeQuery();){
                                    while (rs.next()) {
                                        int i = 1;
                                        res.put(rs.getInt(i++), rs.getDouble(i++));
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
                        if (conn == null) return res;
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
                    return res;
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
                return res;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    private CategoryDataset createDataset(String country) {
        String month = "Last Month";
        String all = "All Time";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        LinkedHashMap<Integer, Double> totalData = this.getTotalFlightPrecentage(country);
        LinkedHashMap<Integer, Double> monthlyData = this.getMonthlyFlightPrecentage(country);
        ArrayList<Integer> keys = new ArrayList<Integer>(totalData.keySet());
        int i = 0;
        while (i < keys.size()) {
            double monthly = 0.04;
            if (monthlyData.containsKey(keys.get(i))) {
                monthly = monthlyData.get(keys.get(i));
            }
            dataset.addValue(monthly, (Comparable)((Object)"Last Month"), (Comparable)keys.get(i));
            dataset.addValue((Number)totalData.get(keys.get(i)), (Comparable)((Object)"All Time"), (Comparable)keys.get(i));
            ++i;
        }
        return dataset;
    }
}
