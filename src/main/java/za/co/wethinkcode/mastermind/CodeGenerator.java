package za.co.wethinkcode.mastermind;

import java.util.Random;
import java.util.stream.IntStream;

public class CodeGenerator {
    private final Random random;

    public CodeGenerator(){
        this.random = new Random();
    }

    public CodeGenerator(Random random){
        this.random = random;
    }

    /**
     * Generates a random 4 digit code, using this.random, where each digit is in the range 1 to 8 only.
     * Duplicated digits are allowed.
     * @return the generated 4-digit code
     */
    public String generateCode(){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//      Instead of concatenating string to String (Use StringBuilder) credits (IntelliJ)
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //TODO: implement using this.random
        StringBuilder numbers = new StringBuilder();

        for ( int i=0; i<4; i++) {
            int number = this.random.nextInt(9);
            numbers.append(number);
        }
//        System.out.println(numbers.toString());
            return numbers.toString();

    }
}
