package controller.user;

import controller.admin.AdminController;
import model.exceptions.AvailableProductExceptions;
import model.products.*;
import model.products.digital.*;
import model.products.food.FoodProduct;
import model.products.stationery.Notebook;
import model.products.stationery.Pen;
import model.products.stationery.Pencil;
import model.products.stationery.StationeryProduct;
import model.products.vehicle.Automobile;
import model.products.vehicle.Bicycle;
import model.products.vehicle.BicycleType;
import model.products.vehicle.VehicleProduct;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class ProductController {

    public Product makeProduct(long productId) {
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public String searchProductById(long productId) throws AvailableProductExceptions {
        AdminController adminController = new AdminController();
        if (adminController.showProducts().contains(makeProduct(productId))) {
            return makeProduct(productId).getBaseInfo();
        }
        throw new AvailableProductExceptions();
    }

    public ArrayList<Product> searchProductsByName(String productName) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.getName().equals(productName)) {
                products.add(product);
            }
        }
        return products;
    }


    public ArrayList<Product> filterByCategory(Category category) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.getCategory().equals(category)) {
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByPrice(double minPrice, double maxPrice) {
        if (minPrice >= 0 && maxPrice >= 0) {
            ArrayList<Product> products = new ArrayList<>();
            AdminController adminController = new AdminController();
            for (Product product : adminController.showProducts()) {
                if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                    products.add(product);
                }
            }
            return products;
        }
        throw new InputMismatchException();
    }

    public ArrayList<Product> filterByAvailable() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.isAvailable()) {
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByAverage(float minAverage, float maxAverage) {
        if (maxAverage >= 0 && minAverage >= 0) {
            ArrayList<Product> products = new ArrayList<>();
            AdminController adminController = new AdminController();
            for (Product product : adminController.showProducts()) {
                if (product.getAverage() >= minAverage && product.getAverage() <= maxAverage) {
                    products.add(product);
                }
            }
            return products;
        }
        throw new InputMismatchException();
    }

    public ArrayList<DigitalProduct> filterByDigitalWeight(double minWeight, double maxWeight) {
        ArrayList<DigitalProduct> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof DigitalProduct) {
                if (((DigitalProduct) product).getWeight() >= minWeight && ((DigitalProduct) product).getWeight() <= maxWeight) {
                    products.add((DigitalProduct) product);
                }
            }
        }
        return products;
    }

    public ArrayList<Product> filterBySavingInoProducts() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof SavingInfoProduct) {
                products.add((SavingInfoProduct) product);
            }
        }
        return products;
    }

    public ArrayList<SavingInfoProduct> filterBySavingLimitaton(int limitation) {
        ArrayList<SavingInfoProduct> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof SavingInfoProduct) {
                if (((SavingInfoProduct) product).getLimitation() == limitation) {
                    products.add((SavingInfoProduct) product);
                }
            }
        }
        return products;
    }

    public ArrayList<Product> filterByStationeryCountry(String country) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof StationeryProduct) {
                if (((StationeryProduct) product).getCountry().equals(country)) {
                    products.add((StationeryProduct) product);
                }
            }
        }
        return products;
    }

    public ArrayList<Product> filterByVehicleCompany(String company) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof VehicleProduct) {
                if (((VehicleProduct) product).getNameOfCompany().equals(company)) {
                    products.add((VehicleProduct) product);
                }
            }
        }
        return products;
    }

    public ArrayList<Product> filterByFoodProduction(String dateOfProduction) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof FoodProduct) {
                if (((FoodProduct) product).getDateOfProduction().equals(dateOfProduction)) {
                    products.add((FoodProduct) product);
                }
            }
        }
        return products;
    }

    public ArrayList<Product> filterByFoodExpiration(String dateOfExpiration) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof FoodProduct) {
                if (((FoodProduct) product).getDateOfExpiration().equals(dateOfExpiration)) {
                    products.add((FoodProduct) product);
                }
            }
        }
        return products;
    }

    public ArrayList<Product> filterByAutomobile() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Automobile) {
                products.add((Automobile) product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByAutomatic(boolean automatic) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Automobile) {
                if (((Automobile) product).getAutomatic() == automatic) {
                    products.add((Automobile) product);
                }
            }
        }
        return products;
    }

    public ArrayList<Product> filterByBicycle() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Bicycle) {
                products.add((Bicycle) product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByBicycleType(BicycleType type) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Bicycle) {
                if (((Bicycle) product).getBicycleType().equals(type)) {
                    products.add((Bicycle) product);
                }
            }
        }
        return products;
    }

    public ArrayList<Product> filterByMemory() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof FlashMemory) {
                products.add((FlashMemory) product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByPC() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Computer) {
                products.add((Computer) product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterBySSD() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof SSD) {
                products.add((SSD) product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByNotebook() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Notebook) {
                products.add((Notebook) product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByPen() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Pen) {
                products.add((Pen) product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByPencil() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Pencil) {
                products.add((Pencil) product);
            }
        }
        return products;
    }


    public ArrayList<Product> sorting(ArrayList<Product> products) {
        products.sort(Product::compareTo);
        return products;
    }

}

