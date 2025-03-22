/*
 * Decompiled with CFR 0.152.
 */
package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import model.Delivery;
import model.DeliveryPerson;
import utils.Neighberhood;

public class DeliveryArea
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    private int id;
    private String areaName;
    private HashSet<DeliveryPerson> delPersons;
    private HashSet<Delivery> delivers;
    private HashSet<Neighberhood> neighberhoods;
    private final int deliverTime;

    public DeliveryArea(String areaName, HashSet<Neighberhood> neighberhoods, int deliverTime) {
        this.id = idCounter++;
        this.areaName = areaName;
        this.neighberhoods = neighberhoods;
        this.deliverTime = deliverTime;
        this.delPersons = new HashSet();
        this.delivers = new HashSet();
    }

    public DeliveryArea(int id) {
        this.id = id;
        this.deliverTime = 0;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        DeliveryArea.idCounter = idCounter;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Set<DeliveryPerson> getDelPersons() {
        return Collections.unmodifiableSet(this.delPersons);
    }

    public Set<Delivery> getDelivers() {
        return Collections.unmodifiableSet(this.delivers);
    }

    public Set<Neighberhood> getNeighberhoods() {
        return Collections.unmodifiableSet(this.neighberhoods);
    }

    public int getDeliverTime() {
        return this.deliverTime;
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
        DeliveryArea other = (DeliveryArea)obj;
        return this.id == other.id;
    }

    public String toString() {
        return String.valueOf(this.id) + "- " + "area: " + this.areaName + ", neighberhoods: " + this.neighberhoods + ", deliver time: " + this.deliverTime;
    }

    public boolean addDelPerson(DeliveryPerson dp) {
        return this.delPersons.add(dp);
    }

    public boolean removeDelPerson(DeliveryPerson dp) {
        return this.delPersons.remove(dp);
    }

    public boolean addDelivery(Delivery d) {
        return this.delivers.add(d);
    }

    public boolean removeDelivery(Delivery d) {
        return this.delivers.remove(d);
    }

    public boolean addNeighberhood(Neighberhood neighberhood) {
        return this.neighberhoods.add(neighberhood);
    }

    public boolean removeNeighberhood(Neighberhood neighberhood) {
        return this.neighberhoods.remove((Object)neighberhood);
    }

    protected Object readResolve() {
        if (this.id == idCounter) {
            idCounter = this.id + 1;
        }
        return this;
    }
}
