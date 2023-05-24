package controller.user;

import controller.admin.AdminController;
import model.exceptions.InvalidLogin;
import model.exceptions.AvailableProductExceptions;
import model.products.commentScore.Comment;
import model.products.commentScore.Score;
import model.products.ShoppingFactor;
import model.users.Admin;
import model.users.Customer;
import model.users.Request;

import java.util.ArrayList;

public class ScoreController {
    public String scoreProduct(long productId, float customerScore, Customer customer) throws InvalidLogin, AvailableProductExceptions {
        AdminController adminController = new AdminController();
        ProductController productController = new ProductController();
        if(customer==null) {throw new InvalidLogin();}
        if (adminController.showProducts().contains(productController.makeProduct(productId))) {
            for (ShoppingFactor factor : customer.getShoppingHistory()) {
                if (factor.getBoughtProducts().contains(productController.makeProduct(productId))) {
                    Score score = new Score();
                    productController.makeProduct(productId).setAverage(customerScore / score.getCounter());
                    score.setter(customer, customerScore, productController.makeProduct(productId));
                    return "It was successful!";
                }
            }
        }
        throw new AvailableProductExceptions();
    }

    public String commentRequest(long productId, String text, Customer customer) {
        Comment comment = new Comment();
        ProductController productController =new ProductController();
        if(customer == null){
            Customer unknownCustomer = new Customer();
            unknownCustomer.setter("unknown","unknown@gmail.com","09000000000","unknown");
        }
        if (customer != null) {
            for (ShoppingFactor factor : customer.getShoppingHistory()) {
                if (factor.getBoughtProducts().contains(productController.makeProduct(productId))) {
                    comment.setBought(factor.getBoughtProducts().contains(productController.makeProduct(productId)));
                }
            }
        }
        comment.setter(productId,text);
        Request request = new Request();
        request.setter(customer, "Comment request");
        if(customer!=null){
        request.setUsername(customer.getUsername());}
            request.setComment(comment);
            request.setCommentText(text);
        Admin.getAdmin().getRequests().add(request);
            return "Your request has been sent!";
    }

    public ArrayList<Comment> showComments(long productId) {
        ProductController productController = new ProductController();
        return productController.makeProduct(productId).getComments();
    }
}
