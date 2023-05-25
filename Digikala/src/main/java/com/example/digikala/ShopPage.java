package com.example.digikala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.users.Customer;

import java.io.IOException;
import java.util.Objects;

public class ShopPage {
    public static Customer onlineCustomer;

    @FXML
    private Button backID;

    @FXML
    private MenuItem byAvailabilityID;

    @FXML
    private MenuItem byCategoryID;

    @FXML
    private MenuItem byDigitalID;

    @FXML
    private MenuItem byFood;

    @FXML
    private MenuItem byIDID;

    @FXML
    private MenuItem byNameID;

    @FXML
    private MenuItem byPrice;

    @FXML
    private MenuItem byScore;

    @FXML
    private MenuItem byStationery;

    @FXML
    private MenuItem byVehicleID;

    @FXML
    private Button closeID;

    @FXML
    private AnchorPane eight;

    @FXML
    private MenuButton filterID;

    @FXML
    private AnchorPane five;

    @FXML
    private AnchorPane four;

    @FXML
    private Button nextID;

    @FXML
    private AnchorPane nine;

    @FXML
    private AnchorPane one;

    @FXML
    private Button preID;

    @FXML
    private MenuButton searchID;

    @FXML
    private AnchorPane seven;

    @FXML
    private AnchorPane six;

    @FXML
    private Button sortID;

    @FXML
    private AnchorPane ten;

    @FXML
    private AnchorPane three;

    @FXML
    private AnchorPane two;

    @FXML
    void on(ActionEvent event) {

    }

    @FXML
    void onAvailability(ActionEvent event) {

    }

    @FXML
    void onByIDClicked(ActionEvent event) throws IOException {
    }

    @FXML
    void onByNameValidation(ActionEvent event) {

    }

    @FXML
    void onCategory(ActionEvent event) {

    }

    @FXML
    void onCloseBottomClick(MouseEvent event) {

    }

    @FXML
    void onDigital(ActionEvent event) {

    }

    @FXML
    void onEightClicked(MouseEvent event) {

    }

    @FXML
    void onFilterBottomClick(MouseEvent event) {

    }

    @FXML
    void onFiveClicked(MouseEvent event) {

    }

    @FXML
    void onFood(ActionEvent event) {

    }

    @FXML
    void onFourClicked(MouseEvent event) {

    }

    @FXML
    void onNextBottomClick(MouseEvent event) {

    }

    @FXML
    void onNineClicked(MouseEvent event) {

    }

    @FXML
    void onOneClicked(MouseEvent event) {

    }

    @FXML
    void onPreBottomClick(MouseEvent event) {

    }

    @FXML
    void onPrice(ActionEvent event) {

    }

    @FXML
    void onScore(ActionEvent event) {

    }

    @FXML
    void onSearchClicked(MouseEvent event) {

    }

    @FXML
    void onSevenClicked(MouseEvent event) {

    }

    @FXML
    void onSixClicked(MouseEvent event) {

    }

    @FXML
    void onSortBottomClick(MouseEvent event) {

    }

    @FXML
    void onTenClicked(MouseEvent event) {

    }

    @FXML
    void onThreeClicked(MouseEvent event) {

    }

    @FXML
    void onTwoClicked(MouseEvent event) {

    }

    @FXML
    void onVehicle(ActionEvent event) {

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
    void onBackBottomClick(MouseEvent event) throws IOException {
        if(onlineCustomer==null){
            newPage(event,"hello","Shabnam's Shop!");}
        else {
            newPage(event,"user","User Page!");
        }
    }
}


