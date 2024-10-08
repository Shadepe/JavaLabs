public class NonPerishableProduct extends Product {

    public NonPerishableProduct(String name, double price, int quantity) throws InvalidProductException {
        super(name, price, quantity);
    }

    public String getProductType() {
        return "Non-Perishable";
    }
}
