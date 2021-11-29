package entity;

import java.util.ArrayList;

public class Order {

    private String customerName;
    
    private ArrayList<Fruit> fruits = new ArrayList<>();

    public Order(String customerName) {
        this.customerName = customerName;
    }

    public Order(String customerName, ArrayList<Fruit> fruits) {
        this.customerName = customerName;
        this.fruits.addAll(fruits);
       
    }

    public Order() {

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<Fruit> fruits) {
        this.fruits = fruits;
    }

    
    

}
