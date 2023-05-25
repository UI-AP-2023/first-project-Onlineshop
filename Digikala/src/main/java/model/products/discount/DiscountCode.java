package model.products.discount;

public interface DiscountCode {
    void addDiscount(int percent);
    void removeDiscount(int percent);
}
