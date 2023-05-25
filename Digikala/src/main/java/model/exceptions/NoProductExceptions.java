package model.exceptions;

public class NoProductExceptions extends InvalidBought{
    public String toString(){
        return super.toString()+"-Doesn't Exist!";
    }
}
