package com.example.digikala;

import controller.user.ProductController;
import controller.user.ScoreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.exceptions.AvailableProductExceptions;
import model.exceptions.InvalidLogin;
import model.products.Product;
import model.products.digital.Computer;
import model.products.digital.FlashMemory;
import model.products.digital.SSD;
import model.products.food.FoodProduct;
import model.products.stationery.Notebook;
import model.products.stationery.Pen;
import model.products.stationery.Pencil;
import model.products.vehicle.Automobile;
import model.products.vehicle.Bicycle;

import java.io.IOException;
import java.util.Objects;

public class ProductPage {
    public static Product product;

    @FXML
    private TextField IDID;

    @FXML
    private TextField commentID;

    @FXML
    private Text goodLuckID;


    @FXML
    private Text productID;

    @FXML
    private TextField scoreID;

    @FXML
    private Text commentsID;


    @FXML
    void onMoved(MouseEvent event) {
        showProduct(ProductPage.product.getId());
    }


    @FXML
    void onBackBottomClick(MouseEvent event) throws IOException {
        newPage(event, "viewShop", "Online Shop!!");
    }

    @FXML
    void onCloseBottomClick(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void onSearchClicked(MouseEvent event) {
        showProduct(Long.parseLong(IDID.getText()));
    }

    ScoreController scoreController = new ScoreController();

    public void newPage(MouseEvent event, String fxml, String title) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml + "-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 1535, 835);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    void showProduct(long productId) {
        ProductController productController = new ProductController();
        product=productController.makeProduct(productId);
        if (productController.makeProduct(productId) instanceof Automobile) {
            productID.setText(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof Bicycle) {
            productID.setText(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof Notebook) {
            productID.setText(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof SSD) {
            productID.setText(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof Pen) {
            productID.setText(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof Pencil) {
            productID.setText(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof Computer) {
            productID.setText(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof FoodProduct) {
            productID.setText(productController.makeProduct(productId).toString());
        } else if (productController.makeProduct(productId) instanceof FlashMemory) {
            productID.setText(productController.makeProduct(productId).toString());
        }

            commentsID.setText(scoreController.showComments(productId).get(commentCounter).toString());
            commentsID.setVisible(true);
    }

    @FXML
    void onPre(MouseEvent event) {
        commentCounter--;
        showProduct(product.getId());
    }

    @FXML
    void onNext(MouseEvent event) {
        for(int i=-1;i<scoreController.showComments(product.getId()).size()-1;i++)
        {commentCounter++;}
        showProduct(product.getId());
    }

    static int commentCounter;

    @FXML
    void onCommentSubmit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText( scoreController.commentRequest(product.getId(), commentID.getText(), UserPage.onlineCustomer));
        alert.show();
    }

    @FXML
    void onScoreSubmit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            scoreController.scoreProduct(product.getId(), Float.parseFloat(scoreID.getText()), UserPage.onlineCustomer);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("It was successful!");
            alert.show();
        } catch (AvailableProductExceptions availableProductExceptions) {
            alert.setContentText(availableProductExceptions.toString());
            alert.show();
        } catch (InvalidLogin invalidLogin) {
            alert.setContentText(invalidLogin.toString());
            alert.show();
        } finally {
            goodLuckID.setVisible(true);
        }

    }

    @FXML
    void onAddToBasket(MouseEvent event) {
        UserPage userPage = new UserPage();
        userPage.counter = this.counter;
        userPage.onAdd(event);
    }

    private int counter = 0;

    @FXML
    void onPlusButton(MouseEvent event) {
        this.counter++;
    }
}
