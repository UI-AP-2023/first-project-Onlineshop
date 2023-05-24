package view;

import controller.user.UserController;
import model.exceptions.InvalidLogin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginPage {
    private Scanner scanner;
    Main main = new Main();

    public LoginPage() {
        scanner = new Scanner(System.in);
    }


    public void loginMenu() {
        UserController userController = new UserController();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("admin")) {
            AdminPage adminPage = new AdminPage();
            adminPage.commands();
        } else {
            try {
                userController.login(username, password);
                UserPage userPage = new UserPage();
                ShopPage shopPage = new ShopPage();
                userPage.onlineCustomer=userController.login(username, password);
               shopPage. onlineCustomer=userController.login(username, password);
                userPage.menu();
            } catch (InvalidLogin invalidLogin) {
                System.out.println(invalidLogin.toString());
                System.out.println("If you want to exit enter '0' and if you want to continue enter '10':");
                int answerExit =-1;
                try {
                    answerExit = scanner.nextInt();
                }catch (InputMismatchException inputMismatchException){
                    scanner.nextLine();
                    loginMenu();
                }
                if (answerExit == 0) {
                    main.mainPage();
                } else if (answerExit == 10) {
                    scanner.nextLine();
                    loginMenu();
                }

            }finally {
                System.out.println("Good Luck!");
            }

        }
    }
}
