/*
 * Decompiled with CFR 0.152.
 */
package application;

import java.io.Serializable;
import java.time.LocalDate;
import utils.Gender;
import utils.Neighberhood;

public class Account
implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private Gender gender;
    private Neighberhood neighberhood;
    private String userName;
    private String password;
    private boolean isManager;
    private long idNumber;
    private String image;

    public Account(String firstName, String lastName, LocalDate birthDay, Gender gender, Neighberhood neighberhood, String userName, String password, boolean isManager, long idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.neighberhood = neighberhood;
        this.userName = userName;
        this.password = password;
        this.isManager = isManager;
        this.idNumber = idNumber;
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

    public Neighberhood getNeighberhood() {
        return this.neighberhood;
    }

    public void setNeighberhood(Neighberhood neighberhood) {
        this.neighberhood = neighberhood;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return this.isManager;
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

    public Long getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public static long getSerialversionuid() {
        return 1L;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
