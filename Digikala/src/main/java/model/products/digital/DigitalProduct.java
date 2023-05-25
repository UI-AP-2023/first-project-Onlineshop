package model.products.digital;

import model.products.Category;
import model.products.Product;
import model.products.discount.DiscountCode;

public abstract class DigitalProduct extends Product implements DiscountCode {
    private double weight;
    private String sides;
    private  int percent;

    DigitalProduct() {}

    void setter(String name, double price,  int numberOfAvailable, double weight, String sides) {
        super.setter(name, numberOfAvailable,price, Category.DIGITAL);
        this.weight = weight;
        this.sides = sides;
    }

    public double getWeight() {
        return this.weight;
    }

    public String getSides() {
        return this.sides;
    }

    public String toString() {
        return super.toString() + "\nWeight= " + this.getWeight() + "\nSides= " + this.getSides()+"\nDiscount: "+this.getPercent()+"%";
    }

    @Override
    public void addDiscount(int percent) {
         this.percent =percent;
         double newPrice=super.getPrice()*((double) (100 - this.percent) /100);
         super.setPrice(newPrice);
    }

    public  int getPercent() {
        return this.percent;
    }

    public  void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public void removeDiscount(int percent) {
        this.percent =0;
        double newPrice=(super.getPrice()*100)/percent;
        super.setPrice(newPrice);
    }
}
