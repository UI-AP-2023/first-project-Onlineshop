package model.exceptions;

public class InvalidPassword extends InvalidLogin{
    public String toString() {
        return super.toString() + "-Invalid Password!\nYour password must be more than 7 elements!";
    }
}
