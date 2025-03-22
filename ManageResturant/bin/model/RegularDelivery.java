/*
 * Decompiled with CFR 0.152.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import model.Delivery;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Order;

public class RegularDelivery
extends Delivery
implements Serializable {
    private static final long serialVersionUID = 1L;
    private TreeSet<Order> orders;

    public RegularDelivery(DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered, LocalDate deliveredDate) {
        super(deliveryPerson, area, isDelivered, deliveredDate);
        this.orders = new TreeSet();
    }

    public RegularDelivery(TreeSet<Order> orders, DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered, LocalDate deliveredDate) {
        super(deliveryPerson, area, isDelivered, deliveredDate);
        this.orders = orders;
    }

    public RegularDelivery(int id) {
        super(id);
    }

    public SortedSet<Order> getOrders() {
        return Collections.unmodifiableSortedSet(this.orders);
    }

    public void setOrders(TreeSet<Order> orders) {
        this.orders = orders;
    }

    public boolean addOrder(Order o) {
        return this.orders.add(o);
    }

    public boolean removeOrder(Order o) {
        return this.orders.remove(o);
    }

    @Override
    public String toString() {
        return "Regular Delivery: " + this.getId() + "- Delivery Person: " + this.getDeliveryPerson() + " " + ", Delivered: " + this.isDelivered();
    }
}
