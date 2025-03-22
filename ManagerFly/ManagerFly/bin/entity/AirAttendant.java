/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Employee;
import java.time.LocalDate;

public class AirAttendant
extends Employee {
    public AirAttendant(String iD, String firstName, String lastName, LocalDate contractStart, LocalDate contractFinish) {
        super(iD, firstName, lastName, contractStart, contractFinish);
    }

    public AirAttendant(String iD) {
        super(iD);
    }
}
