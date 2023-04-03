package view;

import controller.UserController;

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
        String username;
        do {
            System.out.println("Enter your username(It must be unique)  'Enter 0 to exit'  :");
            username = scanner.nextLine();
            if (username.equals("0")) {
                main.mainPage();
                break;
            }
            userController.setUsername(username);
        } while ((userController.setUsername(username)) != null);
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

        String phoneNumber;
        do {
            System.out.println("Enter your phone number please(It must be signed up for one account)   'Enter 0 to exit' :");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.equals("0")) {
                main.mainPage();
                break;
            }
            userController.setPhoneNumber(phoneNumber);
        } while ((userController.setPhoneNumber(phoneNumber)) != null);
        System.out.println("----------------------------------------------------------------");

        String email;
        do {
            System.out.println("Enter your email address please(It must be signed up for one account)   'Enter 0 to exit' :");
            email = scanner.nextLine();
            if(email.equals("0")){
                main.mainPage();
                break;
            }
            userController.setEmail(email);
        } while ((userController.setEmail(email)) != null);

        userController.signupRequest(username, password, phoneNumber, email);
        main.mainPage();
    }
}
