package model.products;

public class FoodProduct extends Product {
    private String dateOfProduction;
    private String dateOfExpiration;

    public FoodProduct() {
    }

    public void setter(String name, double price,  int numberOfAvailable,String dateOfProduction, String dateOfExpiration) {
        super.setter(name, numberOfAvailable,price, Category.FOOD);
        this.dateOfProduction = dateOfProduction;
        this.dateOfExpiration = dateOfExpiration;
    }

    public String getDateOfProduction() {
        return this.dateOfProduction;
    }

    public String getDateOfExpiration() {
        return this.dateOfExpiration;
    }

    public String toString() {
        return super.toString() +"\nDate of production= " + this.getDateOfProduction() + "\nDate Of expiration= " + this.getDateOfExpiration();
    }
}
