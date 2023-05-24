package model.products.commentScore;


import model.products.Product;
import model.users.Customer;

public class Score {
    private Customer customer;
    private float score;
    private Product product;
    private int counter = 0;

    public Score() {
        counter++;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setter(Customer customer, float score, Product product) {
        this.customer = customer;
        this.score = score;
        this.product = product;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public float getScore() {
        return this.score;
    }

    public Product getProduct() {
        return this.product;
    }

    @Override
    public String toString() {
        return "Username= " + this.getCustomer().getUsername() + "\nProduct= " + this.getProduct().getName() + "ID= " + this.getProduct().getId() + "\nScore= " + this.getScore();
    }
}

