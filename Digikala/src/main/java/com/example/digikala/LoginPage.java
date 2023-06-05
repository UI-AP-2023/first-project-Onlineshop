package com.example.digikala;

import controller.user.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.exceptions.InvalidLogin;
import model.users.Customer;

import java.io.IOException;
import java.util.Objects;

public class LoginPage {

    @FXML
    private Text goodLuckID;

    @FXML
    private PasswordField passID;

    @FXML
    private TextField usernameID;

    @FXML
    void onBackBottomClick(MouseEvent event) throws IOException {
        newPage(event, "hello", "Shabnam's Shop!");
    }

    @FXML
    void onCloseBottomClick(MouseEvent event) {
        System.exit(0);
    }

    public void newPage(MouseEvent event, String fxml, String title) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml + "-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onSubmitClick(MouseEvent event) throws IOException {
        if (usernameID.getText().equals("admin") && passID.getText().equals("admin")) {
            newPage(event, "admin", "Admin Page!");
        } else {
            UserController userController = new UserController();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            try {
                Customer onlineCustomer = userController.login(usernameID.getText(), passID.getText());
                UserPage.onlineCustomer = onlineCustomer;
                newPage(event, "user", "User Page!");
            } catch (InvalidLogin invalidLogin) {
                alert.setContentText(invalidLogin.toString());
                alert.show();
            } finally {
                goodLuckID.setVisible(true);
            }
        }
    }

}
