package controller;

import model.users.Request;
import model.users.Customer;
import static model.users.Admin.admin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class UserController {
    Customer customer = new Customer();
    Request request = new Request();

    boolean regexName(String username) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    public String setUsername(String username) {
        if (regexName(username)) {
            for (Customer c : admin.getCustomers()) {
                if (c.getUsername().equals(username)) {
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
            for (Customer c : admin.getCustomers()) {
                if (c.getPhoneNumber().equals(phoneNumber))
                    return "This phone number is already in the list!";
            }
            return null;
        }
        return "Phone number must be started with (09) and has 11 numbers!";
    }

    public String setEmail(String email) {
        if (regexEmail(email)) {
            for (Customer c : admin.getCustomers()) {
                if (c.getEmail().equals(email))
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
        customer.setter(username, email, phoneNumber, password);
        request("sign up request.", customer);
    }

    public void request(String text, Customer customer) {
        request.setter(customer, text);
        if (!admin.getRequests().contains(request)) {
            admin.getRequests().add(request);
        }

    }

    boolean checkSignupRequest(String username) {
        for (Request request : admin.getAcceptedRequest()) {
            if (request.getCustomer().getUsername().equals(username) && request.getText().equals("sign up request.")) {
                if (request.getAcception()) {
                    admin.getCustomers().add(request.getCustomer());
                    return true;
                }
            }
        }
        return false;
    }

    public String login(String username, String password) {
        if (checkSignupRequest(username)) {
            for (Customer customer : admin.getCustomers()) {
                if (customer.getUsername().equals(username)) {
                    if (customer.getPassword().equals(password)) {
                        return "You have been logged in successfully!";
                    }
                    return "Your password is incorrect!";
                }
                return "Your username is incorrect!";
            }
        }
        return "You haven't been signed up!";
    }

    public boolean changeInfo(Customer customer, String prePassword, String newPassword, String newPhoneNumber, String newEmail) {
        if (admin.getCustomers().contains(customer)) {
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
