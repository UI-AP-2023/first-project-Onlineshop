package model.products.digital;

public class SSD extends SavingInfoProduct {
    private String speedOfReading;
    private String speedOfWriting;

    public SSD() {
    }

    public void setter(String name, double price,  int numberOfAvailable, double weight, String sides, int limitation, String speedOfReading, String speedOfWriting) {
        super.setter(name, price, numberOfAvailable, weight, sides, limitation);
        this.speedOfReading = speedOfReading;
        this.speedOfWriting = speedOfWriting;
    }

    public String getSpeedOfReading() {
        return this.speedOfReading;
    }

    public String getSpeedOfWriting() {
        return this.speedOfWriting;
    }

    public String toString() {
        return super.toString() + "\nSpeed of reading= " + this.getSpeedOfReading() + "\nSpeed of Writing= " + this.getSpeedOfWriting();
    }
}

