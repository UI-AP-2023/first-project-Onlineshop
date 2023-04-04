package model.users;

import model.products.Comment;
import model.products.CommentSituation;

public class Request {
    private Customer customer;
    private String username;
    private String text;
    private Comment comment;
    private boolean acception;
    private double money;
    private String commentText;
    private CommentSituation commentSituation=CommentSituation.WAITING;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Request() {
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setter(Customer customer, String text) {
        this.customer = customer;
        this.text = text;
    }
    public void setter(String username, String text) {
        this.username = username;
        this.text = text;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
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
        return "Username= " + this.getUsername() + "\nText= " + this.getText()+"\nComment Text= "+this.getCommentText();
    }
}
