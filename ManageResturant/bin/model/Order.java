/*
 * Decompiled with CFR 0.152.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Component;
import model.Customer;
import model.Delivery;
import model.DeliveryArea;
import model.Dish;
import utils.MyFileLogWriter;

public class Order
implements Comparable<Order>,
Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    private Integer id;
    private Customer customer;
    private ArrayList<Dish> dishes;
    private Delivery delivery;

    public Order(Customer customer, ArrayList<Dish> dishes, Delivery delivery) {
        this.id = idCounter++;
        this.customer = customer;
        this.dishes = dishes;
        this.delivery = delivery;
    }

    public Order(int id) {
        this.id = id;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Order.idCounter = idCounter;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Dish> getDishes() {
        return Collections.unmodifiableList(this.dishes);
    }

    public Delivery getDelivery() {
        return this.delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
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
        Order other = (Order)obj;
        return this.id == other.id;
    }

    public String toString() {
        return this.id + "- customer: " + this.customer;
    }

    public double calcOrderRevenue() {
        double revenue = 0.0;
        for (Dish d : this.getDishes()) {
            double price = d.calcDishPrice();
            double cost = 0.0;
            for (Component c : d.getComponenets()) {
                cost += c.getPrice();
            }
            revenue += price - cost;
        }
        MyFileLogWriter.println("Order Revenue = " + revenue);
        return revenue;
    }

    public int orderWaitingTime(DeliveryArea da) {
        int time = 0;
        time += da.getDeliverTime();
        for (Dish d : this.getDishes()) {
            time += d.getTimeToMake();
        }
        MyFileLogWriter.println("Time for order: " + this + " is " + time + " minutes");
        return time;
    }

    public boolean addDish(Dish d) {
        return this.dishes.add(d);
    }

    public boolean removeDish(Dish d) {
        return this.dishes.remove(d);
    }

    @Override
    public int compareTo(Order o) {
        return this.id.compareTo(o.getId());
    }

    protected Object readResolve() {
        if (this.id == idCounter) {
            idCounter = this.id + 1;
        }
        return this;
    }
}
