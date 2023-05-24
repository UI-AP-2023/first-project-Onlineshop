package model.products.commentScore;

import model.users.Customer;

public class Comment {
    private Customer customer;
    private long productId;
    private String text;
    private CommentSituation status;
    private boolean bought = false;

    public Comment() {
    }

    public void setter(Customer customer, String text, long productId) {
        this.customer = customer;
        this.text = text;
        this.productId = productId;
    }
    public void setter(long productId, String text){
        this.productId = productId;
        this.text = text;
    }

    public void setStatus(CommentSituation status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getProductId() {
        return this.productId;
    }

    public CommentSituation getStatus() {
        return this.status;
    }

    public boolean getBought() {
        return this.bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "text= " + this.getText();
    }
}


