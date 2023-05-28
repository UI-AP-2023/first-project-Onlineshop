package com.example.digikala;

import controller.admin.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainPage {

    @FXML
    private Button loginID;

    @FXML
    private Button shopViewID;

    @FXML
    private Button signupID;

    @FXML
    private Text welcomeID;

    @FXML
    private Label welcomeText;


    @FXML
    void onCloseButtonClick(MouseEvent event) {
        System.exit(0);
    }

    public void newPage(MouseEvent event,String fxml,String title) throws IOException {
        Parent parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml+"-view.fxml")));
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onLoginButtonClick(MouseEvent event) throws IOException {
        newPage(event,"login","Login!");
    }

    @FXML
    void onShopViewButtonClick(MouseEvent event) throws IOException{
        newPage(event,"viewShop","Online Shop!");
    }

    @FXML
    void onSignupButtonClick(MouseEvent event) throws IOException{
        newPage(event,"signup","Sign up!");
    }

}