public abstract class Product {
    public String name;
    public double price;
    public int quantity;

    public Product(String name, double price, int quantity) throws InvalidProductException{
        if (price < 0 || quantity < 0) {
            throw new InvalidProductException("Price and quantity must be non-negative");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return "Type: " +this.getProductType()+ "\nName: " + this.name+ "\nPrice($): " + this.price + "\nQuantity: " + this.quantity;
    }

    public abstract String getProductType();

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}