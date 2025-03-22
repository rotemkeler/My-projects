/*
 * Decompiled with CFR 0.152.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import model.Person;
import utils.Expertise;
import utils.Gender;

public class Cook
extends Person
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    private Expertise expert;
    private boolean isChef;

    public Cook(String firstName, String lastName, LocalDate birthDay, Gender gender, Expertise expert, boolean isChef) {
        super(idCounter++, firstName, lastName, birthDay, gender);
        this.expert = expert;
        this.isChef = isChef;
    }

    public Cook(int id) {
        super(id);
    }

    public Expertise getExpert() {
        return this.expert;
    }

    public void setExpert(Expertise expert) {
        this.expert = expert;
    }

    public boolean isChef() {
        return this.isChef;
    }

    public void setChef(boolean isChef) {
        this.isChef = isChef;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Cook.idCounter = idCounter;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getFirstName()) + " " + this.getLastName() + " , expert=" + (Object)((Object)this.expert);
    }

    protected Object readResolve() {
        if (this.getId() == idCounter) {
            idCounter = this.getId() + 1;
        }
        return this;
    }
}
