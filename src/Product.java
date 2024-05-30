public class Product {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int reorderThreshold;

    public Product(int productId, String name, String description, double price, int reorderThreshold) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.reorderThreshold = reorderThreshold;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }
}
