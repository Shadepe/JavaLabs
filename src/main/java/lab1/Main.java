package lab1;

public class Main {
    public static void main(String[] args) {
        try{
            NumberAsArray NA1 = new NumberAsArray(5345);
            System.out.println("NA1: "+NA1);

            int ns[] = {1,2,3,5,7};
            NumberAsArray NA2 = new NumberAsArray(ns);
            System.out.println("NA2: "+NA2);

            try{
                NA2.writeToFile("test.txt");
            } catch(WriteException e){
                System.out.println("Read error: " + e.getMessage());
            }
            
            NumberAsArray NA3 = new NumberAsArray();
            try{
                NA3.readFromFile("test.txt");
            } catch(ReadException e){
                System.out.println("Read error: " + e.getMessage());
            }

            try{
                System.out.println("NA3: "+NA3.asNumber());
            } catch (NoValueException e){
                System.out.println(e.getMessage());
            }
        
        } catch(InvalidInputException e){
            System.out.println("Invalid number: "+e.getMessage());
        }
    }
}
