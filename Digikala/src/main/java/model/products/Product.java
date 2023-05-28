package model.products;

import model.products.commentScore.Comment;

import java.util.ArrayList;

abstract public class Product implements Comparable {
    private static long counter = 0;
    private long productID;
    private String name;
    private double price;
    private boolean isAvailable = false;
    private int numberOfAvailable;
    private float average;
    private Category category;
    private int numberOfProduct;
    private ArrayList<Comment> comments = new ArrayList<>();

    public Product() {
        counter++;
    }

    public void setter(String name, int numberOfAvailable, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.numberOfAvailable = numberOfAvailable;
        this.productID=counter;
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
        return this.productID;
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
    public int compareTo(Object obj) {

        Product product = (Product) obj;

        if (this.name.compareTo(product.name) > 0)
            return 10;
        else if (this.name.compareTo(product.name) < 0)
            return -10;
        else {

            if (this.average < product.average)
                return -10;
            else if (this.average > product.average)
                return 10;
            else {
                if (this.price < product.price)
                    return -10;
                else if (this.price > product.price)
                    return +10;

                else {
                    if (!this.isAvailable && product.isAvailable)
                        return -10;
                    else if (this.isAvailable && !product.isAvailable)
                        return +10;
                }
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\nProduct's ID: " + this.getId() +
                "\nPrice:  " + this.getPrice() + "\nCategory: " + this.getCategory()
                + "\nAvailable: " + this.isAvailable() + "\nAverage: " + this.getAverage() +
                "\nNumber of available: " + this.getNumberOfAvailable()
                + "\nNumber of products in your basket: " + this.getNumberOfProduct();
    }
}


