package model.exceptions;

public class DiscountExceptions extends Throwable {
    public String toString(){
        return "Invalid code!\nTime of your code is finished or the capacity is full!";
    }
}
