package model.products;

import java.time.LocalDate;
import java.util.ArrayList;

public class ShoppingFactor {
    private static long id = 0;
    private LocalDate date;
    private double cost;
    private ArrayList<Product> boughtProducts = new ArrayList<>();

    public ShoppingFactor() {
        id++;
    }

    public void setter(LocalDate date, double cost) {
        this.date = date;
        this.cost = cost;
    }

    public long getId() {
        return ShoppingFactor.id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    public ArrayList<Product> getBoughtProducts() {
        return boughtProducts;
    }

    @Override
    public String toString() {
        return  "ID= " + this.getId() + "\nDate= " + this.getDate() + "Cost= " + this.getCost() + "\nBought products= ";
    }

}
