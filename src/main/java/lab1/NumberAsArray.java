package lab1;

import java.io.Serializable;

import java.io.IOException;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NumberAsArray implements Serializable{
    // private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(NumberAsArray.class);
    private int[] numberArray;

    public int[] getArray(){
        return this.numberArray;
    }

    public void setArray(int[] numbers) throws InvalidInputException{
        for (int number : numbers) {
            if (number <= 0){
                throw new InvalidInputException("Invalid number");
            }
            
            if (!this.isSimple(number)){
                throw new InvalidInputException("Invalid number");
            }
        }    
        this.numberArray = numbers;   
    }

    // Empty constuctor
    public NumberAsArray(){
    }

    public NumberAsArray(int[] numbers) throws InvalidInputException{
        for (int number : numbers) {
            if (number <= 0){
                throw new InvalidInputException("Invalid number");
            }
            
            if (!this.isSimple(number)){
                throw new InvalidInputException("Invalid number");
            }
        }        
        this.numberArray = numbers;
    }

    public int asNumber() throws NoValueException{
        if (this.numberArray == null){
            throw new NoValueException("Object has not been initialized"); 
        }

        int sum = 1;
        for(int n: this.numberArray){
            sum *= n;
        }
        return sum;
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

    @Override
    public String toString(){
        try{
            return Integer.toString(this.asNumber());
        } catch (NoValueException e){
            logger.debug("AsNumber error" + e.getMessage());
            return "";
        }
    }

    public void writeToFile(String filePath) throws WriteException{
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            throw new WriteException("Write to file error:"+e.getMessage());
        }
    }

    public void readFromFile(String filePath) throws ReadException{
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            NumberAsArray number = (NumberAsArray) objectInputStream.readObject();
            this.numberArray = number.getArray();
            
        } catch (IOException | ClassNotFoundException e) {
            throw new ReadException("Read from file error:"+e.getMessage());
        }
    }

    private void writeObject(ObjectOutputStream outStream)throws IOException {
        outStream.defaultWriteObject();
        outStream.writeInt(numberArray.length);

        logger.debug("Write:");
        logger.debug("\t"+numberArray.length);

        for (int i : numberArray) {
            logger.debug("\t"+i);
            outStream.writeInt(i);
        }
    }
    
    private void readObject(ObjectInputStream inStream) throws IOException, ClassNotFoundException {
        inStream.defaultReadObject();
        int length = inStream.readInt();
        numberArray = new int[length];
        
        logger.debug("Read:");
        logger.debug("\t"+length);

        for (int i = 0; i < length; i++) {
            numberArray[i] = inStream.readInt();
            logger.debug("\t"+numberArray[i]);
        }
    }

    // private void writeObject(ObjectOutputStream outStream)throws IOException {
    //     outStream.defaultWriteObject();
    //     outStream.writeInt(555);
    // }

    // private void readObject(ObjectInputStream inStream) throws IOException, ClassNotFoundException {
    //     inStream.defaultReadObject();
    //     numberArray = new int[1];
    //     numberArray[0] = inStream.readInt();
    // }
}
