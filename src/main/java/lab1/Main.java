package lab1;

public class Main {
    public static void main(String[] args) 
    {
        // try
        // {
        //     Number n = new Number(7);

        // } catch (InvalidInputException e) {
        //     System.out.println(e.getMessage());
        // }

        try
        {  
            int ns[] = {2,5};
            NumberAsArray NA1 = new NumberAsArray(ns);
            try{
                NA1.writeToFile("test.txt");
            } catch(WriteException e){
                System.out.println("Read error: " + e.getMessage());
            }

            NumberAsArray NA2 = new NumberAsArray();
            try{
                NA2.readFromFile("test.txt");
            } catch(ReadException e){
                System.out.println("Read error: " + e.getMessage());
            }

            // System.out.println(NA2);
            try{
                System.out.println(NA2.asNumber());
            } catch (NoValueException e){
                System.out.println(e.getMessage());
            }

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }

    }
}