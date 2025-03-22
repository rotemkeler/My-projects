/*
 * Decompiled with CFR 0.152.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import utils.Gender;

public abstract class Person
implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private Gender gender;

    public Person(int id, String firstName, String lastName, LocalDate birthDay, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
        Person other = (Person)obj;
        return this.id == other.id;
    }

    public String toString() {
        return "[firstName=" + this.firstName + ", lastName=" + this.lastName + "]";
    }
}
