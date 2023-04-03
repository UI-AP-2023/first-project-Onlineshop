package model.users;

import model.products.Product;

import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Request> requests = new ArrayList<>();
    private ArrayList<Request> acceptedRequest = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    private static  Admin admin=new Admin("admin", "admin");

    private Admin(String username, String password) {
        super(username, password);
    }

    public static Admin getAdmin(String name, String password) {
        if (admin == null) {
            admin = new Admin(name,password);}
        return admin;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }
    public ArrayList<Request> getRequests() {
        return this.requests;
    }
    public ArrayList<Request> getAcceptedRequest(){return this.acceptedRequest;}
    public ArrayList<Customer> getCustomers(){return this.customers;}

}