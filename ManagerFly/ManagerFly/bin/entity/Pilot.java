/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Employee;
import java.time.LocalDate;

public class Pilot
extends Employee {
    private String LicenceID;
    private LocalDate issuedDate;

    public Pilot(String iD, String firstName, String lastName, LocalDate contractStart, LocalDate contractFinish, String licenceID, LocalDate issuedDate) {
        super(iD, firstName, lastName, contractStart, contractFinish);
        this.LicenceID = licenceID;
        this.issuedDate = issuedDate;
    }

    public Pilot(String iD) {
        super(iD);
    }

    public String getLicenceID() {
        return this.LicenceID;
    }

    public void setLicenceID(String licenceID) {
        this.LicenceID = licenceID;
    }

    public LocalDate getIssuedDate() {
        return this.issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + " Licence ID = " + this.LicenceID + ", issued Date = " + this.issuedDate;
    }
}
