package exceptions;

public class InvalidEmailExceptions extends InvalidLogin{
    public String toString(){
        return super.toString()+"-Invalid Email!";
    }
}
