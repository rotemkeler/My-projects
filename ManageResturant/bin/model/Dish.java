/*
 * Decompiled with CFR 0.152.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Component;
import utils.DishType;

public class Dish
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    private int id;
    private String dishName;
    private DishType type;
    private ArrayList<Component> componenets;
    private double price;
    private int timeToMake;

    public Dish(String dishName, DishType type, ArrayList<Component> componenets, int timeToMake) {
        this.id = idCounter++;
        this.dishName = dishName;
        this.type = type;
        this.componenets = componenets;
        this.timeToMake = timeToMake;
        this.price = this.calcDishPrice();
    }

    public Dish(int id) {
        this.id = id;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Dish.idCounter = idCounter;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishName() {
        return this.dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public DishType getType() {
        return this.type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public List<Component> getComponenets() {
        return Collections.unmodifiableList(this.componenets);
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

    public int getTimeToMake() {
        return this.timeToMake;
    }

    public void setTimeToMake(int timeToMake) {
        this.timeToMake = timeToMake;
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
        Dish other = (Dish)obj;
        return this.id == other.id;
    }

    public String toString() {
        return String.valueOf(this.id) + "- " + (Object)((Object)this.type) + "-" + this.dishName + ", price: " + String.format("%.2f", this.price) + ", time to make: " + this.timeToMake;
    }

    public String toMenu() {
        return String.valueOf(this.dishName) + ", price: " + String.format("%.2f", this.price);
    }

    public double calcDishPrice() {
        double price = 0.0;
        for (Component c : this.getComponenets()) {
            price += c.getPrice();
        }
        return price *= 3.0;
    }

    public boolean addComponent(Component c) {
        return this.componenets.add(c);
    }

    public boolean removeComponent(Component c) {
        return this.componenets.remove(c);
    }

    protected Object readResolve() {
        if (this.id == idCounter) {
            idCounter = this.id + 1;
        }
        return this;
    }
}
