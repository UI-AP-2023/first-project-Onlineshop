package model.exceptions;

public class InvalidPhoneNumberExceptions extends InvalidLogin {
    public String toString() {
        return super.toString() + "-Invalid Phone Number!";
    }
}
