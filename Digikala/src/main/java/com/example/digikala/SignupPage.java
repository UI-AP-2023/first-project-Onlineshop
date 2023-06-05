package com.example.digikala;

import controller.user.UserController;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import model.exceptions.InvalidEmailExceptions;
import model.exceptions.InvalidPassword;
import model.exceptions.InvalidPhoneNumberExceptions;
import model.exceptions.InvalidUserName;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignupPage {

    @FXML
    private TextField phoneID;

    @FXML
    private Text goodLuckID;

    @FXML
    private Button backID;

    @FXML
    private Button closeID;

    @FXML
    private TextField emailID;

    @FXML
    private TextField nameID;

    @FXML
    private PasswordField passID;

    @FXML
    private Button submitID;

    public void newPage(MouseEvent event, String fxml, String title) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml + "-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent,1535,835);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onBackBottomClick(MouseEvent event) throws IOException {
        newPage(event, "hello", "Shabnam's Shop!");
    }

    @FXML
    void onCloseBottomClick(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void onSubmitClick(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);

        UserController userController = new UserController();
        try {
            userController.setUserName(nameID.getText());
            userController.setPhoneNumber(phoneID.getText());
            userController.setEmail(emailID.getText());
            userController.setPassword(passID.getText());
            userController.signupRequest(nameID.getText(), passID.getText(), phoneID.getText(), emailID.getText());
            alert1.setTitle("Your request has been sent!");
            alert1.show();
        } catch (InvalidUserName | InvalidPhoneNumberExceptions | InvalidEmailExceptions | InvalidPassword x) {
            alert.setContentText(x.toString());
            alert.show();
        } finally {
            goodLuckID.setVisible(true);
        }
    }

}
