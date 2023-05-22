package controller.user;

import controller.admin.AdminController;
import model.exceptions.AvailableProductExceptions;
import model.products.*;

import java.util.ArrayList;

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
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                products.add(product);
            }
        }
        return products;
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
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.getAverage() >= minAverage && product.getAverage() <= maxAverage) {
                products.add(product);
            }
        }
        return products;
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

    public ArrayList<SavingInfoProduct> filterBySavingInoProducts() {
        ArrayList<SavingInfoProduct> products = new ArrayList<>();
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

    public ArrayList<StationeryProduct> filterByStationeryCountry(String country) {
        ArrayList<StationeryProduct> products = new ArrayList<>();
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

    public ArrayList<VehicleProduct> filterByVehicleCompany(String company) {
        ArrayList<VehicleProduct> products = new ArrayList<>();
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

    public ArrayList<FoodProduct> filterByFoodProduction(String dateOfProduction) {
        ArrayList<FoodProduct> products = new ArrayList<>();
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

    public ArrayList<FoodProduct> filterByFoodExpiration(String dateOfExpiration) {
        ArrayList<FoodProduct> products = new ArrayList<>();
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

    public ArrayList<Automobile> filterByAutomobile() {
        ArrayList<Automobile> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Automobile) {
                products.add((Automobile) product);
            }
        }
        return products;
    }

    public ArrayList<Automobile> filterByAutomatic(boolean automatic) {
        ArrayList<Automobile> products = new ArrayList<>();
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

    public ArrayList<Bicycle> filterByBicycle() {
        ArrayList<Bicycle> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Bicycle) {
                products.add((Bicycle) product);
            }
        }
        return products;
    }

    public ArrayList<Bicycle> filterByBicycleType(BicycleType type) {
        ArrayList<Bicycle> products = new ArrayList<>();
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

    public ArrayList<FlashMemory> filterByMemory() {
        ArrayList<FlashMemory> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof FlashMemory) {
                products.add((FlashMemory) product);
            }
        }
        return products;
    }

    public ArrayList<Computer> filterByPC() {
        ArrayList<Computer> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Computer) {
                products.add((Computer) product);
            }
        }
        return products;
    }

    public ArrayList<SSD> filterBySSD() {
        ArrayList<SSD> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof SSD) {
                products.add((SSD) product);
            }
        }
        return products;
    }

    public ArrayList<Notebook> filterByNotebook() {
        ArrayList<Notebook> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Notebook) {
                products.add((Notebook) product);
            }
        }
        return products;
    }

    public ArrayList<Pen> filterByPen() {
        ArrayList<Pen> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Pen) {
                products.add((Pen) product);
            }
        }
        return products;
    }

    public ArrayList<Pencil> filterByPencil() {
        ArrayList<Pencil> products = new ArrayList<>();
        AdminController adminController = new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Pencil) {
                products.add((Pencil) product);
            }
        }
        return products;
    }


    public ArrayList<Product> sorting(ArrayList<Product> products){
        products.sort(Product::compareTo);
        return products;
    }

}

