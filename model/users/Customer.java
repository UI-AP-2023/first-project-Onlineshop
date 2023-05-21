package model.users;

import controller.user.Discount;
import model.products.Product;
import model.products.ShoppingFactor;

import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Product> shoppingbasket = new ArrayList<>();
    private ArrayList<ShoppingFactor> shoppingHistory = new ArrayList<>();
    private ArrayList<Discount> discounts=new ArrayList<>();
    private double property = 0;

    public Customer() {
    }

    public ArrayList<Discount> getDiscounts() {
        return this.discounts;
    }

    public void setter(String username, String email, String phoneNumber, String password) {
        super.setter(username, email, phoneNumber, password);
    }

    public ArrayList<Product> getShoppingbasket() {
        return this.shoppingbasket;
    }

    public ArrayList<ShoppingFactor> getShoppingHistory() {
        return this.shoppingHistory;
    }

    public Double getProperty() {
        return this.property;
    }

    public void addProperty(Double property) {
        this.property += property;
    }

    public void setProperty(double property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return super.toString() + "\nProperty= $ " + this.property;
    }

}
