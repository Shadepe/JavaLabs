package JavaLabs.Lab1;

public class Main {
    public static void main(String[] args) 
    {
        try
        {
            Number n = new Number(7);

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }

        try
        {  
            int ns[] = {2,5};
            Number n2 = new Number(ns);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }

    }
}
