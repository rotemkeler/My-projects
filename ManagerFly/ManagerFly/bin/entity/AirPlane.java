/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.FlightSeat;
import java.util.HashMap;

public class AirPlane {
    private final String tailNum;
    private int attendantsNum;
    private HashMap<Integer, FlightSeat> seats;

    public AirPlane(String tailNum, int attendantsNum) {
        this.tailNum = tailNum;
        this.attendantsNum = attendantsNum;
        this.seats = new HashMap();
    }

    public AirPlane(String tailNum) {
        this.tailNum = tailNum;
    }

    public AirPlane(String tailNum, int attendantsNum, HashMap<Integer, FlightSeat> seats) {
        this.tailNum = tailNum;
        this.attendantsNum = attendantsNum;
        this.seats = seats;
    }

    public String getTailNum() {
        return this.tailNum;
    }

    public int getAttendantsNum() {
        return this.attendantsNum;
    }

    public void setAttendantsNum(int attendantsNum) {
        this.attendantsNum = attendantsNum;
    }

    public HashMap<Integer, FlightSeat> getSeats() {
        return this.seats;
    }

    public void setSeats(HashMap<Integer, FlightSeat> seats) {
        this.seats = seats;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.tailNum == null ? 0 : this.tailNum.hashCode());
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
        AirPlane other = (AirPlane)obj;
        return !(this.tailNum == null ? other.tailNum != null : !this.tailNum.equals(other.tailNum));
    }

    public String toString() {
        return this.tailNum;
    }
}
