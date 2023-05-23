package controller.user;

import controller.admin.AdminController;
import model.exceptions.*;
import model.users.Admin;
import model.users.Request;
import model.users.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserController {

    boolean regexName(String username) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }
    public String setUserName(String username) throws InvalidUserName {
        if (regexName(username)) {
            AdminController adminController = new AdminController();
            for (Customer customer : adminController.showCustomers()) {
                if (customer.getUsername().equals(username)) {
                    throw new InvalidUserName();
                }
            }
            return null;
        }
        throw new InvalidUserName();
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

    public String setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberExceptions {
        if (regexPhoneNumber(phoneNumber)) {
            AdminController adminController = new AdminController();
            for (Customer customer : adminController.showCustomers()) {
                if (customer.getPhoneNumber().equals(phoneNumber))
                    throw new InvalidPhoneNumberExceptions();
            }
            return null;
        }
        throw new InvalidPhoneNumberExceptions();
    }

    public String setEmail(String email) throws InvalidEmailExceptions {
        if (regexEmail(email)) {
            AdminController adminController = new AdminController();
            for (Customer customer : adminController.showCustomers()) {
                if (customer.getEmail().equals(email))
                   throw new InvalidEmailExceptions();
            }
            return null;
        }
        throw new InvalidEmailExceptions();
    }

    public boolean setPassword(String password) throws InvalidPassword {
        Pattern pattern = Pattern.compile("(\\S){8,}");
        Matcher matcher = pattern.matcher(password);
        if(!matcher.find()){
            throw new InvalidPassword();
        }
        return matcher.find();
    }

    public void signupRequest(String username, String password, String phoneNumber, String email) {
        Customer customer = new Customer();
        customer.setter(username, email, phoneNumber, password);

        Request request = new Request();
        request.setter(customer, "sign up request.");
        request.setUsername(username);
        Admin.getAdmin().getRequests().add(request);
    }


    public Customer login(String username, String password) throws InvalidLogin {
        AdminController adminController = new AdminController();
        for (Customer customer : adminController.showCustomers()) {
            if (customer.getUsername().equals(username)) {
                if (customer.getPassword().equals(password)) {
                    return customer;
                }
            }
        }
        throw new InvalidLogin();
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




