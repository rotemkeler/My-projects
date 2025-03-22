/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Employee;
import java.time.LocalDate;

public class GroundAttendant
extends Employee {
    public GroundAttendant(String iD, String firstName, String lastName, LocalDate contractStart, LocalDate contractFinish) {
        super(iD, firstName, lastName, contractStart, contractFinish);
    }

    public GroundAttendant(String iD) {
        super(iD);
    }
}
