package main.model;

/**
 * Abstract base class representing a library product.
 * Demonstrates abstraction and encapsulation.
 */
public abstract class Product {
    protected int productId;
    protected String title;
    protected boolean available;

    public Product(int productId, String title) {
        this.productId = productId;
        this.title = title;
        this.available = true;
    }

    public int getProductId() { return productId; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getInfo() {
        return "ID: " + productId + " | Title: " + title + " | Available: " + available;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
