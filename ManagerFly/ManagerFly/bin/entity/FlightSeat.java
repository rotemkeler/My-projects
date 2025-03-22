/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.AirPlane;

public class FlightSeat {
    private final int seatID;
    private int rowNum;
    private String colNum;
    private String seatType;
    private AirPlane plane;

    public FlightSeat(int seatID, int rowNum, String colNum, String seatType, AirPlane plane) {
        this.seatID = seatID;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.seatType = seatType;
        this.plane = plane;
    }

    public FlightSeat(int seatID) {
        this.seatID = seatID;
    }

    public int getRowNum() {
        return this.rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getColNum() {
        return this.colNum;
    }

    public void setColNum(String colNum) {
        this.colNum = colNum;
    }

    public String getSeatType() {
        return this.seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getSeatID() {
        return this.seatID;
    }

    public AirPlane getPlane() {
        return this.plane;
    }

    public void setPlane(AirPlane plane) {
        this.plane = plane;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + this.seatID;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        FlightSeat other = (FlightSeat)obj;
        return this.seatID == other.seatID;
    }

    public String toString() {
        return "   \t\t" + this.seatID + "\t  \t\t\t" + this.rowNum + " " + this.colNum + "\t\t    \t    " + this.seatType;
    }
}
