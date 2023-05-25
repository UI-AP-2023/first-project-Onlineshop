package model.products.digital;

public abstract class SavingInfoProduct extends DigitalProduct {
    private int limitation;

    SavingInfoProduct() {
    }

    public void setter(String name, double price, int numberOfAvailable, double weight, String sides, int limitation) {
        this.limitation = limitation;
        super.setter(name, price, numberOfAvailable, weight, sides);
    }

    public int getLimitation() {
        return this.limitation;
    }

    public String toString() {
        return super.toString() + "\nLimitation= " + this.getLimitation();
    }
}
