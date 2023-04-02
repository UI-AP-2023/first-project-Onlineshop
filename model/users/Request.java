package model.users;

import model.products.CommentSituation;

public class Request {
    private Customer customer;
    private String text;
    private boolean acception;
    private CommentSituation commentSituation=CommentSituation.WAITING;

    public Request() {
    }

    public void setter(Customer customer, String text) {
        this.customer = customer;
        this.text = text;
    }

    public void setAcception(boolean acception) {
        this.acception = acception;
    }
    public void setCommentSituation(CommentSituation commentSituation) {
        this.commentSituation = commentSituation;
    }
    public CommentSituation getCommentSituation() {
        return this.commentSituation;
    }

    public boolean getAcception() {
        return this.acception;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return "Username= " + this.getCustomer().getUsername() + "\nText= " + this.getText();
    }
}
