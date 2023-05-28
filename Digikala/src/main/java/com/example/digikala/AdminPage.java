package com.example.digikala;

import controller.admin.AdminController;
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
import model.exceptions.InvalidAdminTasks;
import model.users.Admin;
import model.users.Customer;
import model.users.Request;


import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class AdminPage {
    @FXML
    private Text adminShowId;

    @FXML
    private TextField commandsID;

    @FXML
    private Text helpID;

    AdminController adminController=new AdminController();

    public void newPage(MouseEvent event, String fxml, String title) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml + "-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onBack(MouseEvent event) throws IOException {
        newPage(event,"hello","Welcome");
    }

    @FXML
    void onExit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void onMoved(MouseEvent event) {
        helpID.setText("You can entered commands like following formats( '' means exactly ):\n\n'add'+'(car|USB|SSD|computer|pen|pencil|notebook|bicycle|food)'+features\n'edit'+productId+ newName + newPrice +newNumberOfAvailable\n'delete'+productId\n'request'+'(true|false)'+numberOfAcceptions\n'info'+productId\n'showReq'\n'customers'\n'discount'+percent+date(dd/mm/yyyy)+capacity+'all'|'2000'|customerID\n'productDis'+productID+percent\n'removeDis'+productID+percent");
    }

    @FXML
    void onSubmit(MouseEvent event) {
        switch (commandsID.getText()) {
            case "showReq" -> {
                for (Request request : Admin.getAdmin().getRequests()) {
                    adminShowId.setText("Username: " + request.toString());
                }
            }
            case "customers" -> {
                for (Customer customer : adminController.showCustomers()) {
                    adminShowId.setText(customer.toString());
                }
            }
            default -> {
                try {
                    adminShowId.setText(adminController.orders(commandsID.getText()));
                } catch (InvalidAdminTasks invalidAdminTasks) {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(invalidAdminTasks.toString());
                    alert.show();
                } finally {
                    adminShowId.setText("Good Luck!");
                }
            }
        }
    }

}
