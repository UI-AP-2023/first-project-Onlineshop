package model.products;

public abstract class StationeryProduct extends Product {
    private String country;

    StationeryProduct() {
    }

    void setter(String name, double price, int numberOfAvailable, String country) {
        super.setter(name,numberOfAvailable,price, Category.STATIONERY);
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public String toString() {
        return super.toString() +"\nCountry= " + this.getCountry();
    }

}
