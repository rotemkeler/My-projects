/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  application.Account
 *  application.MainController
 *  exceptions.IllegalCustomerException
 *  exceptions.NoComponentsExceptions
 *  exceptions.SensitiveException
 */
package model;

import application.Account;
import application.MainController;
import exceptions.IllegalCustomerException;
import exceptions.NoComponentsExceptions;
import exceptions.SensitiveException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import model.Component;
import model.Cook;
import model.Customer;
import model.Delivery;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Dish;
import model.ExpressDelivery;
import model.Order;
import model.RegularDelivery;
import utils.Expertise;
import utils.MyFileLogWriter;
import utils.Neighberhood;

public class Restaurant
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Restaurant restaurant = null;
    private HashMap<Integer, Cook> cooks = new HashMap();
    private HashMap<Integer, DeliveryPerson> deliveryPersons = new HashMap();
    private HashMap<Integer, Customer> customers = new HashMap();
    private HashMap<Integer, Dish> dishes = new HashMap();
    private HashMap<Integer, Component> componenets = new HashMap();
    private HashMap<Integer, Order> orders = new HashMap();
    private HashMap<Integer, Delivery> deliveries = new HashMap();
    private HashMap<Integer, DeliveryArea> areas = new HashMap();
    private HashMap<Customer, TreeSet<Order>> orderByCustomer = new HashMap();
    private HashMap<DeliveryArea, HashSet<Order>> orderByDeliveryArea = new HashMap();
    private HashSet<Customer> blackList = new HashSet();
    HashMap<String, Account> accounts = new HashMap();

    public void accountsProgram(Account account, String userName) {
        this.accounts.put(userName, account);
    }

    public HashMap<String, Account> getAccounts() {
        return this.accounts;
    }

    public static Restaurant getInstance() {
        if (MainController.rest != null) {
            return MainController.rest;
        }
        if (restaurant == null) {
            restaurant = new Restaurant();
        }
        return restaurant;
    }

    private Restaurant() {
    }

    public HashMap<Integer, Cook> getCooks() {
        return this.cooks;
    }

    public void setCooks(HashMap<Integer, Cook> cooks) {
        this.cooks = cooks;
    }

    public HashMap<Integer, DeliveryPerson> getDeliveryPersons() {
        return this.deliveryPersons;
    }

    public void setDeliveryPersons(HashMap<Integer, DeliveryPerson> deliveryPersons) {
        this.deliveryPersons = deliveryPersons;
    }

    public HashMap<Integer, Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(HashMap<Integer, Customer> customers) {
        this.customers = customers;
    }

    public HashMap<Integer, Dish> getDishes() {
        return this.dishes;
    }

    public void setDishes(HashMap<Integer, Dish> dishes) {
        this.dishes = dishes;
    }

    public HashMap<Integer, Component> getComponenets() {
        return this.componenets;
    }

    public void setComponenets(HashMap<Integer, Component> componenets) {
        this.componenets = componenets;
    }

    public HashMap<Integer, Order> getOrders() {
        return this.orders;
    }

    public void setOrders(HashMap<Integer, Order> orders) {
        this.orders = orders;
    }

    public HashMap<Integer, Delivery> getDeliveries() {
        return this.deliveries;
    }

    public void setDeliveries(HashMap<Integer, Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public HashMap<Integer, DeliveryArea> getAreas() {
        return this.areas;
    }

    public void setAreas(HashMap<Integer, DeliveryArea> areas) {
        this.areas = areas;
    }

    public HashMap<Customer, TreeSet<Order>> getOrderByCustomer() {
        return this.orderByCustomer;
    }

    public void setOrderByCustomer(HashMap<Customer, TreeSet<Order>> orderByCustomer) {
        this.orderByCustomer = orderByCustomer;
    }

    public HashMap<DeliveryArea, HashSet<Order>> getOrderByDeliveryArea() {
        return this.orderByDeliveryArea;
    }

    public void setOrderByDeliveryArea(HashMap<DeliveryArea, HashSet<Order>> orderByDeliveryArea) {
        this.orderByDeliveryArea = orderByDeliveryArea;
    }

    public HashSet<Customer> getBlackList() {
        return this.blackList;
    }

    public void setBlackList(HashSet<Customer> blackList) {
        this.blackList = blackList;
    }

    public boolean addCook(Cook cook) {
        if (cook == null || this.getCooks().containsKey(cook.getId())) {
            return false;
        }
        return this.getCooks().put(cook.getId(), cook) == null;
    }

    public boolean addDeliveryPerson(DeliveryPerson dp, DeliveryArea da) {
        if (dp == null || this.getDeliveryPersons().containsKey(dp.getId()) || !this.getAreas().containsKey(da.getId())) {
            return false;
        }
        return this.deliveryPersons.put(dp.getId(), dp) == null && da.addDelPerson(dp);
    }

    public boolean addCustomer(Customer cust) {
        if (cust == null || this.getCustomers().containsKey(cust.getId())) {
            return false;
        }
        return this.getCustomers().put(cust.getId(), cust) == null;
    }

    public boolean addDish(Dish dish) {
        if (dish == null || this.getDishes().containsKey(dish.getId())) {
            return false;
        }
        for (Component c : dish.getComponenets()) {
            if (this.getComponenets().containsKey(c.getId())) continue;
            return false;
        }
        return this.getDishes().put(dish.getId(), dish) == null;
    }

    public boolean addComponent(Component comp) {
        if (comp == null || this.getComponenets().containsKey(comp.getId())) {
            return false;
        }
        return this.getComponenets().put(comp.getId(), comp) == null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean addOrder(Order order) {
        try {
            if (order == null || this.getOrders().containsKey(order.getId())) {
                return false;
            }
            if (order.getCustomer() == null || !this.getCustomers().containsKey(order.getCustomer().getId())) {
                return false;
            }
            if (this.getBlackList().contains(order.getCustomer())) {
                throw new IllegalCustomerException();
            }
            for (Dish d : order.getDishes()) {
                if (!this.getDishes().containsKey(d.getId())) {
                    return false;
                }
                for (Component c : d.getComponenets()) {
                    if (order.getCustomer().isSensitiveToGluten() && c.isHasGluten()) {
                        throw new SensitiveException(String.valueOf(order.getCustomer().getFirstName()) + " " + order.getCustomer().getLastName(), d.getDishName());
                    }
                    if (!order.getCustomer().isSensitiveToLactose() || !c.isHasLactose()) continue;
                    throw new SensitiveException(String.valueOf(order.getCustomer().getFirstName()) + " " + order.getCustomer().getLastName(), d.getDishName());
                }
            }
            return this.getOrders().put(order.getId(), order) == null;
        }
        catch (SensitiveException e) {
            return false;
        }
        catch (IllegalCustomerException e) {
            return false;
        }
    }

    /*
     * Exception decompiling
     */
    public boolean addDelivery(Delivery delivery) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 9[WHILELOOP]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public boolean addDeliveryArea(DeliveryArea da) {
        if (da == null || this.getAreas().containsKey(da.getId())) {
            return false;
        }
        return this.getAreas().put(da.getId(), da) == null;
    }

    public boolean addCustomerToBlackList(Customer c) {
        if (c == null) {
            return false;
        }
        return this.getBlackList().add(c);
    }

    public boolean removeCook(Cook cook) {
        if (cook == null || !this.getCooks().containsKey(cook.getId())) {
            return false;
        }
        return this.getCooks().remove(cook.getId()) != null;
    }

    public boolean removeDeliveryPerson(DeliveryPerson dp) {
        if (dp == null || !this.getDeliveryPersons().containsKey(dp.getId())) {
            return false;
        }
        for (Delivery d : this.getDeliveries().values()) {
            if (!d.getDeliveryPerson().equals(dp)) continue;
            d.setDeliveryPerson(null);
        }
        return this.getDeliveryPersons().remove(dp.getId()) != null && dp.getArea().removeDelPerson(dp);
    }

    public boolean removeCustomer(Customer cust) {
        if (cust == null || !this.getCustomers().containsKey(cust.getId())) {
            return false;
        }
        return this.getCustomers().remove(cust.getId()) != null;
    }

    public boolean removeDish(Dish dish) {
        if (dish == null || !this.getDishes().containsKey(dish.getId())) {
            return false;
        }
        for (Delivery d : this.deliveries.values()) {
            if (d.isDelivered()) continue;
            if (d instanceof RegularDelivery) {
                RegularDelivery rd = (RegularDelivery)d;
                for (Order o : rd.getOrders()) {
                    o.removeDish(dish);
                }
                continue;
            }
            ExpressDelivery ed = (ExpressDelivery)d;
            ed.getOrder().removeDish(dish);
        }
        return this.getDishes().remove(dish.getId()) != null;
    }

    public boolean removeComponent(Component comp) {
        Dish removeDish;
        block4: {
            removeDish = null;
            if (comp != null && this.getComponenets().containsKey(comp.getId())) break block4;
            return false;
        }
        try {
            for (Dish d : this.getDishes().values()) {
                d.removeComponent(comp);
                if (!d.getComponenets().isEmpty()) continue;
                removeDish = d;
                throw new NoComponentsExceptions(d);
            }
        }
        catch (NoComponentsExceptions e) {
            MyFileLogWriter.println(e.getMessage());
            this.removeDish(removeDish);
        }
        return this.getComponenets().remove(comp.getId()) != null;
    }

    public boolean removeOrder(Order order) {
        if (order == null || !this.getOrders().containsKey(order.getId())) {
            return false;
        }
        if (this.getOrders().remove(order.getId()) != null) {
            if (order.getDelivery() instanceof RegularDelivery) {
                RegularDelivery rd = (RegularDelivery)order.getDelivery();
                return rd.removeOrder(order);
            }
            ExpressDelivery ed = (ExpressDelivery)order.getDelivery();
            return true;
        }
        return false;
    }

    public boolean removeDelivery(Delivery delivery) {
        if (delivery == null || !this.getDeliveries().containsKey(delivery.getId())) {
            return false;
        }
        if (delivery instanceof RegularDelivery) {
            RegularDelivery rd = (RegularDelivery)delivery;
            for (Order o : rd.getOrders()) {
                o.setDelivery(null);
            }
        } else {
            ExpressDelivery ed = (ExpressDelivery)delivery;
            ed.getOrder().setDelivery(null);
        }
        return this.getDeliveries().remove(delivery.getId()) != null && delivery.getArea().removeDelivery(delivery);
    }

    public boolean removeDeliveryArea(DeliveryArea oldArea, DeliveryArea newArea) {
        if (oldArea == null || newArea == null || !this.getAreas().containsKey(oldArea.getId()) || !this.getAreas().containsKey(newArea.getId())) {
            return false;
        }
        for (Neighberhood n : oldArea.getNeighberhoods()) {
            newArea.addNeighberhood(n);
        }
        for (Delivery d : oldArea.getDelivers()) {
            newArea.addDelivery(d);
        }
        for (DeliveryPerson dp : oldArea.getDelPersons()) {
            newArea.addDelPerson(dp);
        }
        for (DeliveryPerson dp : oldArea.getDelPersons()) {
            dp.setArea(newArea);
        }
        for (Delivery d : oldArea.getDelivers()) {
            d.setArea(newArea);
        }
        return this.getAreas().remove(oldArea.getId()) != null;
    }

    public Cook getRealCook(int id) {
        return this.getCooks().get(id);
    }

    public DeliveryPerson getRealDeliveryPerson(int id) {
        return this.getDeliveryPersons().get(id);
    }

    public Customer getCustomerByIdNumber(long id) {
        for (Map.Entry<Integer, Customer> e : this.customers.entrySet()) {
            if (e.getValue().getIdNumber() != id) continue;
            return e.getValue();
        }
        return null;
    }

    public Customer getRealCustomer(int id) {
        return this.getCustomers().get(id);
    }

    public Dish getRealDish(int id) {
        return this.getDishes().get(id);
    }

    public Component getRealComponent(int id) {
        return this.getComponenets().get(id);
    }

    public Order getRealOrder(int id) {
        return this.getOrders().get(id);
    }

    public Delivery getRealDelivery(int id) {
        return this.getDeliveries().get(id);
    }

    public DeliveryArea getRealDeliveryArea(int id) {
        return this.getAreas().get(id);
    }

    public Collection<Dish> getReleventDishList(Customer c) {
        ArrayList<Dish> dishList = new ArrayList<Dish>();
        if (!c.isSensitiveToGluten() && !c.isSensitiveToLactose()) {
            return this.getDishes().values();
        }
        for (Dish d : this.getDishes().values()) {
            boolean isValid = true;
            for (Component comp : d.getComponenets()) {
                if (comp == null) continue;
                if (c.isSensitiveToGluten() && c.isSensitiveToLactose()) {
                    if (!comp.isHasGluten() && !comp.isHasLactose()) continue;
                    isValid = false;
                    continue;
                }
                if (c.isSensitiveToGluten() && comp.isHasGluten()) {
                    isValid = false;
                    continue;
                }
                if (!c.isSensitiveToLactose() || !comp.isHasLactose()) continue;
                isValid = false;
            }
            if (!isValid) continue;
            dishList.add(d);
        }
        return dishList;
    }

    public void deliver(Delivery d) {
        d.setDelivered(true);
        if (d instanceof RegularDelivery) {
            RegularDelivery rd = (RegularDelivery)d;
            for (Order o : rd.getOrders()) {
                MyFileLogWriter.println(o + " had reached to Customer " + o.getCustomer());
            }
        } else {
            ExpressDelivery ed = (ExpressDelivery)d;
            MyFileLogWriter.println(ed.getOrder() + " had reached to Customer " + ed.getOrder().getCustomer());
        }
    }

    public ArrayList<Cook> GetCooksByExpertise(Expertise e) {
        ArrayList<Cook> cooks = new ArrayList<Cook>();
        for (Cook c : this.getCooks().values()) {
            if (!c.getExpert().equals((Object)e)) continue;
            cooks.add(c);
        }
        return cooks;
    }

    public TreeSet<Delivery> getDeliveriesByPerson(DeliveryPerson dp, int month) {
        TreeSet<Delivery> delivered = new TreeSet<Delivery>((Comparator<Delivery>)new /* Unavailable Anonymous Inner Class!! */);
        for (Delivery d : this.getDeliveries().values()) {
            if (!d.getDeliveryPerson().equals(dp) || d.getDeliveredDate().getMonthValue() != month) continue;
            delivered.add(d);
        }
        return delivered;
    }

    public HashMap<String, Integer> getNumberOfDeliveriesPerType() {
        HashMap<String, Integer> deliveriesPerType = new HashMap<String, Integer>();
        deliveriesPerType.put("Regular delivery", 0);
        deliveriesPerType.put("Express delivery", 0);
        for (Delivery d : this.getDeliveries().values()) {
            int amount;
            LocalDate today = LocalDate.now();
            if (d instanceof RegularDelivery && d.getDeliveredDate().getYear() == today.getYear()) {
                amount = deliveriesPerType.get("Regular delivery");
                deliveriesPerType.put("Regular delivery", amount + 1);
                continue;
            }
            if (d.getDeliveredDate().getYear() != today.getYear()) continue;
            amount = deliveriesPerType.get("Express delivery");
            deliveriesPerType.put("Express delivery", amount + 1);
        }
        return deliveriesPerType;
    }

    public double revenueFromExpressDeliveries() {
        HashSet<Customer> customers = new HashSet<Customer>();
        double amountOfPostages = 0.0;
        for (Delivery d : this.getDeliveries().values()) {
            if (!(d instanceof ExpressDelivery)) continue;
            ExpressDelivery ed = (ExpressDelivery)d;
            amountOfPostages += ed.getPostage();
            if (ed.getOrder() == null) continue;
            customers.add(ed.getOrder().getCustomer());
        }
        return amountOfPostages += (double)(30 * customers.size());
    }

    public LinkedList<Component> getPopularComponents() {
        HashMap<Component, Integer> componentsandAmount = new HashMap<Component, Integer>();
        for (Dish d : this.getDishes().values()) {
            for (Component c : d.getComponenets()) {
                Integer numOfComponent = (Integer)componentsandAmount.get(c);
                if (numOfComponent == null) {
                    numOfComponent = 0;
                }
                componentsandAmount.put(c, numOfComponent + 1);
            }
        }
        LinkedList<Component> popularComponents = new LinkedList<Component>();
        for (Component c : componentsandAmount.keySet()) {
            popularComponents.add(c);
        }
        popularComponents.sort((Comparator<Component>)new /* Unavailable Anonymous Inner Class!! */);
        return popularComponents;
    }

    public TreeSet<Dish> getProfitRelation() {
        TreeSet<Dish> profit = new TreeSet<Dish>((o1, o2) -> {
            if (o2.getPrice() / (double)o2.getTimeToMake() > o1.getPrice() / (double)o1.getTimeToMake()) {
                return 1;
            }
            if (o2.getPrice() / (double)o2.getTimeToMake() < o1.getPrice() / (double)o1.getTimeToMake()) {
                return -1;
            }
            return -1 * o1.getId().compareTo(o2.getId());
        });
        for (Dish d : this.getDishes().values()) {
            profit.add(d);
        }
        return profit;
    }

    public TreeSet<Delivery> createAIMacine(DeliveryPerson dp, DeliveryArea da, TreeSet<Order> orders) {
        TreeSet<Delivery> AIDecision = new TreeSet<Delivery>((Comparator<Delivery>)new /* Unavailable Anonymous Inner Class!! */);
        TreeSet<Order> toRegularDelivery = new TreeSet<Order>();
        if (orders.size() <= 2) {
            for (Order o : orders) {
                ExpressDelivery ed = new ExpressDelivery(dp, da, false, o, LocalDate.of(2021, 1, 1));
                AIDecision.add(ed);
            }
        } else {
            for (Order o : orders) {
                if (o.getCustomer().isSensitiveToGluten() || o.getCustomer().isSensitiveToLactose()) {
                    ExpressDelivery ed = new ExpressDelivery(dp, da, false, o, LocalDate.of(2021, 1, 1));
                    AIDecision.add(ed);
                    continue;
                }
                toRegularDelivery.add(o);
            }
            if (toRegularDelivery.size() < 2) {
                ExpressDelivery ed = new ExpressDelivery(dp, da, false, (Order)toRegularDelivery.first(), LocalDate.of(2021, 1, 1));
                AIDecision.add(ed);
            } else {
                RegularDelivery rd = new RegularDelivery(toRegularDelivery, dp, da, false, LocalDate.of(2021, 1, 1));
                AIDecision.add(rd);
            }
        }
        return AIDecision;
    }

    public void serialize() {
        try {
            FileOutputStream fileOut = new FileOutputStream("Rest.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            JOptionPane.showMessageDialog(null, "Data is saved");
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Restaurant deserialize() {
        Restaurant res = null;
        try {
            FileInputStream fileIn = new FileInputStream("Rest.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            res = (Restaurant)in.readObject();
            in.close();
            fileIn.close();
        }
        catch (IOException i) {
            JOptionPane.showMessageDialog(null, "Rest.ser not found!");
        }
        catch (ClassNotFoundException c) {
            JOptionPane.showMessageDialog(null, "Resturant class not found!");
            c.printStackTrace();
        }
        return res;
    }
}
