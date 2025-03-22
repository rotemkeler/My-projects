/*
 * Decompiled with CFR 0.152.
 */
package model;

import java.io.Serializable;

public class Component
implements Comparable<Component>,
Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    private Integer id;
    private String componentName;
    private boolean hasLactose;
    private boolean hasGluten;
    private Double price;

    public Component(String componentName, boolean hasLactose, boolean hasGluten, double price) {
        this.id = idCounter++;
        this.componentName = componentName;
        this.hasLactose = hasLactose;
        this.hasGluten = hasGluten;
        this.setPrice(price);
    }

    public Component(int id) {
        this.id = id;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Component.idCounter = idCounter;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComponentName() {
        return this.componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public boolean isHasLactose() {
        return this.hasLactose;
    }

    public void setHasLactose(boolean hasLactose) {
        this.hasLactose = hasLactose;
    }

    public boolean isHasGluten() {
        return this.hasGluten;
    }

    public void setHasGluten(boolean hasGluten) {
        this.hasGluten = hasGluten;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price > 0.0) {
            this.price = price;
        } else {
            price = 0.0;
        }
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
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
        Component other = (Component)obj;
        return this.id == other.id;
    }

    public String toString() {
        return this.id + "- " + this.componentName + ", price= " + this.price;
    }

    @Override
    public int compareTo(Component o) {
        if (this.price.compareTo(o.getPrice()) != 0) {
            return this.price.compareTo(o.getPrice());
        }
        return this.id.compareTo(o.getId());
    }

    public String toMenu() {
        return "    - " + this.componentName;
    }

    protected Object readResolve() {
        if (this.id == idCounter) {
            idCounter = this.id + 1;
        }
        return this;
    }
}
