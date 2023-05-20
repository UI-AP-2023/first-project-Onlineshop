package model.products;

public class Pencil extends StationeryProduct {
    private PencilType type;

    public Pencil() {
    }

    public void setter(String name, double price,  int numberOfAvailable, String country, PencilType type) {
        super.setter(name, price, numberOfAvailable,country);
        this.type = type;
    }

    public PencilType getType() {
        return this.type;
    }

    public String toString() {
        return super.toString() + "\nType= " + this.getType();
    }
}

