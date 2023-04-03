package controller;

import model.products.*;
import model.users.Request;
import model.users.Customer;

import static model.users.Admin.admin;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserController {
    Customer customer = new Customer();
    Request request = new Request();

    boolean regexName(String username) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    public String setUsername(String username) {
        if (regexName(username)) {
            for (Customer c : admin.getCustomers()) {
                if (c.getUsername().equals(username)) {
                    return "This username is already in the list!";
                }
            }
            return null;
        }
        return "Username just should have letters and numbers!";
    }


    boolean regexPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^09\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.find();
    }

    boolean regexEmail(String email) {
        Pattern pattern = Pattern.compile("^\\w+@(yahoo|gmail)\\.com$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public String setPhoneNumber(String phoneNumber) {
        if (regexPhoneNumber(phoneNumber)) {
            for (Customer c : admin.getCustomers()) {
                if (c.getPhoneNumber().equals(phoneNumber))
                    return "This phone number is already in the list!";
            }
            return null;
        }
        return "Phone number must be started with (09) and has 11 numbers!";
    }

    public String setEmail(String email) {
        if (regexEmail(email)) {
            for (Customer c : admin.getCustomers()) {
                if (c.getEmail().equals(email))
                    return "This email is already in the list!";
            }
            return null;
        }
        return "Email is invalid!";
    }

    public boolean setPassword(String password) {
        Pattern pattern = Pattern.compile("(\\S){8,}");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public void signupRequest(String username, String password, String phoneNumber, String email) {
        customer.setter(username, email, phoneNumber, password);
        request("sign up request.", customer);
    }

    public void request(String text, Customer customer) {
        request.setter(customer, text);
        if (!admin.getRequests().contains(request)) {
            admin.getRequests().add(request);
        }

    }

    boolean checkSignupRequest(String username) {
        for (Request request : admin.getAcceptedRequest()) {
            if (request.getCustomer().getUsername().equals(username) && request.getText().equals("sign up request.")) {
                if (request.getAcception()) {
                    admin.getCustomers().add(request.getCustomer());
                    return true;
                }
            }
        }
        return false;
    }

    public String login(String username, String password) {
        if (checkSignupRequest(username)) {
            for (Customer customer : admin.getCustomers()) {
                if (customer.getUsername().equals(username)) {
                    if (customer.getPassword().equals(password)) {
                        return "You have been logged in successfully!";
                    }
                    return "Your password is incorrect!";
                }
                return "Your username is incorrect!";
            }
        }
        return "You haven't been signed up!";
    }

    public boolean changeInfo(Customer customer, String prePassword, String newPassword, String newPhoneNumber, String newEmail) {
        if (admin.getCustomers().contains(customer)) {
            if (customer.getPassword().equals(prePassword)) {
                customer.setPassword(newPassword);
                customer.setPhoneNumber(newPhoneNumber);
                customer.setEmail(newEmail);
                return true;
            }
        }
        return false;
    }


    ////products


    public Product makeProduct(long productId) {
        for (Product product : admin.getProducts()) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> showProducts() {
        return admin.getProducts();
    }

    public String searchProductById(long productId) {
        if (showProducts().contains(makeProduct(productId))) {
            return makeProduct(productId).getBaseInfo();
        }
        return null;
    }

    public ArrayList<Product> seachProductsByName(String productName) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : showProducts()) {
            if (product.getName().equals(productName)) {
                products.add(product);
            }
        }
        return products;
    }


    public ArrayList<Product> filterByCategory(Category category) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : showProducts()) {
            if (product.getCategory().equals(Category.DIGITAL)) {
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByPrice(double minPrice, double maxPrice) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : showProducts()) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByAvailable() {
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : showProducts()) {
            if (product.isAvailable()) {
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByAverage(float minAverage, float maxAverage) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : showProducts()) {
            if (product.getAverage() >= minAverage && product.getAverage() <= maxAverage) {
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<DigitalProduct> filterByDigitalWeight(double minWeight, double maxWeight) {
        ArrayList<DigitalProduct> products = new ArrayList<>();
        for (Product product : showProducts()) {
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
        for (Product product : showProducts()) {
            if (product instanceof SavingInfoProduct) {
                products.add((SavingInfoProduct) product);
            }
        }
        return products;
    }

    public ArrayList<SavingInfoProduct> filterBySavingLimitaton(int limitation) {
        ArrayList<SavingInfoProduct> products = new ArrayList<>();
        for (Product product : showProducts()) {
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
        for (Product product : showProducts()) {
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
        for (Product product : showProducts()) {
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
        for (Product product : showProducts()) {
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
        for (Product product : showProducts()) {
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
        for (Product product : showProducts()) {
            if (product instanceof Automobile) {
                products.add((Automobile) product);
            }
        }
        return products;
    }

    public ArrayList<Automobile> filterByAutomatic(boolean automatic) {
        ArrayList<Automobile> products = new ArrayList<>();
        for (Product product : showProducts()) {
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
        for (Product product : showProducts()) {
            if (product instanceof Bicycle) {
                products.add((Bicycle) product);
            }
        }
        return products;
    }

    public ArrayList<Bicycle> filterByBicycleType(BicycleType type) {
        ArrayList<Bicycle> products = new ArrayList<>();
        for (Product product : showProducts()) {
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
        for (Product product : showProducts()) {
            if (product instanceof FlashMemory) {
                products.add((FlashMemory) product);
            }
        }
        return products;
    }

    public ArrayList<Computer> filterByPC() {
        ArrayList<Computer> products = new ArrayList<>();
        for (Product product : showProducts()) {
            if (product instanceof Computer) {
                products.add((Computer) product);
            }
        }
        return products;
    }

    public ArrayList<SSD> filterBySSD() {
        ArrayList<SSD> products = new ArrayList<>();
        for (Product product : showProducts()) {
            if (product instanceof SSD) {
                products.add((SSD) product);
            }
        }
        return products;
    }

    public ArrayList<Notebook> filterByNotebook() {
        ArrayList<Notebook> products = new ArrayList<>();
        for (Product product : showProducts()) {
            if (product instanceof Notebook) {
                products.add((Notebook) product);
            }
        }
        return products;
    }

    public ArrayList<Pen> filterByPen() {
        ArrayList<Pen> products = new ArrayList<>();
        for (Product product : showProducts()) {
            if (product instanceof Pen) {
                products.add((Pen) product);
            }
        }
        return products;
    }

    public ArrayList<Pencil> filterByPencil() {
        ArrayList<Pencil> products = new ArrayList<>();
        for (Product product : showProducts()) {
            if (product instanceof Pencil) {
                products.add((Pencil) product);
            }
        }
        return products;
    }

}

////score
