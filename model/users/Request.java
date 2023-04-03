package model.users;

import model.products.Comment;
import model.products.CommentSituation;

public class Request {
    private Customer customer;
    private String text;
    private Comment comment;
    private boolean acception;
    private double money;
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

    public Comment getComment() {
        return this.comment;
    }
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Username= " + this.getCustomer().getUsername() + "\nText= " + this.getText();
    }
}
