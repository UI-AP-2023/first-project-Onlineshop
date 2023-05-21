package controller.user;

import controller.admin.AdminController;
import exceptions.*;
import model.products.Discount;
import model.products.Product;
import model.products.ShoppingFactor;
import model.users.Admin;
import model.users.Customer;
import model.users.Request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasketController {

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

    public boolean chargeRequest(Customer customer, String number, String cvv2, String password, double money) throws InvalidInformationExceptions {
        if (regexCard(number, cvv2, password)) {
            Request request = new Request();
            request.setUsername(customer.getUsername());
            request.setText("Charge credit card request!");
            request.setMoney(money);
            Admin.getAdmin().getRequests().add(request);

            return true;
        }
        throw new InvalidInformationExceptions();
    }

    public String addProductToBasket(long productId, int number, Customer customer) throws AvailableProductExceptions, InvalidLogin, NoProductExceptions {
        if (customer != null) {
            AdminController adminController = new AdminController();
            ProductController productController = new ProductController();
            if (adminController.showProducts().contains(productController.makeProduct(productId))) {
                if (productController.makeProduct(productId).isAvailable()) {
                    if (productController.makeProduct(productId).getNumberOfAvailable() >= number) {
                        customer.getShoppingbasket().add(productController.makeProduct(productId));
                        productController.makeProduct(productId).setNumberOfProduct(number);
                        return "It was successful!";
                    } else
                        throw new AvailableProductExceptions();
                }
                throw new NoProductExceptions();
            }
            throw new AvailableProductExceptions();
        }
        throw new InvalidLogin();
    }

    public boolean removeProductFromBasket(long productId, Customer customer) throws InvalidLogin {
        if (customer != null) {
            AdminController adminController = new AdminController();
            ProductController productController = new ProductController();
            if (adminController.showProducts().contains(productController.makeProduct(productId))) {
                customer.getShoppingbasket().remove(productController.makeProduct(productId));
                productController.makeProduct(productId).setNumberOfProduct(0);
                return true;
            }
        }
        throw new InvalidLogin();
    }

    public ArrayList<Product> viewBasket(Customer customer) {
        return customer.getShoppingbasket();
    }

    public boolean buyBasket(Customer customer, LocalDate date, String code) throws NoMoneyExceptions {
        double cost = 0;
        ArrayList<Product> endProducts = new ArrayList<>();
        for (Product product : customer.getShoppingbasket()) {
            cost += (product.getPrice() * product.getNumberOfProduct());
        }

        double disCost=cost;

        try {
            disCost=useDiscount(code, customer,cost);
        } catch (DiscountExceptions discountExceptions) {
            System.out.println(discountExceptions.toString());
        }
        if (customer.getProperty() >= disCost) {
            ShoppingFactor factor = new ShoppingFactor();
            for (Product product : customer.getShoppingbasket()) {
                product.setNumberOfAvailable(product.getNumberOfAvailable() - product.getNumberOfProduct());
                factor.setter(date, disCost);
                factor.getBoughtProducts().add(product);
                customer.getShoppingHistory().add(factor);
                endProducts.add(product);
                customer.setProperty(customer.getProperty() - disCost);

            }
            for (Product product : endProducts) {
                customer.getShoppingbasket().remove(product);
            }
            return true;
        }
        throw new NoMoneyExceptions();
    }

    public double useDiscount(String code, Customer customer, double cost) throws DiscountExceptions {
        for (Discount discount : customer.getDiscounts()) {
            if (discount.getCode().equals(code) &&
                    (discount.getLimitDate().isBefore(java.time.LocalDate.now()) | discount.getLimitDate().isEqual(java.time.LocalDate.now()))
                    && discount.getCapacity() >= discount.getCounter()) {
                return cost * ((100 - discount.getPercent()) / 100);

            } else {
                throw new DiscountExceptions();
            }
        }
        return cost;
    }
}
