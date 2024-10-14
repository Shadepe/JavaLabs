package example;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) 
    {

        ArrayList<Product> productList = new ArrayList<>();

        try 
        {
            productList.add(new NonPerishableProduct("Laptop", 1000, 5)                          );
            productList.add(new NonPerishableProduct("Phone",  500,  10)                         );
            productList.add(new PerishableProduct(   "Milk",   1.2,  30, "2024-10-15"));
            productList.add(new PerishableProduct(   "Cheese", 2.5,  10, "2024-11-01"));

        } catch (InvalidProductException e) 
        {
            System.out.println(e.getMessage());
        }

        for (Product product : productList) 
        {
            System.out.println(product);
        }

        readProductsFromFile("test.txt");
        // saveProductsToFile(productList, "test.txt");
        
        // Product[] products = new Product[5];

        // try {
        //     products[0] = new NonPerishableProduct("Apple", 0.5, 100);
        //     products[1] = new NonPerishableProduct("Orange", 0.8, 80);
        //     products[2] = new NonPerishableProduct("Banana", 0.3, 50);
        //     products[3] = 
        //     products[4] = 

        // } catch (InvalidProductException e) {
        //     System.out.println(e.getMessage());
        // }

        // this.filterCheapProducts(products, 0.5);
        
    }

    private static void writeProductToWriter(Product product, BufferedWriter writer) throws IOException
    {
        writer.write(product.toString());
        writer.newLine();
        writer.newLine();

    }

    public static void saveProductsToFile(ArrayList<Product> productList, String fileName)
    {
        
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) 
            {
                for (Product product : productList) 
                {
                    writeProductToWriter(product, writer);
                }
            } catch (IOException e) 
            {
                System.out.println("An error occurred while writing to the file.");
            }
    }
    public static void readProductsFromFile(String fileName)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) 
        {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e)
        {
            System.out.println("An error occurred while reading from the file.");
        }
    }

    public static void filterCheapProducts(Product[] products, double threshold) 
    {
        for (Product product : products) 
        {
            if (product.getPrice() < threshold) 
            {
                System.out.println(product.getName() + " is cheap. Price: " + product.getPrice());
            }
        }
    }

    public static Product findProductByName(Product[] products, String name) 
    {
        for (Product product : products) 
        {
            if (product.getName().equalsIgnoreCase(name)) 
            {
                return product;
            }
        }
        return null;
    }
    
    
}
