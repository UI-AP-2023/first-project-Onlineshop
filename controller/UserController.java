package controller;

import model.products.*;
import model.users.Request;
import model.users.Customer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class UserController {

    boolean regexName(String username) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    public String setUsername(String username) {
        if (regexName(username)) {
            AdminController adminController=new AdminController();
            for (Customer customer : adminController.showCustomers()) {
                if (customer.getUsername().equals(username)) {
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
            AdminController adminController=new AdminController();
            for (Customer customer : adminController.showCustomers()) {
                if (customer.getPhoneNumber().equals(phoneNumber))
                    return "This phone number is already in the list!";
            }
            return null;
        }
        return "Phone number must be started with (09) and has 11 numbers!";
    }

    public String setEmail(String email) {
        if (regexEmail(email)) {
            AdminController adminController=new AdminController();
            for (Customer customer : adminController.showCustomers()) {
                if (customer.getEmail().equals(email))
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
        Customer customer=new Customer();
        customer.setter(username, email, phoneNumber, password);
        request("sign up request.", customer);
    }

    public void request(String text, Customer customer) {
        Request request = new Request();
        request.setter(customer, text);
        AdminController adminController=new AdminController();
        if (!adminController.showRequests().contains(request)) {
            adminController.showRequests().add(request);
        }

    }

    boolean checkSignupRequest(String username) {
        AdminController adminController=new AdminController();
        for (Request request : adminController.showAcceptionRequest()) {
            if (request.getCustomer().getUsername().equals(username) && request.getText().equals("sign up request.")) {
                if (request.getAcception()) {
                    adminController.showCustomers().add(request.getCustomer());
                    return true;
                }
            }
        }
        return false;
    }

    public Customer login(String username, String password) {
        if (checkSignupRequest(username)) {
            AdminController adminController=new AdminController();
            for (Customer customer : adminController.showCustomers()) {
                if (customer.getUsername().equals(username)) {
                    if (customer.getPassword().equals(password)) {
                        return customer;
                    }
                }
            }
        }
        return null;
    }


    public boolean changeInfo(Customer customer, String prePassword, String newPassword, String newPhoneNumber, String newEmail) {
        AdminController adminController=new AdminController();
        if (adminController.showCustomers().contains(customer)) {
            if (customer.getPassword().equals(prePassword)) {
                customer.setPassword(newPassword);
                customer.setPhoneNumber(newPhoneNumber);
                customer.setEmail(newEmail);
                return true;
            }
        }
        return false;
    }

    public Product makeProduct(long productId) {
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public String searchProductById(long productId) {
        AdminController adminController=new AdminController();
        if (adminController.showProducts().contains(makeProduct(productId))) {
            return makeProduct(productId).getBaseInfo();
        }
        return null;
    }

    public ArrayList<Product> seachProductsByName(String productName) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.getName().equals(productName)) {
                products.add(product);
            }
        }
        return products;
    }


    public ArrayList<Product> filterByCategory(Category category) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.getCategory().equals(category)) {
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByPrice(double minPrice, double maxPrice) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByAvailable() {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.isAvailable()) {
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<Product> filterByAverage(float minAverage, float maxAverage) {
        ArrayList<Product> products = new ArrayList<>();
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product.getAverage() >= minAverage && product.getAverage() <= maxAverage) {
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<DigitalProduct> filterByDigitalWeight(double minWeight, double maxWeight) {
        ArrayList<DigitalProduct> products = new ArrayList<>();
        AdminController adminController=new AdminController();
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
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof SavingInfoProduct) {
                products.add((SavingInfoProduct) product);
            }
        }
        return products;
    }

    public ArrayList<SavingInfoProduct> filterBySavingLimitaton(int limitation) {
        ArrayList<SavingInfoProduct> products = new ArrayList<>();
        AdminController adminController=new AdminController();
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
        AdminController adminController=new AdminController();
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
        AdminController adminController=new AdminController();
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
        AdminController adminController=new AdminController();
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
        AdminController adminController=new AdminController();
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
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Automobile) {
                products.add((Automobile) product);
            }
        }
        return products;
    }

    public ArrayList<Automobile> filterByAutomatic(boolean automatic) {
        ArrayList<Automobile> products = new ArrayList<>();
        AdminController adminController=new AdminController();
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
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Bicycle) {
                products.add((Bicycle) product);
            }
        }
        return products;
    }

    public ArrayList<Bicycle> filterByBicycleType(BicycleType type) {
        ArrayList<Bicycle> products = new ArrayList<>();
        AdminController adminController=new AdminController();
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
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof FlashMemory) {
                products.add((FlashMemory) product);
            }
        }
        return products;
    }

    public ArrayList<Computer> filterByPC() {
        ArrayList<Computer> products = new ArrayList<>();
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Computer) {
                products.add((Computer) product);
            }
        }
        return products;
    }

    public ArrayList<SSD> filterBySSD() {
        ArrayList<SSD> products = new ArrayList<>();
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof SSD) {
                products.add((SSD) product);
            }
        }
        return products;
    }

    public ArrayList<Notebook> filterByNotebook() {
        ArrayList<Notebook> products = new ArrayList<>();
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Notebook) {
                products.add((Notebook) product);
            }
        }
        return products;
    }

    public ArrayList<Pen> filterByPen() {
        ArrayList<Pen> products = new ArrayList<>();
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Pen) {
                products.add((Pen) product);
            }
        }
        return products;
    }

    public ArrayList<Pencil> filterByPencil() {
        ArrayList<Pencil> products = new ArrayList<>();
        AdminController adminController=new AdminController();
        for (Product product : adminController.showProducts()) {
            if (product instanceof Pencil) {
                products.add((Pencil) product);
            }
        }
        return products;
    }


    public boolean scoreProduct(long productId, float customerScore, Customer customer) {
        AdminController adminController=new AdminController();
        if (adminController.showProducts().contains(makeProduct(productId))) {
            for (ShoppingFactor factor : customer.getShoppingHistory()) {
                if (factor.getBoughtProducts().contains(makeProduct(productId))) {
                    Score score = new Score();
                    makeProduct(productId).setAverage(customerScore / score.getCounter());
                    score.setter(customer, customerScore,makeProduct(productId));
                    return true;
                }
            }
        }
        return false;
    }

    public Comment commentRequest(long productId, String text, Customer customer) {
        AdminController adminController=new AdminController();
        if (adminController.admin.getCustomers().contains(customer)) {
            if (adminController.showProducts().contains(makeProduct(productId))) {
                Comment comment = new Comment();
                for (ShoppingFactor factor : customer.getShoppingHistory()) {
                    if (factor.getBoughtProducts().contains(makeProduct(productId))) {
                        comment.setBought(factor.getBoughtProducts().contains(makeProduct(productId)));
                    }
                }
                comment.setter(customer, text, productId);
                request("Comment request", customer);
                return comment;
            }
        }
        return null;
    }

    void checkCommentRequest(String username, long productId, Comment comment) {
        AdminController adminController=new AdminController();
        for (Request request : adminController.showAcceptionRequest()) {
            if (request.getCustomer().getUsername().equals(username) && request.getText().equals("Comment request")) {
                if (request.getCommentSituation().equals(CommentSituation.ACCEPTED)) {
                    makeProduct(productId).getComments().add(comment);
                    comment.setStatus(CommentSituation.ACCEPTED);
                } else if (request.getCommentSituation().equals(CommentSituation.REJECTED)) {
                    comment.setStatus(CommentSituation.REJECTED);
                } else {
                    comment.setStatus(CommentSituation.WAITING);
                }
            }
        }
    }

    public ArrayList<Comment> showComments(String username, long productId, Comment comment) {
        checkCommentRequest(username, productId, comment);
        return makeProduct(productId).getComments();
    }









    boolean regexCard(String cardNumber, String cvv2, String password) {
        Pattern patternNumber = Pattern.compile("\\d{16}");
        Matcher matcherNumber = patternNumber.matcher(cardNumber);
        Pattern patternCvv21 = Pattern.compile("\\d{4}");
        Matcher matcherCvv21 = patternCvv21.matcher(cvv2);
        Pattern patternCvv22 = Pattern.compile("\\d{3}");
        Matcher matcherCvv22 = patternCvv22.matcher(cvv2);
        Pattern patternPass = Pattern.compile("\\d");
        Matcher matcherPass = patternPass.matcher(password);
        return matcherNumber.find() && (matcherCvv21.find() || matcherCvv22.find()) && matcherPass.find();
    }

    public double chargeRequest(Customer customer, String cardNumber, String cvv2, String password, double money) {
        if (regexCard(cardNumber, cvv2, password)) {
            request("Charge credit card request!",customer);
            return money;
        }
        return 0;
    }

    void checkChargeRequest(Customer customer, double money) {
        AdminController adminController=new AdminController();
        for (Request request : adminController.showAcceptionRequest()) {
            if (request.getCustomer()==customer && request.getText().equals("Charge credit card request!")) {
                if (request.getAcception()) {
                    customer.setProperty(money);
                }
            }
        }
    }

    public String chargingResult(Customer customer, double money) {
        checkChargeRequest(customer, money);
        return customer.toString();
    }
    public String addProductToBasket(long productId, int number, Customer customer) {
        if (customer != null) {
            AdminController adminController=new AdminController();
            if (adminController.showProducts().contains(makeProduct(productId))) {
                if (makeProduct(productId).isAvailable()) {
                    if (makeProduct(productId).getNumberOfAvailable() >= number) {
                        customer.getShoppingbasket().add(makeProduct(productId));
                        makeProduct(productId).setNumberOfProduct(number);
                        return "It was successful!";
                    } else
                        return "Sorry There are " + makeProduct(productId).getNumberOfAvailable() + " items available!";
                }
                return "Sorry This isn't exist ";
            }
            return "Sorry This isn't available in the shop!";
        }
        return "You didn't logged in!";
    }

    public boolean removeProductFromBasket(long productId, Customer customer) {
        if (customer!= null) {
            AdminController adminController=new AdminController();
            if (adminController.showProducts().contains(makeProduct(productId))) {
                customer.getShoppingbasket().remove(makeProduct(productId));
                makeProduct(productId).setNumberOfProduct(0);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Product> viewBasket(Customer customer) {
        return customer.getShoppingbasket();
    }

    public boolean buyBasket(Customer customer, String date) {
        double cost = 0;
        for (Product product : customer.getShoppingbasket()) {
            cost = product.getPrice();
        }
        if (customer.getProperty() <= cost) {
            ShoppingFactor factor = new ShoppingFactor();
            factor.setter(date, cost);
            for (Product product : customer.getShoppingbasket()) {
                product.setNumberOfAvailable(product.getNumberOfAvailable()-product.getNumberOfProduct());
                factor.getBoughtProducts().add(product);
                return true;
            }
        }
        return false;
    }
    public ArrayList<ShoppingFactor> showFactors(Customer customer) {
        return customer.getShoppingHistory();
    }
}
