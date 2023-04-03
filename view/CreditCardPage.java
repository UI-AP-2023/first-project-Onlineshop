package view;

import controller.BasketController;
import model.users.Customer;

import java.util.Scanner;

public class CreditCardPage {
    private Scanner scanner;
    UserPage userPage=new UserPage();

    public CreditCardPage() {
        scanner = new Scanner(System.in);
    }
    public void charging(Customer onlineCustomer){

        System.out.println("Please enter your credit card's number:");
        String number=scanner.next();
        System.out.println("Please enter your credit card's Cvv2:");
        String cvv2=scanner.next();
        System.out.println("Please enter your credit card's password:");
        String password=scanner.next();
        System.out.println("Please enter money:");
        double money=scanner.nextDouble();

        BasketController basketController=new BasketController();

        if(basketController.chargeRequest(onlineCustomer,number,cvv2,password,money)){
            System.out.println("Your request has been sent.");
            userPage.menu();
        }
        else{
            System.out.println("Your information is incorrect!!!!");
            System.out.println("If you want to exit enter '0' and if you want to try again enter '10':");
            int answerExit=scanner.nextInt();
            if (answerExit==0){
                userPage.menu();
            }
            else if (answerExit==10){
                charging(onlineCustomer);
            }

        }


    }
}
