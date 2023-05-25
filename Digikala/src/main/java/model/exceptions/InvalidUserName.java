package model.exceptions;

public class InvalidUserName extends InvalidLogin{
    public String toString(){
        return super.toString()+"-Invalid username!";
    }
}
