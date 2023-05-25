package com.example.digikala;

import controller.user.BasketController;
import javafx.scene.text.Text;
import model.exceptions.InvalidInformationExceptions;
import model.users.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CreditCardPage {
    public static Customer onlineCustomer;

    @FXML
    private Button backID;

    @FXML
    private Text goodLuckID;

    @FXML
    private TextField cardID;

    @FXML
    private Button closeID;

    @FXML
    private TextField cvv2ID;

    @FXML
    private TextField costID;

    @FXML
    private TextField passID;

    @FXML
    private Button payID;

    @FXML
    void onBackBottomClick(MouseEvent event) {

    }

    @FXML
    void onCloseBottomClick(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void onPayBottomClick(MouseEvent event) {
        BasketController basketController = new BasketController();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        try {
            basketController.chargeRequest(onlineCustomer, cardID.getText(), cvv2ID.getText(), passID.getText(), Double.parseDouble(costID.getText()));
            alert.setContentText("Your request has been sent.");
            alert.show();
        } catch (InvalidInformationExceptions invalidInformationExceptions) {
            alert1.setContentText(invalidInformationExceptions.toString());
            alert1.show();
        } finally {
            goodLuckID.setVisible(true);
        }

    }
}