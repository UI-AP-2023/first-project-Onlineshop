package model.products;

public abstract class DigitalProduct extends Product {
    private double weight;
    private String sides;

    DigitalProduct() {
    }

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
        return super.toString() + "\nWeight= " + this.getWeight() + "\nSides= " + this.getSides();
    }
}
