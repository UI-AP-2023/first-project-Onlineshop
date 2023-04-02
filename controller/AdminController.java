package controller;

import model.products.*;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.users.Admin.admin;

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
            admin.getProducts().add(car);
            return "true";
        } else
            return "false";
    }

    String addUSB(String command) {
        String[] order = command.split("\\s");
        if (order.length == 9) {
            FlashMemory flashMemory = new FlashMemory();
            flashMemory.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), Double.parseDouble(order[5]), order[6],Integer.parseInt(order[7]), order[8]);
            admin.getProducts().add(flashMemory);
            return "true";
        } else
            return "false";
    }

    String addSSD(String command) {
        String[] order = command.split("\\s");
        if (order.length == 8) {
            SSD ssd = new SSD();
            ssd.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), Double.parseDouble(order[5]), order[6], Integer.parseInt(order[7]));
            admin.getProducts().add(ssd);
            return "true";
        } else
            return "false";
    }

    String addComputer(String command) {
        String[] order = command.split("\\s");
        if (order.length == 9) {
            Computer pc = new Computer();
            pc.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), Double.parseDouble(order[5]), order[6], order[7], order[8]);
            admin.getProducts().add(pc);
            return "true";
        } else
            return "false";
    }

    String addPen(String command) {
        String[] order = command.split("\\s");
        if (order.length == 7) {
            Pen pen = new Pen();
            pen.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], Color.valueOf(order[6]));
            admin.getProducts().add(pen);
            return "true";
        } else
            return "false";
    }

    String addPencil(String command) {
        String[] order = command.split("\\s");
        if (order.length == 7) {
            Pencil pencil = new Pencil();
            pencil.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], PencilType.valueOf(order[6]));
            admin.getProducts().add(pencil);
            return "true";
        } else
            return "false";
    }

    String addNotebook(String command) {
        String[] order = command.split("\\s");
        if (order.length == 8) {
            Notebook notebook = new Notebook();
            notebook.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], Integer.parseInt(order[6]), PageType.valueOf(order[7]));
            admin.getProducts().add(notebook);
            return "true";
        } else
            return "false";
    }

    String addBicycle(String command) {
        String[] order = command.split("\\s");
        if (order.length == 7) {
            Bicycle bicycle = new Bicycle();
            bicycle.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], BicycleType.valueOf(order[6]));
            admin.getProducts().add(bicycle);
            return "true";
        } else return "false";
    }

    String addFood(String command) {
        String[] order = command.split("\\s");
        if (order.length == 7) {
            FoodProduct food = new FoodProduct();
            food.setter(order[2], Double.parseDouble(order[3]), Integer.parseInt(order[4]), order[5], order[6]);
            admin.getProducts().add(food);
            return "true";
        } else return "false";
    }


    String editProduct(String command) {
        String[] order = command.split("\\s");
        if (order.length == 5) {
            long id = Long.parseLong(order[1]);
            for (Product product : admin.getProducts()) {
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
            for (Product product : admin.getProducts()) {
                if (product.getId() == id) {
                    admin.getProducts().remove(product);
                    return "true";
                }
            }
        }
        return "false";
    }

    public String showProductInfo(String command) {
        String[] order = command.split("\\s");
        if (order.length == 2) {
            for (Product product : admin.getProducts()) {
                if (product.getId() == (Long.parseLong(order[1]))) {
                    return product.toString();
                }
            }
        }
        return "Error!";
    }

    String manageRequest(String command) {
        String[] order = command.split("\\s");
        if (order.length == 3) {
            for (int i = 0; i < Integer.parseInt(order[2]); i++) {
                admin.getRequests().get(i).setAcception(Boolean.parseBoolean(order[1]));
                if (admin.getRequests().get(i).getAcception()) {
                    admin.getRequests().get(i).setCommentSituation(CommentSituation.ACCEPTED);
                    admin.getAcceptedRequest().add(admin.getRequests().get(i));
                    admin.getRequests().remove(i);
                } else {
                    admin.getRequests().get(i).setCommentSituation(CommentSituation.REJECTED);
                }
            }
            return "true";
        }
        return "false";
    }
}
