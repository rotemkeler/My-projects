/*
 * Decompiled with CFR 0.152.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import model.DeliveryArea;
import model.Person;
import utils.Gender;
import utils.Vehicle;

public class DeliveryPerson
extends Person
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    private Vehicle vehicle;
    private DeliveryArea area;

    public DeliveryPerson(String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle, DeliveryArea area) {
        super(idCounter++, firstName, lastName, birthDay, gender);
        this.vehicle = vehicle;
        this.area = area;
    }

    public DeliveryPerson(int id) {
        super(id);
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        DeliveryPerson.idCounter = idCounter;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public DeliveryArea getArea() {
        return this.area;
    }

    public void setArea(DeliveryArea area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getFirstName()) + " " + this.getLastName() + ", vehicle: " + (Object)((Object)this.vehicle);
    }

    protected Object readResolve() {
        if (this.getId() == idCounter) {
            idCounter = this.getId() + 1;
        }
        return this;
    }
}
