package model.products;

public abstract class VehicleProduct extends Product {
    private String nameOfCompany;

    VehicleProduct() {
    }

    void setter(String name, double price,  int numberOfAvailable, String nameOfCompany) {
        super.setter(name, numberOfAvailable, price,Category.VEHICLE);
        this.nameOfCompany = nameOfCompany;
    }

    public String getNameOfCompany() {
        return this.nameOfCompany;
    }

    public String toString() {
        return super.toString() +"\nCompany= " + this.getNameOfCompany();
    }
}
