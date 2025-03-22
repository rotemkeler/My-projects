/*
 * Decompiled with CFR 0.152.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import model.Delivery;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Order;

public class ExpressDelivery
extends Delivery
implements Serializable {
    private static final long serialVersionUID = 1L;
    private Order order;
    private double postage;

    public ExpressDelivery(DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered, Order order, double postage, LocalDate deliveredDate) {
        super(deliveryPerson, area, isDelivered, deliveredDate);
        this.order = order;
        this.postage = postage;
    }

    public ExpressDelivery(DeliveryPerson deliveryPerson, DeliveryArea area, boolean isDelivered, Order order, LocalDate deliveredDate) {
        super(deliveryPerson, area, isDelivered, deliveredDate);
        this.order = order;
        this.postage = 5.0;
    }

    public ExpressDelivery(int id) {
        super(id);
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getPostage() {
        return this.postage;
    }

    public void setPostage(double postage) {
        this.postage = postage;
    }

    @Override
    public String toString() {
        return "Express Delivery: " + this.getId() + "- Delivery Person: " + this.getDeliveryPerson() + " " + this.getArea() + ", Delivered: " + this.isDelivered() + ", postage: " + this.postage;
    }
}
