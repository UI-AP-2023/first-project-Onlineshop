package view;

import controller.admin.AdminController;
import exceptions.InvalidAdminTasks;
import model.users.Admin;
import model.users.Customer;
import model.users.Request;


import java.util.Scanner;

public class AdminPage {
    private Scanner scanner;
    boolean exit = true;

    public AdminPage() {
        scanner = new Scanner(System.in);
    }

    public void commands() {
        AdminController adminController = new AdminController();
        System.out.println("----------------------------------------------------------------");
        System.out.println("You can entered commands like following formats( '' means exactly ):");
        System.out.println("    'add'+'(car|USB|SSD|computer|pen|pencil|notebook|bicycle|food)'+features");
        System.out.println("    'edit'+productId+ newName + newPrice +newNumberOfAvailable");
        System.out.println("    'delete'+productId");
        System.out.println("    'request'+'(true|false)'+numberOfAcceptions");
        System.out.println("    'info'+productId");
        System.out.println("    'showReq'");
        System.out.println("    'customers'");
        System.out.println("    'exit'");
        System.out.println("----------------------------------------------------------------");

        do {
            System.out.println("Enter your command please:");
            String command = scanner.nextLine();
            switch (command) {
                case "showReq" -> {
                    for (Request request : Admin.getAdmin().getRequests()) {
                        System.out.println("Username: " + request.toString());
                    }
                }
                case "customers" -> {
                    for (Customer customer : adminController.showCustomers()) {
                        System.out.println(customer.toString());
                    }
                }
                case "exit" -> this.exit = false;
                default -> {
                    try {
                        System.out.println(adminController.orders(command));
                    } catch (InvalidAdminTasks invalidAdminTasks) {
                        System.out.println(invalidAdminTasks.toString());
                    }
                }
            }
        } while (exit);
        Main main = new Main();
        main.mainPage();
    }

}
