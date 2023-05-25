package model.exceptions;

public class AvailableProductExceptions extends InvalidBought{
    public String toString(){
        return super.toString()+"-Doesn't Available!";
    }
}
