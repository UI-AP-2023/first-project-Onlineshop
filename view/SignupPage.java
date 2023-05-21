package view;

import controller.user.UserController;
import exceptions.InvalidEmailExceptions;
import exceptions.InvalidLogin;
import exceptions.InvalidPhoneNumberExceptions;
import exceptions.InvalidUserName;

import java.util.Scanner;

public class SignupPage {
    private Scanner scanner;

    public SignupPage() {
        scanner = new Scanner(System.in);
    }

    Main main = new Main();

    public void signupMenu() {
        UserController userController = new UserController();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Enter your username(It must be unique)  'Enter 0 to exit'  :");
        String username = scanner.nextLine();
        if (username.equals("0")) {
            main.mainPage();
        }
        try {
            userController.setUsername(username);
        } catch (InvalidUserName invalidUserName) {
            System.out.println(invalidUserName.toString());
            signupMenu();
        }
        System.out.println("----------------------------------------------------------------");
        String password;
        do {
            System.out.println("Enter your password please(It must be more than 7)  'Enter 0 to exit'  :");
            password = scanner.nextLine();
            if (password.equals("0")) {
                main.mainPage();
                break;
            }

        } while (!(userController.setPassword(password)));

        System.out.println("----------------------------------------------------------------");
        System.out.println("Enter your phone number please(It must be signed up for one account)   'Enter 0 to exit' :");
        String phoneNumber = scanner.nextLine();
        if (phoneNumber.equals("0")) {
            main.mainPage();
        }
        try {
            userController.setPhoneNumber(phoneNumber);
        } catch (InvalidPhoneNumberExceptions invalidPhoneNumberExceptions) {
            System.out.println(invalidPhoneNumberExceptions.toString());
            signupMenu();
        }
        System.out.println("----------------------------------------------------------------");
        System.out.println("Enter your email address please(It must be signed up for one account)   'Enter 0 to exit' :");
        String email = scanner.nextLine();
        if (email.equals("0")) {
            main.mainPage();
        }
        try {

            userController.setEmail(email);
        } catch (InvalidEmailExceptions invalidEmailExceptions) {
            System.out.println(invalidEmailExceptions.toString());
            signupMenu();
        }

        userController.signupRequest(username, password, phoneNumber, email);
        main.mainPage();
    }
}
