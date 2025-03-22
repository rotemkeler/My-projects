/*
 * Decompiled with CFR 0.152.
 */
package entity;

import java.time.LocalDate;

public abstract class Employee {
    private final String ID;
    private String firstName;
    private String lastName;
    private LocalDate contractStart;
    private LocalDate contractFinish;

    public Employee(String iD, String firstName, String lastName, LocalDate contractStart, LocalDate contractFinish) {
        this.ID = iD;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contractStart = contractStart;
        this.contractFinish = contractFinish;
    }

    public Employee(String iD) {
        this.ID = iD;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getContractStart() {
        return this.contractStart;
    }

    public void setContractStart(LocalDate contractStart) {
        this.contractStart = contractStart;
    }

    public LocalDate getContractFinish() {
        return this.contractFinish;
    }

    public void setContractFinish(LocalDate contractFinish) {
        this.contractFinish = contractFinish;
    }

    public String getID() {
        return this.ID;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.ID == null ? 0 : this.ID.hashCode());
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
        Employee other = (Employee)obj;
        return !(this.ID == null ? other.ID != null : !this.ID.equals(other.ID));
    }

    public String toString() {
        return "ID = " + this.ID + ", first name = " + this.firstName + ", last name = " + this.lastName;
    }

    public String fullToString() {
        return String.valueOf(this.toString()) + ", contract start date = " + this.contractStart + ", contract finish date = " + this.contractFinish;
    }

    public String toString2() {
        return String.valueOf(this.ID) + ", " + this.firstName + " " + this.lastName;
    }
}
