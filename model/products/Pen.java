package model.products;

public class Pen extends StationeryProduct {
    private Color color;

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
        return super.toString() +"\nColor= " + this.getColor();
    }
}

