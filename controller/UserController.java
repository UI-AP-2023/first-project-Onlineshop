package controller;

import model.products.*;
import model.users.Request;
import model.users.Customer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserController {

    boolean regexName(String username) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    public String setUsername(String username) {
        if (regexName(username)) {
            AdminController adminController = new AdminController();
            for (Customer customer : adminController.showCustomers()) {
                if (customer.getUsername().equals(username)) {
                    return "This username is already in the list!";
                }
            }
            return null;
        }
        return "Username just should have letters and numbers!";
    }


    boolean regexPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^09\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.find();
    }

    boolean regexEmail(String email) {
        Pattern pattern = Pattern.compile("^\\w+@(yahoo|gmail)\\.com$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public String setPhoneNumber(String phoneNumber) {
        if (regexPhoneNumber(phoneNumber)) {
            AdminController adminController = new AdminController();
            for (Customer customer : adminController.showCustomers()) {
                if (customer.getPhoneNumber().equals(phoneNumber))
                    return "This phone number is already in the list!";
            }
            return null;
        }
        return "Phone number must be started with (09) and has 11 numbers!";
    }

    public String setEmail(String email) {
        if (regexEmail(email)) {
            AdminController adminController = new AdminController();
            for (Customer customer : adminController.showCustomers()) {
                if (customer.getEmail().equals(email))
                    return "This email is already in the list!";
            }
            return null;
        }
        return "Email is invalid!";
    }

    public boolean setPassword(String password) {
        Pattern pattern = Pattern.compile("(\\S){8,}");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public void signupRequest(String username, String password, String phoneNumber, String email) {
        Customer customer = new Customer();
        customer.setter(username, email, phoneNumber, password);

        Request request = new Request();
        request.setter(customer, "sign up request.");
        AdminController adminController = new AdminController();
        if (!adminController.showRequests().contains(request)) {
            adminController.showRequests().add(request);
        }
    }


    public Customer login(String username, String password) {
        AdminController adminController = new AdminController();
        for (Customer customer : adminController.showCustomers()) {
            if (customer.getUsername().equals(username)) {
                if (customer.getPassword().equals(password)) {
                    return customer;
                }
            }
        }
        return null;
    }


    public boolean changeInfo(Customer customer, String prePassword, String newPassword, String newPhoneNumber, String newEmail) {
        AdminController adminController = new AdminController();
        if (adminController.showCustomers().contains(customer)) {
            if (customer.getPassword().equals(prePassword)) {
                customer.setPassword(newPassword);
                customer.setPhoneNumber(newPhoneNumber);
                customer.setEmail(newEmail);
                return true;
            }
        }
        return false;
    }

}




