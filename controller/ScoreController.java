package controller;

import model.products.Comment;
import model.products.Score;
import model.products.ShoppingFactor;
import model.users.Customer;
import model.users.Request;

import java.util.ArrayList;

public class ScoreController {
    public boolean scoreProduct(long productId, float customerScore, Customer customer) {
        AdminController adminController = new AdminController();
        ProductController productController = new ProductController();
        if (adminController.showProducts().contains(productController.makeProduct(productId))) {
            for (ShoppingFactor factor : customer.getShoppingHistory()) {
                if (factor.getBoughtProducts().contains(productController.makeProduct(productId))) {
                    Score score = new Score();
                    productController.makeProduct(productId).setAverage(customerScore / score.getCounter());
                    score.setter(customer, customerScore, productController.makeProduct(productId));
                    return true;
                }
            }
        }
        return false;
    }

    public String commentRequest(long productId, String text, Customer customer) {
        Comment comment = new Comment();
        ProductController productController =new ProductController();
        for (ShoppingFactor factor : customer.getShoppingHistory()) {
            if (factor.getBoughtProducts().contains(productController.makeProduct(productId))) {
                comment.setBought(factor.getBoughtProducts().contains(productController.makeProduct(productId)));
            }
        }
        comment.setter(customer, text, productId);
        Request request = new Request();
        request.setter(customer, "Comment request");
        AdminController adminController = new AdminController();
        if (!adminController.showRequests().contains(request)) {
            request.setComment(comment);
            adminController.showRequests().add(request);
            return "Your request has been sent!";
        }
        return null;
    }

    public ArrayList<Comment> showComments(long productId) {
        ProductController productController = new ProductController();
        return productController.makeProduct(productId).getComments();
    }
}
