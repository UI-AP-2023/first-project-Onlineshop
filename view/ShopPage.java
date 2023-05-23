package view;

import controller.admin.AdminController;
import controller.user.ProductController;
import model.exceptions.AvailableProductExceptions;
import model.products.*;
import model.users.Customer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ShopPage {
    private Scanner scanner;
    private int minIndex = 0;
    private int maxIndex = 10;
    private Customer onlineCustomer;

    public void setOnlineCustomer(Customer onlineUsername) {
        this.onlineCustomer = onlineUsername;
    }

    public Customer getOnlineCustomer() {
        return onlineCustomer;
    }

    AdminController adminController = new AdminController();
    ProductController productController = new ProductController();

    public ShopPage() {
        scanner = new Scanner(System.in);
    }

    public void shopMenu() {
        System.out.println("                     WELCOME!");
        showTenProducts(adminController.showProducts());
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.Search Products.");
        System.out.println("    2.More information about a product.");
        System.out.println("    3.Filter products.");
        System.out.println("    4.Sort products.");
        System.out.println("    5.previous page.");
        System.out.println("    6.next page.");
        System.out.println("    7.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            shopMenu();
        }
        ProductController productController = new ProductController();

        switch (answer) {
            case 1 -> searchMenu();
            case 2 -> visit();
            case 3 -> filterMenu();
            case 4 -> {
                showTenProducts(productController.sorting(adminController.showProducts()));
                shopMenu();
            }
            case 5 -> {
                this.minIndex -= 10;
                this.maxIndex -= 10;
                if (minIndex < 0) {
                    System.out.println("It's the first page!");
                    this.minIndex = 0;
                    this.maxIndex = 10;
                }
                showTenProducts(adminController.showProducts());
                shopMenu();
            }
            case 6 -> {
                this.minIndex += 10;
                this.maxIndex += 10;
                showTenProducts(adminController.showProducts());
                shopMenu();
            }
            case 7 -> {
                if (getOnlineCustomer() != null) {
                    UserPage userPage = new UserPage();
                    userPage.menu();
                } else {
                    Main main = new Main();
                    main.mainPage();
                }
            }
            default -> shopMenu();
        }

    }

    void showTenProducts(ArrayList<Product> products) {
        int counter = 0;
        if (this.minIndex < products.size()) {
            for (Product product : products) {
                if (this.minIndex <= counter) {
                    System.out.println(product.getBaseInfo());
                    System.out.println("----------------------------------------------------------------");
                } else if (this.maxIndex == counter) {
                    break;
                }
                counter++;
            }
        }
    }

    void removeFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Do you want to remove it?         1.Yes, 2.No");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            removeFilter();
        }
        switch (answer) {
            case 1 -> {
                for (Product product : adminController.showProducts()) {
                    System.out.println(product.getBaseInfo());
                }
            }
            default -> filterMenu();
        }
    }

    void searchMenu() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.Search Products by name.");
        System.out.println("    2.Search Products by ID.");
        System.out.println("    3.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            searchMenu();
        }

        switch (answer) {
            case 1 -> {
                nameSearcher();
                searchMenu();
            }
            case 2 -> {
                idSeacher();
                searchMenu();
            }
            case 3 -> shopMenu();
            default -> searchMenu();
        }
    }

    void idSeacher() {
        System.out.println("Please enter the ID of the product:");
        long productId=0;
        try {
            productId =  scanner.nextLong();
        }catch (InputMismatchException inputMismatchException){
            idSeacher();
        }
        try {
            System.out.println(productController.searchProductById(productId));
        } catch (AvailableProductExceptions availableProductExceptions) {
            System.out.println(availableProductExceptions.toString());
        } finally {
            System.out.println("Good Luck!");
        }
    }

    void nameSearcher() {
        scanner.nextLine();
        System.out.println("Please enter the name of the product:");
        String productName=null;
        try {
            productName =  scanner.nextLine();
        }catch (InputMismatchException inputMismatchException){
            nameSearcher();
        }
        for (Product product : productController.searchProductsByName(productName)) {
            System.out.println(product.getBaseInfo());
        }

    }

    void filterMenu() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.Filter by category(DIGITAL,STATIONERY,VEHICLE,FOOD).");
        System.out.println("    2.Filter by range price.");
        System.out.println("    3.Filter by availability.");
        System.out.println("    4.Filter by range average of score.");
        System.out.println("    5.Filter in digital products.");
        System.out.println("    6.Filter in stationery products.");
        System.out.println("    7.Filter in vehicle products.");
        System.out.println("    8.Filter in food products.");
        System.out.println("    9.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            filterMenu();
        }

        switch (answer) {
            case 1 -> categoryFilter();
            case 2 -> {
                priceFilter();
                filterMenu();
            }
            case 3 -> {
                availableFilter();
                filterMenu();
            }
            case 4 -> {
                averageFilter();
                filterMenu();

            }
            case 5 -> digitalFilter();
            case 6 -> stationeryFilter();
            case 7 -> vehicleFilter();
            case 8 -> foodFilter();
            case 9 -> shopMenu();
            default -> filterMenu();
        }
    }

    void categoryFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.Filter by DIGITAL.");
        System.out.println("    2.Filter by STATIONERY.");
        System.out.println("    3.Filter by VEHICLE.");
        System.out.println("    4.Filter by FOOD.");
        System.out.println("    5.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            categoryFilter();
        }

        switch (answer) {
            case 1 -> {
                for (Product product : productController.filterByCategory(Category.DIGITAL)) {
                    System.out.println(product.getBaseInfo());
                }
                removeFilter();
                filterMenu();
            }
            case 2 -> {
                for (Product product : productController.filterByCategory(Category.STATIONERY)) {
                    System.out.println(product.getBaseInfo());
                }
                removeFilter();
                filterMenu();

            }
            case 3 -> {
                for (Product product : productController.filterByCategory(Category.VEHICLE)) {
                    System.out.println(product.getBaseInfo());
                }
                removeFilter();
                filterMenu();

            }
            case 4 -> {
                for (Product product : productController.filterByCategory(Category.FOOD)) {
                    System.out.println(product.getBaseInfo());
                }
                removeFilter();
                filterMenu();

            }
            case 5 -> filterMenu();
            default -> {
                categoryFilter();
            }
        }
    }

    void averageFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter minimum average(0-5):");
        float minAvg =0;
        try {
            minAvg = scanner.nextFloat();
        }catch (InputMismatchException inputMismatchException){
            averageFilter();
        }
        System.out.println("Please enter average (0-5):");
        float maxAvg =0;
        try {
            maxAvg = scanner.nextFloat();
        }catch (InputMismatchException inputMismatchException){
            averageFilter();
        }
        for (Product product : productController.filterByAverage(minAvg, maxAvg)) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }



    void priceFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter minimum price:");
        long minPrice = 0;
        try {
            minPrice = scanner.nextLong();
        }catch (InputMismatchException inputMismatchException){
            priceFilter();
        }
        System.out.println("Please enter maximum price:");
        long maxPrice = 0;
        try {
            maxPrice = scanner.nextLong();
        }catch (InputMismatchException inputMismatchException){
            priceFilter();
        }
        for (Product product : productController.filterByPrice(minPrice, maxPrice)) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void availableFilter() {
        for (Product product : productController.filterByAvailable()) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void visit() {
        System.out.println("Please enter product's ID:");
        long productId =0;
        try {
            productId = scanner.nextLong();
        }catch (InputMismatchException inputMismatchException){
            visit();
        }
        ProductPage productPage = new ProductPage();
        productPage.visitProduct(productId, getOnlineCustomer());
    }

    void digitalFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.Filter by range of weight.");
        System.out.println("    2.Filter by saving info products.");
        System.out.println("    3.Filter by limitation of saving info products.");
        System.out.println("    4.Filter by flash memory.");
        System.out.println("    5.Filter by SSD.");
        System.out.println("    6.Filter by PCs.");
        System.out.println("    7.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            digitalFilter();
        }

        switch (answer) {
            case 1 -> {
                wightFilter();
                digitalFilter();
            }
            case 2 -> {
                savingInfoFilter();
                digitalFilter();
            }
            case 3 -> {
                limitationFilter();
                digitalFilter();
            }
            case 4 -> {
                memoryFilter();
                digitalFilter();
            }
            case 5 -> {
                ssdFilter();
                digitalFilter();
            }
            case 6 -> {
                pcFilter();
                digitalFilter();
            }
            case 7 -> filterMenu();
            default -> digitalFilter();
        }
    }

    void stationeryFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.Filter by country.");
        System.out.println("    2.Filter by notebooks.");
        System.out.println("    3.Filter by pen.");
        System.out.println("    4.Filter by pencil.");
        System.out.println("    5.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            stationeryFilter();
        }
        switch (answer) {
            case 1 -> {
                countryFilter();
                stationeryFilter();
            }
            case 2 -> {
                notebookFilter();
                stationeryFilter();
            }
            case 3 -> {
                penFilter();
                stationeryFilter();
            }
            case 4 -> {
                pencilFilter();
                stationeryFilter();
            }
            case 5 -> filterMenu();
            default -> stationeryFilter();
        }

    }

    void vehicleFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.Filter by company.");
        System.out.println("    2.Filter by automobile.");
        System.out.println("    3.Filter automobile by being automatic .");
        System.out.println("    4.Filter by bicycle.");
        System.out.println("    5.Filter by bicycle's type.");
        System.out.println("    6.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            vehicleFilter();
        }
        switch (answer) {
            case 1 -> {
                companyFilter();
                vehicleFilter();
            }
            case 2 -> {
                carFilter();
                vehicleFilter();
            }
            case 3 -> {
                automaticFilter();
                vehicleFilter();
            }
            case 4 -> {
                bicycleFilter();
                vehicleFilter();
            }
            case 5 -> {
                bicycleTypeFilter();
                vehicleFilter();
            }
            case 6 -> filterMenu();
            default -> vehicleFilter();
        }

    }

    void foodFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.Filter by date of production.");
        System.out.println("    2.Filter by date of expiration.");
        System.out.println("    3.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            foodFilter();
        }
        switch (answer) {
            case 1 -> {
                productionFilter();
                foodFilter();
            }
            case 2 -> {
                expirationFilter();
                foodFilter();
            }
            case 3 -> filterMenu();
            default -> foodFilter();
        }

    }

    void wightFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter minimum weight:");
        double minWeight =0;
        try {
            minWeight = scanner.nextDouble();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            wightFilter();
        }
        System.out.println("Please enter maximum weight:");
        double maxWeight =0;
        try {
            maxWeight = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            wightFilter();
        }
        for (DigitalProduct product : productController.filterByDigitalWeight(minWeight, maxWeight)) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void savingInfoFilter() {
        for (SavingInfoProduct product : productController.filterBySavingInoProducts()) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void limitationFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter number of limitation:");
        int limit =0;
        try {
            limit = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            limitationFilter();
        }
        for (SavingInfoProduct product : productController.filterBySavingLimitaton(limit)) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();

    }

    void memoryFilter() {
        for (FlashMemory product : productController.filterByMemory()) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void ssdFilter() {
        for (SSD product : productController.filterBySSD()) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void pcFilter() {
        for (Computer product : productController.filterByPC()) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void countryFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter the name of country:");
        String country =null;
        try {
            country = scanner.next();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            countryFilter();
        }
        for (StationeryProduct product : productController.filterByStationeryCountry(country)) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void notebookFilter() {
        for (Notebook product : productController.filterByNotebook()) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void penFilter() {
        for (Pen product : productController.filterByPen()) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void pencilFilter() {
        for (Pencil product : productController.filterByPencil()) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void companyFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter the name of the company:");
        String company =null;
        try {
            company = scanner.next();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            companyFilter();
        }
        for (VehicleProduct product : productController.filterByVehicleCompany(company)) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void carFilter() {
        for (Automobile product : productController.filterByAutomobile()) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void automaticFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.Filter by Automatic.");
        System.out.println("    2.Filter by Not Automatic.");
        System.out.println("    3.Exit.");
        System.out.println("----------------------------------------------------------------");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            automaticFilter();
        }

        switch (answer) {
            case 1 -> {
                for (Automobile product : productController.filterByAutomatic(true)) {
                    System.out.println(product.getBaseInfo());
                }
                removeFilter();
                vehicleFilter();
            }
            case 2 -> {
                for (Automobile product : productController.filterByAutomatic(false)) {
                    System.out.println(product.getBaseInfo());
                }
                removeFilter();
                vehicleFilter();

            }
            case 3 -> vehicleFilter();
        }
    }

    void bicycleFilter() {
        for (Bicycle product : productController.filterByBicycle()) {
            System.out.println(product.toString());
        }
        removeFilter();
    }

    void productionFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter the date of production:");
        String production =null;
        try {
            production = scanner.next();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            productionFilter();
        }
        for (FoodProduct product : productController.filterByFoodProduction(production)) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void expirationFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter the date of expiration:");
        String exoiration =null;
        try {
            exoiration = scanner.next();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            expirationFilter();
        }
        for (FoodProduct product : productController.filterByFoodExpiration(exoiration)) {
            System.out.println(product.getBaseInfo());
        }
        removeFilter();
    }

    void bicycleTypeFilter() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Select one of the following numbers please:");
        System.out.println("    1.Filter by MOUNTAIN.");
        System.out.println("    2.Filter by URBAN.");
        System.out.println("    3.Filter by ROAD.");
        System.out.println("    4.Filter by HYBRID.");
        System.out.println("    5.Exit.");
        int answer=0;
        try {
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            scanner.nextLine();
            bicycleFilter();
        }

        switch (answer) {
            case 1 -> {
                for (Bicycle product : productController.filterByBicycleType(BicycleType.MOUNTAIN)) {
                    System.out.println(product.getBaseInfo());
                }
                removeFilter();
                vehicleFilter();
            }
            case 2 -> {
                for (Bicycle product : productController.filterByBicycleType(BicycleType.URBAN)) {
                    System.out.println(product.getBaseInfo());
                }
                removeFilter();
                vehicleFilter();

            }
            case 3 -> {
                for (Bicycle product : productController.filterByBicycleType(BicycleType.ROAD)) {
                    System.out.println(product.getBaseInfo());
                }
                removeFilter();
                vehicleFilter();

            }
            case 4 -> {
                for (Bicycle product : productController.filterByBicycleType(BicycleType.HYBRID)) {
                    System.out.println(product.getBaseInfo());
                }
                removeFilter();
                vehicleFilter();

            }
            case 5 -> vehicleFilter();
            default -> bicycleFilter();
        }
    }

}
