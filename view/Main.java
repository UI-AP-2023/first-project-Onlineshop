package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
    }

    public void mainPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select a number:");
        System.out.println("    1.Sign up");
        System.out.println("    2.Login");
        System.out.println("    3.View shop");
        System.out.println("----------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException e){
            scanner.nextLine();
            mainPage();
        }
        switch (answer) {
            case 1 -> {
                SignupPage signupPage = new SignupPage();
                signupPage.signupMenu();
            }
            case 2 -> {
                LoginPage loginPage = new LoginPage();
                loginPage.loginMenu();
            }
            case 3 -> {
                ShopPage shopPage = new ShopPage();
                shopPage.shopMenu();
            }
            default -> mainPage();
        }
    }
}
