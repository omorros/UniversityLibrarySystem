package main.model;

/**
 * Abstract base class representing a library product.
 * Demonstrates abstraction, encapsulation, and interface implementation.
 */
public abstract class Product implements Borrowable {
    protected int productId;
    protected String title;
    protected boolean available;

    public Product(int productId, String title) {
        this.productId = productId;
        this.title = title;
        this.available = true;
    }

    // -----------------------------------
    // Product-specific methods
    // -----------------------------------
    public int getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    // -----------------------------------
    // Borrowable interface implementation
    // -----------------------------------
    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Returns product info including availability status.
     */
    @Override
    public String getInfo() {
        String status = available ? "Available" : "Checked Out";
        return "ID: " + productId + " | Title: " + title + " | Status: " + status;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
