package model.products.vehicle;

public class Automobile extends VehicleProduct {
    private String engineVolume;
    private boolean automatic;

    public Automobile() {
    }

    public void setter(String name, double price, int numberOfAvailable, String nameOfCompany, String engineVolume, boolean automatic) {
        super.setter(name, price, numberOfAvailable, nameOfCompany);
        this.engineVolume = engineVolume;
        this.automatic = automatic;
    }

    public String getEngineVolume() {
        return this.engineVolume;
    }

    public boolean getAutomatic() {
        return this.automatic;
    }

    public String toString() {
        return super.toString() +"\nEngine volume= " + this.getEngineVolume() + "\nAutomatic= " + this.getAutomatic();
    }
}
