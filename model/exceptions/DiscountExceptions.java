package model.exceptions;

public class DiscountExceptions extends Throwable {
    public String toString(){
        return "Invalid code!\nIf you sure that you entered correctly,time of your code is finished or the capacity is full!";
    }
}
