package JavaLabs.Lab1;

public class Number {
    public int number = 0;
    
    public Number(int number) throws InvalidInputException
    {
        if (number <= 0){
            throw new InvalidInputException("Invalid number");
        }
        
        if (!this.isSimple(number)){
            throw new InvalidInputException("Invalid number");
        }

        this.number = number;
        System.out.println(this.number);
    }

    public Number(int[] numbers) throws InvalidInputException
    {
        this.number = 1;
        for (int number : numbers) {
            if (number <= 0){
                throw new InvalidInputException("Invalid number");
            }
            
            if (!this.isSimple(number)){
                throw new InvalidInputException("Invalid number");
            }
            this.number *= number;
        }        
        System.out.println(this.number);
    }

    public boolean isSimple(int number)
    {
        if (number <= 3){
            return true;
        }

        for (int i = 2; i <= number/2; i++) {
            if (number%i == 0){
                return false;
            }            
        }
        return true;
    }
}
