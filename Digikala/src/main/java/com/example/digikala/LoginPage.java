package com.example.digikala;

import controller.user.UserController;
import javafx.scene.text.Text;
import model.exceptions.InvalidLogin;
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

public class LoginPage {

    @FXML
    private Button backID;

    @FXML
    private Button closeID;
    @FXML
    private Text loginID;
    @FXML
    private Text goodLuckID;

    @FXML
    private TextField passID;

    @FXML
    private TextField usernameID;

    @FXML
    private Button submitID;

    @FXML
    void onBackBottomClick(MouseEvent event) throws IOException {
        newPage(event,"hello","Shabnam's Shop!");
    }

    @FXML
    void onCloseBottomClick(MouseEvent event) {
        System.exit(0);
    }

    public void newPage(MouseEvent event,String fxml,String title) throws IOException {
        Parent parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml+"-view.fxml")));
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent,1535,835);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onSubmitClick(MouseEvent event) throws IOException{
        if (usernameID.getText().equals("admin") && passID.getText().equals("admin")) {
//            newPage(event,"admin","Admin Page!");
            AdminPage adminPage=new AdminPage();
            adminPage.commands();
        } else {
            UserController userController=new UserController();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            try {
                userController.login(usernameID.getText(), passID.getText());
                UserPage.onlineCustomer =userController.login(usernameID.getText(), passID.getText());
                ShopPage.onlineCustomer =userController.login(usernameID.getText(), passID.getText());
                CreditCardPage.onlineCustomer=userController.login(usernameID.getText(),passID.getText());
                newPage(event,"userPage","User Page!");
            }catch (InvalidLogin invalidLogin){
                alert.setContentText(invalidLogin.toString());
                alert.show();
            }finally {
                goodLuckID.setVisible(true);
            }
        }
    }

}
