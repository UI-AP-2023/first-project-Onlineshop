package com.example.digikala;

import controller.user.BasketController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.exceptions.InvalidInformationExceptions;
import model.users.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class CreditCardPage {

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

    public void newPage(MouseEvent event,String fxml,String title) throws IOException {
        Parent parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml+"-view.fxml")));
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onBackBottomClick(MouseEvent event) throws IOException {
        newPage(event,"user","User Page!");
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
            basketController.chargeRequest(UserPage.onlineCustomer, cardID.getText(), cvv2ID.getText(), passID.getText(), Double.parseDouble(costID.getText()));
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