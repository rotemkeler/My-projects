/*
 * Decompiled with CFR 0.152.
 */
package entity;

public class AirPort {
    private final int airportCode;
    private String city;
    private String country;
    private int timeZone;
    private boolean isOpen;

    public AirPort(int airportCode, String city, String country, int timeZone) {
        this.airportCode = airportCode;
        this.city = city;
        this.country = country;
        this.timeZone = timeZone;
    }

    public AirPort(int airportCode, String city, String country, int timeZone, boolean isOpen) {
        this.airportCode = airportCode;
        this.city = city;
        this.country = country;
        this.timeZone = timeZone;
        this.isOpen = isOpen;
    }

    public AirPort(int airportCode) {
        this.airportCode = airportCode;
    }

    public AirPort(int airportCode, String city, String country) {
        this.airportCode = airportCode;
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

    public int getAirportCode() {
        return this.airportCode;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + this.airportCode;
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
        AirPort other = (AirPort)obj;
        return this.airportCode == other.airportCode;
    }

    public String toString() {
        return " " + this.airportCode + " " + this.city + " " + this.country + ", GMT = " + this.timeZone + " open status: " + this.isOpen;
    }
}
