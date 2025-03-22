/*
 * Decompiled with CFR 0.152.
 */
package entity;

import java.time.LocalDateTime;

public class Shift {
    private LocalDateTime start;
    private LocalDateTime end;

    public Shift(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String toString() {
        return "Shift [start=" + this.start + ", end=" + this.end + "]";
    }
}
