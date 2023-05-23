package model.products;

import java.time.LocalDate;

public class Discount {
    private String code;
    private int percent=0;
    private LocalDate limitDate;
    private int capacity=0;
    private static long counter=-1;
    private long ID;

    public Discount(int percent, LocalDate limitDate, int capacity){
        this.capacity=capacity;
        this.limitDate=limitDate;
        this.percent=percent;
        counter++;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    public void setCapacity() {
        this.capacity--;
    }

    public int getPercent() {
        return this.percent;
    }

    public LocalDate getLimitDate() {
        return this.limitDate;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public  long getID() {
        this.ID=counter;
        return this.ID;
    }

    @Override
    public String toString(){
        return "Code:"+getCode()+"\nPercent: "+getPercent()+"\nCapacity: "+getCapacity()+"\nDate: "+getLimitDate();
    }
}
