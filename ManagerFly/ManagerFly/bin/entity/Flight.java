/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.AirPlane;
import entity.AirPort;
import java.time.Duration;
import java.time.LocalDateTime;

public class Flight {
    private final String flightNum;
    private LocalDateTime depatureTime;
    private LocalDateTime landingTime;
    private String flightStatus;
    private AirPort depatureAirport;
    private AirPort destinationAirport;
    private AirPlane airPlaneTail;
    private String cheifPilotID;
    private String coPilotID;
    private String orderStatus;

    public Flight(String flightNum, LocalDateTime depatureTime, LocalDateTime landingTime, AirPort depatureAirportID, AirPort destinationAirportID, AirPlane airPlaneTailNum, String cheifPilotID, String coPilotID, String orderStatus) {
        this.flightNum = flightNum;
        this.depatureTime = depatureTime;
        this.landingTime = landingTime;
        this.depatureAirport = depatureAirportID;
        this.destinationAirport = destinationAirportID;
        this.airPlaneTail = airPlaneTailNum;
        this.cheifPilotID = cheifPilotID;
        this.coPilotID = coPilotID;
        this.orderStatus = orderStatus;
    }

    public Flight(String flightNum) {
        this.flightNum = flightNum;
    }

    public Flight(String flightNum, LocalDateTime depatureTime, LocalDateTime landingTime, String flightStatus, String orderStatus) {
        this.flightNum = flightNum;
        this.depatureTime = depatureTime;
        this.landingTime = landingTime;
        this.flightStatus = flightStatus;
        this.orderStatus = orderStatus;
    }

    public Flight(String flightNum, LocalDateTime depatureTime, LocalDateTime landingTime, String flightStatus, AirPlane airPlane, AirPort dep, AirPort dest) {
        this.flightNum = flightNum;
        this.depatureTime = depatureTime;
        this.landingTime = landingTime;
        this.flightStatus = flightStatus;
        this.airPlaneTail = airPlane;
        this.depatureAirport = dep;
        this.destinationAirport = dest;
    }

    public Flight(String flightNum, LocalDateTime depatureTime, LocalDateTime landingTime, String flightStatus, AirPort depatureAirportID, AirPort destinationAirportID, AirPlane airPlaneTailNum, String cheifPilotID, String coPilotID, String orderStatus) {
        this.flightNum = flightNum;
        this.depatureTime = depatureTime;
        this.landingTime = landingTime;
        this.flightStatus = flightStatus;
        this.depatureAirport = depatureAirportID;
        this.destinationAirport = destinationAirportID;
        this.airPlaneTail = airPlaneTailNum;
        this.cheifPilotID = cheifPilotID;
        this.coPilotID = coPilotID;
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getDepatureTime() {
        return this.depatureTime;
    }

    public void setDepatureTime(LocalDateTime depatureTime) {
        this.depatureTime = depatureTime;
    }

    public LocalDateTime getLandingTime() {
        return this.landingTime;
    }

    public void setLandingTime(LocalDateTime landingTime) {
        this.landingTime = landingTime;
    }

    public String getFlightStatus() {
        return this.flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public AirPort getDepatureAirportID() {
        return this.depatureAirport;
    }

    public void setDepatureAirportID(AirPort depatureAirportID) {
        this.depatureAirport = depatureAirportID;
    }

    public AirPort getDestinationAirportID() {
        return this.destinationAirport;
    }

    public void setDestinationAirportID(AirPort destinationAirportID) {
        this.destinationAirport = destinationAirportID;
    }

    public AirPlane getAirPlaneTailNum() {
        return this.airPlaneTail;
    }

    public void setAirPlaneTailNum(AirPlane airPlaneTailNum) {
        this.airPlaneTail = airPlaneTailNum;
    }

    public String getCheifPilotID() {
        return this.cheifPilotID;
    }

    public void setCheifPilotID(String cheifPilotID) {
        this.cheifPilotID = cheifPilotID;
    }

    public String getCoPilotID() {
        return this.coPilotID;
    }

    public void setCoPilotID(String coPilotID) {
        this.coPilotID = coPilotID;
    }

    public String getFlightNum() {
        return this.flightNum;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.flightNum == null ? 0 : this.flightNum.hashCode());
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
        Flight other = (Flight)obj;
        return !(this.flightNum == null ? other.flightNum != null : !this.flightNum.equals(other.flightNum));
    }

    public String fullToString() {
        return "Flight [flightNum=" + this.flightNum + ", depatureTime=" + this.depatureTime + ", LandingTime=" + this.landingTime + ", flightStatus=" + this.flightStatus + ", depatureAirportID=" + this.depatureAirport + ", destinationAirportID=" + this.destinationAirport + ", airPlaneTailNum=" + this.airPlaneTail + ", cheifPilotID=" + this.cheifPilotID + ", coPilotID=" + this.coPilotID + "]";
    }

    public String toString() {
        return "flight num = " + this.flightNum;
    }

    public double calcFlightDuration() {
        double HoursBetween = Duration.between(this.depatureTime, this.landingTime).toHours();
        return HoursBetween;
    }
}
