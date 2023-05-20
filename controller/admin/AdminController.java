package controller.admin;

import controller.user.ProductController;
import model.products.*;
import model.users.Admin;
import model.users.Customer;
import model.users.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AdminController {

    public String regexOrder(String command) {
        Pattern pattern = Pattern.compile("^(add|edit|delete|showReq|customers|request|info)");
        Matcher matcher = pattern.matcher(command);
        List<MatchResult> matches = matcher.results().toList();
        for (MatchResult match : matches) {
            return match.group();
        }
        return "Error!";
    }

    public String regexProduct(String command) {
        Pattern pattern = Pattern.compile("(car|USB|SSD|computer|pen|pencil|notebook|bicycle|food)");
        Matcher matcher = pattern.matcher(command);
        List<MatchResult> matches = matcher.results().toList();
        for (MatchResult match : matches) {
            return match.group();
        }
        return "Error!";
    }

    public String orders(String command) {
        if (regexOrder(command).equals("add")) {
            if (regexProduct(command).equals("car")) {
                return addCar(command);
            }
            if (regexProduct(command).equals("USB")) {
                return addUSB(command);
            }
            if (regexProduct(command).equals("SSD")) {
                return addSSD(command);
            }
            if (regexProduct(command).equals("computer")) {
                return addComputer(command);
            }
            if (regexProduct(command).equals("pen")) {
                return addPen(command);
            }
            if (regexProduct(command).equals("pencil")) {
                return addPencil(command);
            }
            if (regexProduct(command).equals("notebook")) {
                return addNotebook(command);
            }
            if (regexProduct(command).equals("bicycle")) {
                return addBicycle(command);
            }
            if (regexProduct(command).equals("food")) {
                return addFood(command);
            }
        }
        if (regexOrder(command).equals("edit")) {
            return editProduct(command);
        }
        if (regexOrder(command).equals("delete")) {
            return deleteProduct(command);
        }
        if (regexOrder(command).equals("request")) {
            return manageRequest(command);
        }
        if (regexOrder(command).equals("info")) {
            return showProductInfo(command);
        }
        return "false";
    }

    String addCar(String command) {
        String[] order = command.split("\\s");
        if (order.length == 8) {
            Automobile car = new Automobile();
            car.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], order[6], Boolean.parseBoolean(order[7]));
            Admin.getAdmin().getProducts().add(car);
            return "true";
        } else
            return "false";
    }

    String addUSB(String command) {
        String[] order = command.split("\\s");
        if (order.length == 9) {
            FlashMemory flashMemory = new FlashMemory();
            flashMemory.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), Double.parseDouble(order[5]), order[6], Integer.parseInt(order[7]), order[8]);
            Admin.getAdmin().getProducts().add(flashMemory);
            return "true";
        } else
            return "false";
    }

    String addSSD(String command) {
        String[] order = command.split("\\s");
        if (order.length == 8) {
            SSD ssd = new SSD();
            ssd.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), Double.parseDouble(order[5]), order[6], Integer.parseInt(order[7]));
            Admin.getAdmin().getProducts().add(ssd);
            return "true";
        } else
            return "false";
    }

    String addComputer(String command) {
        String[] order = command.split("\\s");
        if (order.length == 9) {
            Computer pc = new Computer();
            pc.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), Double.parseDouble(order[5]), order[6], order[7], order[8]);
            Admin.getAdmin().getProducts().add(pc);
            return "true";
        } else
            return "false";
    }

    String addPen(String command) {
        String[] order = command.split("\\s");
        if (order.length == 7) {
            Pen pen = new Pen();
            pen.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], Color.valueOf(order[6]));
            Admin.getAdmin().getProducts().add(pen);
            return "true";
        } else
            return "false";
    }

    String addPencil(String command) {
        String[] order = command.split("\\s");
        if (order.length == 7) {
            Pencil pencil = new Pencil();
            pencil.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], PencilType.valueOf(order[6]));
            Admin.getAdmin().getProducts().add(pencil);
            return "true";
        } else
            return "false";
    }

    String addNotebook(String command) {
        String[] order = command.split("\\s");
        if (order.length == 8) {
            Notebook notebook = new Notebook();
            notebook.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], Integer.parseInt(order[6]), PageType.valueOf(order[7]));
            Admin.getAdmin().getProducts().add(notebook);
            return "true";
        } else
            return "false";
    }

    String addBicycle(String command) {
        String[] order = command.split("\\s");
        if (order.length == 7) {
            Bicycle bicycle = new Bicycle();
            bicycle.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], BicycleType.valueOf(order[6]));
            Admin.getAdmin().getProducts().add(bicycle);
            return "true";
        } else return "false";
    }

    String addFood(String command) {
        String[] order = command.split("\\s");
        if (order.length == 7) {
            FoodProduct food = new FoodProduct();
            food.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], order[6]);
            Admin.getAdmin().getProducts().add(food);
            return "true";
        } else return "false";
    }


    String editProduct(String command) {
        String[] order = command.split("\\s");
        if (order.length == 5) {
            long id = Long.parseLong(order[1]);
            for (Product product : Admin.getAdmin().getProducts()) {
                if (product.getId() == id) {
                    product.setName(order[2]);
                    product.setPrice(Long.parseLong(order[3]));
                    product.setNumberOfAvailable(Integer.parseInt(order[4]));
                    return "true";
                }
            }
        }
        return "false";
    }

    String deleteProduct(String command) {
        String[] order = command.split("\\s");
        if (order.length == 2) {
            long id = Long.parseLong(order[1]);
            for (Product product : Admin.getAdmin().getProducts()) {
                if (product.getId() == id) {
                    Admin.getAdmin().getProducts().remove(product);
                    return "true";
                }
            }
        }
        return "false";
    }

    public String showProductInfo(String command) {
        String[] order = command.split("\\s");
        if (order.length == 2) {
            for (Product product : Admin.getAdmin().getProducts()) {
                if (product.getId() == (Long.parseLong(order[1]))) {
                    return product.toString();
                }
            }
        }
        return "Error!";
    }


    String manageRequest(String command) {
        String[] order = command.split("\\s");
        ArrayList<Request> endRequest = new ArrayList<>();
        if (order.length == 3) {
            if (Integer.parseInt(order[2]) > Admin.getAdmin().getRequests().size()) {
                return "It is more than number of requests!";
            }
            for (int i = 0; i < Integer.parseInt(order[2]); i++) {
                Admin.getAdmin().getRequests().get(i).setAcception(Boolean.parseBoolean(order[1]));
                if (Admin.getAdmin().getRequests().get(i).getAcception()) {
                    Admin.getAdmin().getRequests().get(i).setCommentSituation(CommentSituation.ACCEPTED);
                    Admin.getAdmin().getAcceptedRequest().add(Admin.getAdmin().getRequests().get(i));
                    endRequest.add(Admin.getAdmin().getRequests().get(i));

                } else {
                    Admin.getAdmin().getRequests().get(i).setCommentSituation(CommentSituation.REJECTED);
                    endRequest.add(Admin.getAdmin().getRequests().get(i));
                }
                if (Admin.getAdmin().getRequests().get(i).getUsername() == null) {
                    checkCommentRequest();
                } else {
                    checkChargeRequest();
                    checkSignupRequest(Admin.getAdmin().getRequests().get(i).getUsername());
                    checkCommentRequest();
                }
            }
            for (Request request : endRequest) {
                Admin.getAdmin().getRequests().remove(request);
            }
            return "true";
        }
        return "false";
    }


    public ArrayList<Customer> showCustomers() {
        return Admin.getAdmin().getCustomers();
    }

    public ArrayList<Product> showProducts() {
        return Admin.getAdmin().getProducts();
    }

    public ArrayList<Request> showAcceptionRequest() {
        return Admin.getAdmin().getAcceptedRequest();
    }

    void checkSignupRequest(String username) {
        for (Request request : showAcceptionRequest()) {
            if (request.getUsername().equals(username) && request.getText().equals("sign up request.")) {
                if (request.getAcception()) {
                    showCustomers().add(request.getCustomer());
                }
            }
        }
    }

    void checkCommentRequest() {
        ProductController productController = new ProductController();
        for (Request request : showAcceptionRequest()) {
            if (request.getText().equals("Comment request")) {
                if (request.getCommentSituation().equals(CommentSituation.ACCEPTED)) {
                    productController.makeProduct(request.getComment().getProductId()).getComments().remove(request.getComment());
                    productController.makeProduct(request.getComment().getProductId()).getComments().add(request.getComment());
                    request.getComment().setStatus(CommentSituation.ACCEPTED);
                } else if (request.getCommentSituation().equals(CommentSituation.REJECTED)) {
                    request.getComment().setStatus(CommentSituation.REJECTED);
                } else {
                    request.getComment().setStatus(CommentSituation.WAITING);
                }
            }
        }
    }

    void checkChargeRequest() {
        for (Request request : showAcceptionRequest()) {
            if (request.getText().equals("Charge credit card request!")) {
                for(Customer customer : Admin.getAdmin().getCustomers()){
                    if(request.getUsername().equals(customer.getUsername())){
                        request.setCustomer(customer);
                    }
                }
                if (request.getAcception()) {
                    request.getCustomer().setProperty(request.getMoney());
                }
            }
        }
    }
}
