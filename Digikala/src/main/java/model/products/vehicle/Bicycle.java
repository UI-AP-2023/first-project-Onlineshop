package model.products.vehicle;

public class Bicycle extends VehicleProduct {
    private BicycleType bicycleType;

    public Bicycle() {
    }

    public void setter(String name, double price,int numberOfAvailable,String nameOfCompany, BicycleType bicycleType) {
        super.setter(name, price, numberOfAvailable, nameOfCompany);
        this.bicycleType = bicycleType;
    }

    public BicycleType getBicycleType() {
        return this.bicycleType;
    }

    public String toString() {
        return super.toString() + "\nBicycle's type= " + this.getBicycleType();
    }
}
