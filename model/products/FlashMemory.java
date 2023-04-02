package model.products;

public class FlashMemory extends SavingInfoProduct {
    private String version;

    public FlashMemory() {
    }

    public void setter(String name, double price,  int numberOfAvailable, double weight, String sides, int limitation, String version) {
        super.setter(name, price, numberOfAvailable, weight, sides, limitation);
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    public String toString() {
        return super.toString() +"\nVersion= " + this.getVersion();
    }
}

