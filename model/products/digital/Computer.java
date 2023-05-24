package model.products.digital;

public class Computer extends DigitalProduct {
    private String cpu;
    private String ramLimitation;

    public Computer() {
    }

    public void setter(String name, double price,int numberOfAvailable, double weight, String sides, String cpu, String ramLimitation) {
        super.setter(name, price, numberOfAvailable, weight, sides);
        this.cpu = cpu;
        this.ramLimitation = ramLimitation;
    }

    public String getCpu() {
        return this.cpu;
    }

    public String getRamLimitation() {
        return this.ramLimitation;
    }

    public String toString() {
        return super.toString() +"\nCPU= " + this.getCpu() + "\nRAM= " + this.getRamLimitation();
    }

}

