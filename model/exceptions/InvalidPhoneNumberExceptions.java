package model.exceptions;

public class InvalidPhoneNumberExceptions extends InvalidLogin {
    public String toString() {
        return super.toString() + "-Invalid Phone Number!\nIf you sure your phone number started with (09) and has 11 numbers,it has been used by someone else!";
    }
}
