package model.products.stationery;

import model.products.discount.DiscountCode;

public class Pencil extends StationeryProduct implements DiscountCode {
    private PencilType type;
    private int percent;

    public Pencil() {
    }

    public void setter(String name, double price, int numberOfAvailable, String country, PencilType type) {
        super.setter(name, price, numberOfAvailable, country);
        this.type = type;
    }

    public PencilType getType() {
        return this.type;
    }

    public String toString() {
        return super.toString() + "\nType= " + this.getType()+"\nDiscount: "+this.getPercent()+"%";
    }

    @Override
    public void addDiscount(int percent) {
        this.percent = percent;
        double newPrice = super.getPrice() * ((double) (100 - this.percent) / 100);
        super.setPrice(newPrice);
    }

    @Override
    public void removeDiscount(int percent) {
        this.percent = 0;
        double newPrice = (super.getPrice() * 100) / percent;
        super.setPrice(newPrice);
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getPercent() {
        return this.percent;
    }

}