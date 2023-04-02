package model.users;

import model.products.Product;

import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Request> requests = new ArrayList<>();
    private ArrayList<Request> acceptedRequest = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    public static final Admin admin = new Admin("admin", "admin");

    private Admin(String username, String password) {
        super(username, password);
    }

    String getUserName() {
        return super.getUsername();
    }


    public String getPassword() {
        return super.getPassword();
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<Request> getRequests() {
        return this.requests;
    }
    public ArrayList<Request> getAcceptedRequest(){
        return this.acceptedRequest;
    }
    public ArrayList<Customer> getCustomers(){return this.customers;}

    @Override
    public String toString() {
        return "Username= " + this.getUserName() + "\nPassword= " + this.getPassword();
    }
}
