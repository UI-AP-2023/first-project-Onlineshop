package view;

import controller.user.BasketController;
import controller.user.UserController;
import exceptions.*;
import model.products.Product;
import model.products.ShoppingFactor;
import model.users.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserPage {
    private Customer onlineCustomer;
    private Scanner scanner;

    public void setOnlineCustomer(Customer onlineCustomer) {
        this.onlineCustomer = onlineCustomer;
    }

    public Customer getOnlineCustomer() {
        return this.onlineCustomer;
    }

    UserController userController = new UserController();
    BasketController basketController = new BasketController();

    Main main = new Main();

    public UserPage() {
        scanner = new Scanner(System.in);
    }

    public void menu() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.View your information and edit them.");
        System.out.println("    2.View shop.");
        System.out.println("    3.View basket's shop,add a product to basket,buy products.");
        System.out.println("    4.Charge credit card.");
        System.out.println("    5.View factors.");
        System.out.println("    6.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer = scanner.nextInt();
        switch (answer) {
            case 1 -> informationSettings();
            case 2 -> {
                ShopPage shopPage = new ShopPage();
                shopPage.shopMenu();
            }
            case 3 -> basketSettings();
            case 4 -> {
                CreditCardPage creditCardPage = new CreditCardPage();
                creditCardPage.charging(getOnlineCustomer());
            }
            case 5 -> {
                for (ShoppingFactor factor : getOnlineCustomer().getShoppingHistory()) {
                    System.out.println(factor.toString());
                    for (Product product : factor.getBoughtProducts()) {
                        System.out.println(product.toString());
                    }
                }
                menu();
            }
            case 6 -> main.mainPage();
            default -> menu();
        }
    }

    void informationSettings() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("1.Show information.");
        System.out.println("2.Edit information.");
        System.out.println("3.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer = scanner.nextInt();

        switch (answer) {
            case 1 -> {
                if (getOnlineCustomer() != null) {
                    System.out.println(getOnlineCustomer().toString());
                }
                informationSettings();
            }
            case 2 -> editInfo();

            case 3 -> menu();
            default -> informationSettings();
        }
    }

    void editInfo() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Enter your previous password:");
        String prePassword = scanner.next();
        System.out.println("----------------------------------------------------------------");
        String password;
        do {
            System.out.println("Enter your password please(It must be more than 7)  'Enter 0 to exit'  :");
            password = scanner.next();
            if (password.equals("0")) {
                informationSettings();
                break;
            }

        } while (!(userController.setPassword(password)));

        System.out.println("----------------------------------------------------------------");
        System.out.println("Enter your phone number please(It must be signed up for one account)   'Enter 0 to exit' :");
        String phoneNumber = scanner.next();
        if (phoneNumber.equals("0")) {
            informationSettings();
        }
        try {
            userController.setPhoneNumber(phoneNumber);
        } catch (InvalidPhoneNumberExceptions invalidPhoneNumberExceptions) {
            System.out.println(invalidPhoneNumberExceptions.toString());
            editInfo();
        } finally {
            System.out.println("Good Luck!");
        }
        System.out.println("----------------------------------------------------------------");
        System.out.println("Enter your email address please(It must be signed up for one account)   'Enter 0 to exit' :");
        String email = scanner.next();
        if (email.equals("0")) {
            informationSettings();
        }
        try {
            userController.setEmail(email);
        } catch (InvalidEmailExceptions invalidEmailExceptions) {
            System.out.println(invalidEmailExceptions.toString());
            editInfo();
        } finally {
            System.out.println("Good Luck!");
        }
        if (userController.changeInfo(getOnlineCustomer(), prePassword, password, phoneNumber, email)) {
            menu();
        } else {
            System.out.println("Your password is incorrect!\nIf you want to exit enter '0' and if you want to continue enter '10':");
            int answerExit = scanner.nextInt();
            if (answerExit == 0) {
                informationSettings();
            } else if (answerExit == 10) {
                editInfo();
            }
        }
    }

    void basketSettings() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("1.View basket's buy.");
        System.out.println("2.Add products to basket.");
        System.out.println("3.Delete product from basket.");
        System.out.println("4.Buy products.");
        System.out.println("5.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer = scanner.nextInt();

        switch (answer) {
            case 1: {
                for (Product product : basketController.viewBasket(getOnlineCustomer())) {
                    System.out.println(product.toString());
                }
                basketSettings();
                break;
            }
            case 2: {
                addProduct();
                break;
            }
            case 3: {
                deleteProduct();
                break;
            }
            case 4: {
                buy();
            }
            case 5: {
                menu();
                break;
            }
            default: {
                basketSettings();
                break;
            }
        }

    }

    void addProduct() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter product's ID.");
        long productId = scanner.nextInt();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter number of products.");
        int number = scanner.nextInt();
        System.out.println("----------------------------------------------------------------");
        String add;
        try {
            add = basketController.addProductToBasket(productId, number, getOnlineCustomer());
            System.out.println(add);
            basketSettings();
        } catch (AvailableProductExceptions availableProductExceptions) {
            System.out.println(availableProductExceptions.toString());
            System.out.println("If you want to exit enter '0' and if you want to continue enter '10':");
            int answerExit = scanner.nextInt();
            if (answerExit == 0) {
                basketSettings();
            } else if (answerExit == 10) {
                addProduct();
            }
        } catch (InvalidLogin invalidLogin) {
            System.out.println(invalidLogin.toString());
            System.out.println("If you want to exit enter '0' and if you want to continue enter '10':");
            int answerExit = scanner.nextInt();
            if (answerExit == 0) {
                basketSettings();
            } else if (answerExit == 10) {
                addProduct();
            }
        } catch (NoProductExceptions noProductExceptions) {
            System.out.println(noProductExceptions.toString());
            System.out.println("If you want to exit enter '0' and if you want to continue enter '10':");
            int answerExit = scanner.nextInt();
            if (answerExit == 0) {
                basketSettings();
            } else if (answerExit == 10) {
                addProduct();
            }
        } finally {
            System.out.println("Good Luck!");
        }
    }

    void deleteProduct() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter product's ID.");
        int productId = scanner.nextInt();
        System.out.println("----------------------------------------------------------------");
        try {
            basketController.removeProductFromBasket(productId, getOnlineCustomer());
            basketSettings();
        } catch (InvalidLogin invalidLogin) {
            System.out.println(invalidLogin.toString());
            System.out.println("If you want to exit enter '0' and if you want to continue enter '10':");
            int answerExit = scanner.nextInt();
            if (answerExit == 0) {
                basketSettings();
            } else if (answerExit == 10) {
                deleteProduct();
            }
        } finally {
            System.out.println("Good Luck!");
        }
    }

    void buy() {
        System.out.println("----------------------------------------------------------------");
        scanner.nextLine();
        System.out.println("Please enter date of today:");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.println("----------------------------------------------------------------");
        try {
            basketController.buyBasket(getOnlineCustomer(), date);
            basketSettings();
        } catch (NoMoneyExceptions noMoneyExceptions) {
            System.out.println(noMoneyExceptions.toString());
            System.out.println("If you want to exit enter '0' and if you want to continue enter '10':");
            int answerExit = scanner.nextInt();
            if (answerExit == 0) {
                basketSettings();
            } else if (answerExit == 10) {
                buy();
            }
        } finally {
            System.out.println("Good Luck!");
        }

    }
}
