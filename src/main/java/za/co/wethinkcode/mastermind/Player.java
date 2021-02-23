package za.co.wethinkcode.mastermind;

import java.io.InputStream;
import java.util.Scanner;

public class Player {
    final Scanner inputScanner;
    public int turns = 11;

    public Player(){
        this.inputScanner = new Scanner(System.in);
    }

    public Player(InputStream inputStream){
        this.inputScanner = new Scanner(inputStream);
    }

    /**
     * Gets a guess from user via text console.
     * This must prompt the user to re-enter a guess until a valid 4-digit string is entered, or until the user enters `exit` or `quit`.
     *
     * @return the value entered by the user
     */
    public String getGuess(){

        System.out.println("Input 4 digit code:");

        String user_in = this.inputScanner.nextLine();

        if (user_in.length() != 4){
            System.out.println("Please enter exactly 4 digits (each from 1 to 8).");
            return getGuess();
        }

        if ( user_in.equalsIgnoreCase("exit") || user_in.equalsIgnoreCase("quit") ){
            return user_in;
        }

        for ( int i=0; i< 4; i++){
//            System.out.println(user_in.codePointAt(i));
            if ( Character.isLetter(user_in.charAt(i)) ) {
                System.out.println("Please enter exactly 4 digits (each from 1 to 8).");
                return getGuess();
            }
            else if (user_in.charAt(i) == '0' || user_in.charAt(i) == '9'){
                System.out.println("Please enter exactly 4 digits (each from 1 to 8).");
                return getGuess();
            }
//            else if (user_in.length() == 0) {
//                System.out.println("Please enter exactly 4 digits (each from 1 to 8).");
//                return getGuess();
            }



        return user_in;
    }
}
