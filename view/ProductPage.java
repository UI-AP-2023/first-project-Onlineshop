package view;


import controller.user.ProductController;
import controller.user.ScoreController;
import model.exceptions.AvailableProductExceptions;
import model.exceptions.InvalidLogin;
import model.products.commentScore.Comment;
import model.products.digital.Computer;
import model.products.digital.FlashMemory;
import model.products.digital.SSD;
import model.products.food.FoodProduct;
import model.products.stationery.Notebook;
import model.products.stationery.Pen;
import model.products.stationery.Pencil;
import model.products.vehicle.Automobile;
import model.products.vehicle.Bicycle;
import model.users.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductPage {
    private Scanner scanner;


    ProductController productController = new ProductController();
    ScoreController scoreController = new ScoreController();
    ShopPage shopPage = new ShopPage();

    public ProductPage() {
        scanner = new Scanner(System.in);
    }

    public void visitProduct(long productId, Customer customer) {

        showProduct(productId);

        showComments(productId);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("    1.Add comment.");
        System.out.println("    2.Add score.");
        System.out.println("    3.Exit.");
        System.out.println("-------------------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            visitProduct(productId,customer);
        }
        switch (answer) {
            case 1 -> {
                addComment(productId, customer);
                visitProduct(productId, customer);
            }
            case 2 -> {
                addScore(productId, customer);
                visitProduct(productId, customer);
            }
            case 3 -> shopPage.shopMenu();
            default -> visitProduct(productId, customer);
        }
    }

    void showProduct(long productId) {
        if (productController.makeProduct(productId) instanceof Automobile) {
            System.out.println(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof Bicycle) {
            System.out.println(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof Notebook) {
            System.out.println(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof SSD) {
            System.out.println(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof Pen) {
            System.out.println(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof Pencil) {
            System.out.println(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof Computer) {
            System.out.println(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof FoodProduct) {
            System.out.println(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof FlashMemory) {
            System.out.println(productController.makeProduct(productId).toString());
        }
    }

    void showComments(long productId) {
        System.out.println("Comments:");
        for (Comment comment : scoreController.showComments(productId)) {
            System.out.println(comment.toString());
        }
    }


    void addComment(long productId, Customer customer) {
        System.out.println("-------------------------------------------------------------------------");
        scanner.nextLine();
        System.out.println("Please enter your comment.");
        String comment = scanner.nextLine();
        System.out.println(scoreController.commentRequest(productId, comment, customer));
    }

    void addScore(long productId, Customer customer) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Please enter your score.");
        float score =0;
        try {
            score = scanner.nextFloat();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            addScore(productId,customer);
        }
        System.out.println("-------------------------------------------------------------------------");
        try {
            System.out.println(scoreController.scoreProduct(productId, score, customer));
        } catch (AvailableProductExceptions availableProductExceptions) {
            System.out.println(availableProductExceptions.toString());
        } catch (InvalidLogin invalidLogin) {
            System.out.println(invalidLogin.toString());
        }finally {
            System.out.println("Good Luck!");
        }
    }


}
