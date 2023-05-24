package model.products.stationery;

public class Notebook extends StationeryProduct {
    private int numberOfPages;
    private PageType pageType;

    public Notebook() {
    }

    public void setter(String name, double price,  int numberOfAvailable,String country, int numberOfPages, PageType pageType) {
        super.setter(name, price, numberOfAvailable,country);
        this.numberOfPages = numberOfPages;
        this.pageType = pageType;
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public PageType getPageType() {
        return this.pageType;
    }

    public String toString() {
        return super.toString()+ "\nNumber of pages= " + this.getNumberOfPages() + "\nType of pages= " + this.getPageType();
    }
}
