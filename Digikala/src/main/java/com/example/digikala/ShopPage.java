package com.example.digikala;

import controller.admin.AdminController;
import controller.user.ProductController;
import javafx.event.ActionEvent;
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
import model.products.Category;
import model.products.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;

public class ShopPage {
    ProductController productController = new ProductController();
    private int minIndex = 0;
    private int maxIndex = 10;

    static int filterCounter = 0;

    AdminController adminController = new AdminController();
    private ArrayList<Product> products = adminController.showProducts();

    @FXML
    private TextField minAvg;
    @FXML
    private TextField maxAvg;

    @FXML
    private TextField nameTextID;

    @FXML
    private TextField date;

    @FXML
    private TextField maxPrice;

    @FXML
    private TextField minPrice;

    @FXML
    private Text text10ID;

    @FXML
    private Text text1ID;

    @FXML
    private Text text2ID;

    @FXML
    private Text text3ID;

    @FXML
    private Text text4ID;

    @FXML
    private Text text5ID;

    @FXML
    private Text text6ID;

    @FXML
    private Text text7ID;

    @FXML
    private Text text8ID;

    @FXML
    private Text text9ID;


    @FXML
    void onMouseMoved(MouseEvent event) {
        showTenProducts();
    }

    @FXML
    void onByIDClicked(MouseEvent event) throws IOException {
        newPage(event, "product", "Search Product!");
    }

    @FXML
    private Text helpID;

    @FXML
    void onByName(MouseEvent event) {
        nameTextID.setVisible(true);
        helpID.setVisible(true);
        ProductController productController = new ProductController();
        this.products = productController.searchProductsByName(nameTextID.getText());
        showTenProducts();
        nameTextID.setVisible(false);
        helpID.setVisible(false);
    }

    @FXML
    void onCloseBottomClick(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void onDigital(ActionEvent event) {
        categoryFilter(Category.DIGITAL);
    }

    @FXML
    void onVehicle(ActionEvent event) {
        categoryFilter(Category.VEHICLE);
    }

    @FXML
    void onFood(ActionEvent event) {
        categoryFilter(Category.FOOD);
    }

    void categoryFilter(Category category) {
        filterCounter++;
        ProductController productController = new ProductController();
        if (filterCounter == 1) {
            this.products = productController.filterByCategory(category);
            showTenProducts();
        } else if (filterCounter == 2) {             //remove filter
            this.products = adminController.showProducts();
            this.products = adminController.showProducts();
            showTenProducts();
            filterCounter = 0;
        }
    }


    void moreInfo(Text text, MouseEvent event) throws IOException {
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (text.getText().equals(product.getBaseInfo())) {
                ProductPage.product = product;
                newPage(event, "product", "More information!");
            }
        }
    }

    @FXML
    void onOneClicked(MouseEvent event) throws IOException {
        moreInfo(text1ID, event);
    }

    @FXML
    void onTwoClicked(MouseEvent event) throws IOException {
        moreInfo(text2ID, event);
    }

    @FXML
    void onThreeClicked(MouseEvent event) throws IOException {
        moreInfo(text3ID, event);
    }

    @FXML
    void onFourClicked(MouseEvent event) throws IOException {
        moreInfo(text4ID, event);
    }

    @FXML
    void onFiveClicked(MouseEvent event) throws IOException {
        moreInfo(text5ID, event);
    }

    @FXML
    void onSixClicked(MouseEvent event) throws IOException {
        moreInfo(text6ID, event);
    }

    @FXML
    void onSevenClicked(MouseEvent event) throws IOException {
        moreInfo(text7ID, event);
    }

    @FXML
    void onEightClicked(MouseEvent event) throws IOException {
        moreInfo(text8ID, event);
    }

    @FXML
    void onNineClicked(MouseEvent event) throws IOException {
        moreInfo(text9ID, event);
    }

    @FXML
    void onTenClicked(MouseEvent event) throws IOException {
        moreInfo(text10ID, event);
    }

    @FXML
    void onScore(MouseEvent event) {
        minAvg.setVisible(true);
        maxAvg.setVisible(true);
        helpID.setVisible(true);
        ProductController productController = new ProductController();
        try {
            this.products = productController.filterByAverage(Float.parseFloat(minAvg.getText()), Float.parseFloat(maxAvg.getText()));
            showTenProducts();
            minAvg.setVisible(false);
            maxAvg.setVisible(false);
            helpID.setVisible(false);
        } catch (InputMismatchException inputMismatchException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(inputMismatchException.toString());
            alert.show();
        }
    }

    @FXML
    void onPriceBottomClick(MouseEvent event) {
        maxPrice.setVisible(true);
        minPrice.setVisible(true);
        helpID.setVisible(true);
        ProductController productController = new ProductController();
        try {
            this.products = productController.filterByPrice(Double.parseDouble(minPrice.getText()), Double.parseDouble(maxPrice.getText()));
            showTenProducts();
            maxPrice.setVisible(false);
            minPrice.setVisible(false);
            helpID.setVisible(false);
        } catch (InputMismatchException inputMismatchException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(inputMismatchException.toString());
            alert.show();
        }
    }

    @FXML
    void onAvailability(MouseEvent event) {
        ProductController productController = new ProductController();
        this.products = productController.filterByAvailable();
        showTenProducts();
    }

    @FXML
    void onSortBottomClick(MouseEvent event) {
        ProductController productController = new ProductController();
        AdminController adminController = new AdminController();
        this.products = productController.sorting(adminController.showProducts());
        showTenProducts();
    }


    @FXML
    void onStationery(ActionEvent event) {
        categoryFilter(Category.STATIONERY);
    }


    void showTenProducts() {
        for (Text text : Arrays.asList(text1ID, text2ID, text3ID, text4ID, text5ID, text6ID, text7ID, text8ID, text9ID, text10ID)) {
            text.setText("");
        }
        int counter = 0;
        if (this.minIndex < this.products.size()) {
            for (Product product : this.products) {
                if (this.minIndex <= counter) {
                    setTextProduct(product,counter);
                } else if (this.maxIndex == counter) {
                    break;
                }
                counter++;
            }
        }
    }

    void setTextProduct(Product product, int counter) {
        switch (counter) {
            case 0 -> text1ID.setText(product.getBaseInfo());
            case 1 -> text2ID.setText(product.getBaseInfo());
            case 2 -> text3ID.setText(product.getBaseInfo());
            case 3 -> text4ID.setText(product.getBaseInfo());
            case 4 -> text5ID.setText(product.getBaseInfo());
            case 5 -> text6ID.setText(product.getBaseInfo());
            case 6 -> text7ID.setText(product.getBaseInfo());
            case 7 -> text8ID.setText(product.getBaseInfo());
            case 8 -> text9ID.setText(product.getBaseInfo());
            case 9 -> text10ID.setText(product.getBaseInfo());
        }
    }

    public void newPage(MouseEvent event, String fxml, String title) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml + "-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 1535, 835);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onNextBottomClick(MouseEvent event) {
        this.minIndex += 10;
        this.maxIndex += 10;
        AdminController adminController = new AdminController();
        this.products = adminController.showProducts();
        showTenProducts();
    }

    @FXML
    void onPreBottomClick(MouseEvent event) {
        this.minIndex -= 10;
        this.maxIndex -= 10;
        if (minIndex < 0) {
            this.minIndex = 0;
            this.maxIndex = 10;
        }
        AdminController adminController = new AdminController();
        this.products = adminController.showProducts();
        showTenProducts();
    }


    @FXML
    void onBackBottomClick(MouseEvent event) throws IOException {
        if (UserPage.onlineCustomer == null) {
            newPage(event, "hello", "Shabnam's Shop!");
        } else {
            newPage(event, "user", "User Page!");
        }
    }


    @FXML
    void dateOfExpiration(ActionEvent event) {
        date.setVisible(true);
        helpID.setVisible(true);
        this.products=productController.filterByFoodExpiration(date.getText());
        showTenProducts();
        date.setVisible(false);
        helpID.setVisible(false);
    }

    @FXML
    void onAutomobile(ActionEvent event) {
        this.products = productController.filterByAutomobile();
        showTenProducts();
    }

    @FXML
    void onByAutomatic(ActionEvent event) {
        this.products = productController.filterByAutomatic(true);
        showTenProducts();
    }

    @FXML
    void onByBicycle(ActionEvent event) {
        this.products = productController.filterByBicycle();
        showTenProducts();
    }


    @FXML
    void onByFlash(ActionEvent event) {
        this.products = productController.filterByMemory();
        showTenProducts();
    }


    @FXML
    void onByNotebooks(ActionEvent event) {
        this.products = productController.filterByPC();
        showTenProducts();
    }

    @FXML
    void onByPCs(ActionEvent event) {
        this.products = productController.filterByPC();
        showTenProducts();
    }

    @FXML
    void onByPencil(ActionEvent event) {
        this.products = productController.filterByPencil();
        showTenProducts();
    }

    @FXML
    void onByPen(ActionEvent event) {
        this.products = productController.filterByPen();
        showTenProducts();
    }

    @FXML
    void onBySSD(ActionEvent event) {
        this.products = productController.filterBySSD();
        showTenProducts();
    }

    @FXML
    void onBySavingInfo(ActionEvent event) {
        this.products = productController.filterBySavingInoProducts();
        showTenProducts();
    }


    @FXML
    void onDateOfProduction(ActionEvent event) {
        date.setVisible(true);
        helpID.setVisible(true);
        this.products=productController.filterByFoodProduction(date.getText());
        showTenProducts();
        date.setVisible(false);
        helpID.setVisible(false);
    }


    @FXML
    void onSignup(MouseEvent event) throws IOException {
        newPage(event,"signup","Sign up!");
    }
}



