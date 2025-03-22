/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.GroundAttendant;
import entity.Shift;

public class GroundAttendantInShift {
    private Shift shift;
    private GroundAttendant groundAttendant;
    private String role;

    public GroundAttendantInShift(Shift shift, GroundAttendant groundAttendant, String role) {
        this.shift = shift;
        this.groundAttendant = groundAttendant;
        this.role = role;
    }

    public Shift getShift() {
        return this.shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public GroundAttendant getGroundAttendant() {
        return this.groundAttendant;
    }

    public void setGroundAttendant(GroundAttendant groundAttendant) {
        this.groundAttendant = groundAttendant;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return this.shift.getStart() + "             " + this.shift.getEnd() + "             " + this.role + "                  " + this.groundAttendant.toString2();
    }
}
