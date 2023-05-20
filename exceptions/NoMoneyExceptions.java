package exceptions;

public class NoMoneyExceptions extends InvalidBought{
    public String toString(){
        return super.toString()+"-No Property!";
    }
}
