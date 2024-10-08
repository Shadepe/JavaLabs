public class PerishableProduct extends Product implements Discountable {
    private String expiryDate;

    public PerishableProduct(String name, double price, int quantity, String expiryDate) throws InvalidProductException {
        super(name, price, quantity);  // Call the constructor of Product
        this.expiryDate = expiryDate;
    }

    @Override
    public double applyDiscount(double discountRate) {
        return this.price * (1 - discountRate);
    }

    public String getProductType() {
        return "Perishable";
    }

    @Override
    public String toString() {
        return super.toString() + "\nExpiry Date: " + this.expiryDate;
    }
}

