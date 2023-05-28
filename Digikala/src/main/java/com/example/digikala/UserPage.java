package com.example.digikala;

import controller.user.BasketController;
import controller.user.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.exceptions.*;
import model.products.Product;
import model.products.discount.Discount;
import model.users.Customer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Objects;

public class UserPage {

    public static Customer onlineCustomer;
    @FXML
    private AnchorPane anchorPanBuy;

    @FXML
    private AnchorPane anchorPanDel;

    @FXML
    private AnchorPane anchorPaneAdd;

    @FXML
    private AnchorPane anchorPaneCircle;

    @FXML
    private AnchorPane anchorPaneEdit;

    @FXML
    private Text goodLuckID;

    @FXML
    private TextField codesID;

    @FXML
    private TextField delProductID;

    @FXML
    private Text discountCodeID;

    @FXML
    private Text infoID;

    @FXML
    private TextField newEmailID;

    @FXML
    private TextField newPassID;

    @FXML
    private TextField newPhonenumberID;

    @FXML
    private TextField prePassID;

    @FXML
    private TextField productID;

    public int counter = 0;

    @FXML
    void onPlusButton(MouseEvent event) {
        this.counter++;
    }

    @FXML
    void onBack(MouseEvent event) throws IOException {
        newPage(event, "hello", "Main Page!");
    }

    @FXML
    void onClose(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void onDelSumitButton(MouseEvent event) {
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        goodLuckID.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            if (Long.parseLong(delProductID.getText()) < 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException inputMismatchException) {
            alert.setContentText(inputMismatchException.toString());
            alert.show();
        }
        try {
            BasketController basketController = new BasketController();
            basketController.removeProductFromBasket(Long.parseLong(delProductID.getText()), onlineCustomer);
            this.counter = 0;
        } catch (InvalidLogin invalidLogin) {
            alert.setContentText(invalidLogin.toString());
            alert.show();
        } finally {
            goodLuckID.setVisible(true);
        }
    }

    @FXML
    void onEditSubmitButton(MouseEvent event) {
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        goodLuckID.setVisible(false);
        UserController userController = new UserController();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            userController.setPassword(newPassID.getText());
            userController.setPhoneNumber(newPhonenumberID.getText());
            userController.setEmail(newEmailID.getText());
            userController.changeInfo(onlineCustomer, prePassID.getText(), newPassID.getText(), newPhonenumberID.getText(), newEmailID.getText());
        } catch (InvalidInformationExceptions invalidInformationExceptions) {
            if (invalidInformationExceptions instanceof InvalidPassword) {
                alert.setContentText(invalidInformationExceptions.toString());
                alert.show();
            } else if (invalidInformationExceptions instanceof InvalidPhoneNumberExceptions) {
                alert.setContentText(invalidInformationExceptions.toString());
                alert.show();
            } else if (invalidInformationExceptions instanceof InvalidEmailExceptions) {
                alert.setContentText(invalidInformationExceptions.toString());
                alert.show();
            } else {
                alert.setContentText(invalidInformationExceptions.toString());
                alert.show();
            }
        } finally {
            goodLuckID.setVisible(true);
        }
    }

    @FXML
    void onAddSubmitButton(MouseEvent event) {
        goodLuckID.setVisible(false);
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            if (Long.parseLong(productID.getText()) < 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException inputMismatchException) {
            alert.setContentText(inputMismatchException.toString());
            alert.show();
        }
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText("ID: " + Long.parseLong(productID.getText()) + "\nNumber of products :" + this.counter + "\nIf It's false delete it!");
        alert.show();

        try {
            BasketController basketController = new BasketController();
            basketController.addProductToBasket(Long.parseLong(productID.getText()), this.counter, UserPage.onlineCustomer);
            this.counter = 0;
        } catch (AvailableProductExceptions availableProductExceptions) {
            alert.setContentText(availableProductExceptions.toString());
            alert.show();
        } catch (InvalidLogin invalidLogin) {
            alert.setContentText(invalidLogin.toString());
            alert.show();
        } catch (NoProductExceptions noProductExceptions) {
            alert.setContentText(noProductExceptions.toString());
            alert.show();
        } finally {
            goodLuckID.setVisible(true);
        }
    }

    @FXML
    void onAdd(MouseEvent event) {
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        infoID.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        anchorPaneAdd.setVisible(false);
        anchorPanBuy.setVisible(false);
        anchorPanDel.setVisible(false);
        anchorPaneEdit.setVisible(false);
        if (UserPage.onlineCustomer != null) {
            anchorPaneAdd.setVisible(true);
        }
    }

    @FXML
    void onBuy(MouseEvent event) {
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        infoID.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        anchorPaneAdd.setVisible(false);
        anchorPaneEdit.setVisible(false);
        anchorPanDel.setVisible(false);
        anchorPanBuy.setVisible(true);
    }

    @FXML
    void onBuySubmitButton(MouseEvent event) {
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        goodLuckID.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        BasketController basketController = new BasketController();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            basketController.buyBasket(UserPage.onlineCustomer, java.time.LocalDate.now(), codesID.getText());
        } catch (NoMoneyExceptions noMoneyExceptions) {
            alert.setContentText(noMoneyExceptions.toString());
            alert.show();
        } catch (DiscountExceptions discountExceptions) {
            alert.setContentText(discountExceptions.toString());
            alert.show();

        } finally {
            goodLuckID.setVisible(true);
        }
    }

    @FXML
    void onFactors(MouseEvent event) {
        factorCounter = 0;
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        anchorPaneAdd.setVisible(false);
        anchorPanBuy.setVisible(false);
        anchorPanDel.setVisible(false);
        anchorPaneEdit.setVisible(false);
        preID.setVisible(true);
        nextID.setVisible(true);
        if (factorCounter <= onlineCustomer.getShoppingHistory().size()) {
            for (Product product : onlineCustomer.getShoppingHistory().get(factorCounter).getBoughtProducts()) {
                infoID.setText(onlineCustomer.getShoppingHistory().get(factorCounter).toString() + "\n" + product.toString());
            }
            infoID.setVisible(true);
        }
    }

    static int basketCounter;

    @FXML
    void onBasket(MouseEvent event) {
        basketCounter = 0;
        preID.setVisible(false);
        nextID.setVisible(false);
        infoID.setVisible(false);
        anchorPaneAdd.setVisible(false);
        anchorPanBuy.setVisible(false);
        anchorPanDel.setVisible(false);
        anchorPaneEdit.setVisible(false);
        nextBasketID1.setVisible(true);
        preBasketID1.setVisible(true);
        if (factorCounter <= UserPage.onlineCustomer.getShoppingbasket().size()) {
            infoID.setText(UserPage.onlineCustomer.getShoppingbasket().get(basketCounter).toString());
        }
        infoID.setVisible(true);
    }

    @FXML
    private Button nextBasketID1;


    @FXML
    private Button preBasketID1;

    @FXML
    void onBasketNext(MouseEvent event) {
        basketCounter++;
        onBasket(event);
    }

    @FXML
    void onBasketPre(MouseEvent event) {
        basketCounter--;
        onBasket(event);
    }

    @FXML
    void onCharge(MouseEvent event) throws IOException {
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        infoID.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        anchorPaneAdd.setVisible(false);
        anchorPanBuy.setVisible(false);
        anchorPanDel.setVisible(false);
        anchorPaneEdit.setVisible(false);
        newPage(event, "Charge", "Charging");
    }

    private static LocalDate counterChance = LocalDate.ofEpochDay(1);

    @FXML
    void onCircleClicked(MouseEvent event) {
        infoID.setVisible(false);
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        anchorPaneAdd.setVisible(false);
        anchorPanBuy.setVisible(false);
        anchorPanDel.setVisible(false);
        anchorPaneEdit.setVisible(false);

        if (counterChance.equals(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Once a day!");
            alert.show();
            anchorPaneCircle.setVisible(false);
        } else {
            counterChance = LocalDate.now();
            UserController userController = new UserController();
            discountCodeID.setText(userController.circleChance(UserPage.onlineCustomer));
        }
    }

    @FXML
    void onDiscount(MouseEvent event) {
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        infoID.setVisible(false);
        anchorPaneAdd.setVisible(false);
        anchorPanBuy.setVisible(false);
        anchorPanDel.setVisible(false);
        anchorPaneEdit.setVisible(false);
        String discounts = "";
        for (Discount discount : UserPage.onlineCustomer.getDiscounts()) {
            discounts += discount.toString() + "\n\n";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(discounts);
        alert.show();
    }

    @FXML
    void onDelete(MouseEvent event) {
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        infoID.setVisible(false);
        anchorPaneAdd.setVisible(false);
        anchorPanBuy.setVisible(false);
        anchorPaneEdit.setVisible(false);
        anchorPanDel.setVisible(true);
    }

    @FXML
    void onEditProfile(MouseEvent event) {
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        preID.setVisible(false);
        nextID.setVisible(false);
        infoID.setVisible(false);
        anchorPaneAdd.setVisible(false);
        anchorPanBuy.setVisible(false);
        anchorPanDel.setVisible(false);
        anchorPaneEdit.setVisible(true);
    }

    static int factorCounter;

    @FXML
    private Button preID;

    @FXML
    private Button nextID;


    @FXML
    void onNext(MouseEvent event) {
        factorCounter++;
        onFactors(event);
    }

    @FXML
    void onPre(MouseEvent event) {
        factorCounter--;
        onFactors(event);
    }

    @FXML
    void onProfile(MouseEvent event) {
        preID.setVisible(false);
        nextBasketID1.setVisible(false);
        preBasketID1.setVisible(false);
        nextID.setVisible(false);
        infoID.setVisible(false);
        anchorPaneAdd.setVisible(false);
        anchorPanBuy.setVisible(false);
        anchorPanDel.setVisible(false);
        anchorPaneEdit.setVisible(false);
        infoID.setText(UserPage.onlineCustomer.toString());
        infoID.setVisible(true);
    }

    @FXML
    void onShop(MouseEvent event) throws IOException {
        newPage(event, "viewShop", "Online Shop!");
    }

    public void newPage(MouseEvent event, String fxml, String title) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml + "-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 1535, 835);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}

