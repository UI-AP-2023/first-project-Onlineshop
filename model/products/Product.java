package model.products;

import java.util.ArrayList;

abstract public class Product {
    private static long productId = 0;
    private String name;
    private double price;
    private boolean isAvailable=false;
    private int numberOfAvailable;
    private float average;
    private Category category;
    private int numberOfProduct;
    private ArrayList<Comment> comments = new ArrayList<>();

    Product() {
        productId++;
    }

    void setter(String name, int numberOfAvailable, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.numberOfAvailable = numberOfAvailable;
        if (this.numberOfAvailable > 0) {
            setIsAvailable(true);
        }
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public int getNumberOfProduct() {
        return this.numberOfProduct;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public float getAverage() {
        return this.average;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public Category getCategory() {
        return this.category;
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public long getId() {
        return Product.productId;
    }

    public int getNumberOfAvailable() {
        return this.numberOfAvailable;
    }

    public void setNumberOfAvailable(int numberOfAvailable) {
        this.numberOfAvailable = numberOfAvailable;
        if (this.numberOfAvailable > 0) {
            setIsAvailable(true);
        }
    }

    public String getBaseInfo() {
        return "Name= " + this.getName()
                + "\nProduct's ID= " + this.getId()
                + "\nPrice=  " + this.getPrice()
                + "\nAvailable= " + this.isAvailable()
                + "\nAverage= " + this.getAverage();
    }

    @Override
    public String toString() {
        return "Name= " + this.getName() + "\nProduct's ID= " + this.getId() +
                "\nPrice=  " + this.getPrice() + "\nCategoty= " + this.getCategory()
                + "\nAvailable= " + this.isAvailable() + "\nAverage= " + this.getAverage() +
                "\nNumber of available= " + this.getNumberOfAvailable()
                + "\nNumber of products in your basket= " + this.getNumberOfProduct();
    }
}


