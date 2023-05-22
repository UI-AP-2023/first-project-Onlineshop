package model.products;

public interface DiscountCode {
    void addDiscount(int percent);
    void removeDiscount(int percent);
}
