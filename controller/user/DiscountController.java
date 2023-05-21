package controller.user;

import model.products.Discount;

public class DiscountController {
    public String codeMaker(Discount discount){
        discount.setCode(java.time.LocalDate.now().toString() +"-"+ discount.getCounter());
        return discount.getCode();
    }
}