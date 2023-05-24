package model.products.stationery;

import model.products.discount.DiscountCode;

public class Pen extends StationeryProduct implements DiscountCode {
    private Color color;
    private int percent;

    public Pen() {
    }

    public void setter(String name, double price,  int numberOfAvailable, String country, Color color) {
        super.setter(name, price,numberOfAvailable, country);
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public String toString() {
        return super.toString() +"\nColor= " + this.getColor()+"\nDiscount: "+this.getPercent()+"%";
    }

    @Override
    public void addDiscount(int percent) {
        this.percent =percent;
        double newPrice=super.getPrice()*((double) (100 - this.percent) /100);
        super.setPrice(newPrice);
    }

    @Override
    public void removeDiscount(int percent) {
        this.percent =0;
        double newPrice=(super.getPrice()*100)/percent;
        super.setPrice(newPrice);
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getPercent() {
        return this.percent;
    }
}