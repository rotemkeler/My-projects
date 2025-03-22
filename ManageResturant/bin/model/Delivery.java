/*
 * Decompiled with CFR 0.152.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import model.DeliveryArea;
import model.DeliveryPerson;

public abstract class Delivery
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    private int id;
    private DeliveryPerson deliveryPerson;
    private DeliveryArea area;
    private boolean isDelivered;
    private LocalDate deliveredDate;

    public Delivery(DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered, LocalDate diliveredDate) {
        this.id = idCounter++;
        this.deliveryPerson = deliveryPerson;
        this.area = area;
        this.isDelivered = isDelivered;
        this.deliveredDate = diliveredDate;
    }

    public Delivery(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeliveryPerson getDeliveryPerson() {
        return this.deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public DeliveryArea getArea() {
        return this.area;
    }

    public void setArea(DeliveryArea area) {
        this.area = area;
    }

    public boolean isDelivered() {
        return this.isDelivered;
    }

    public void setDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public LocalDate getDeliveredDate() {
        return this.deliveredDate;
    }

    public void setDeliveredDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + this.id;
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
        Delivery other = (Delivery)obj;
        return this.id == other.id;
    }

    public String toString() {
        return String.valueOf(this.id) + ", deliveryPerson: " + this.deliveryPerson + ", area: " + this.area + "Delivered: " + this.isDelivered;
    }

    protected Object readResolve() {
        if (this.id == idCounter) {
            idCounter = this.id + 1;
        }
        return this;
    }
}
