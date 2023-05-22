package model.exceptions;

public class InvalidUserName extends InvalidLogin{
    public String toString(){
        return super.toString()+"-Invalid username!\nIf you sure your username contains words or numbers,it has been used by someone else!";
    }
}
