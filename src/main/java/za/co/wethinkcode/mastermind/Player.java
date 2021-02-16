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
        String user_in = this.inputScanner.next();

        if (user_in.length() != 4){return getGuess();}

        if ( user_in.equalsIgnoreCase("exit") || user_in.equalsIgnoreCase("quit") ){
            return user_in;
        }

        for ( int i=0; i< 4; i++){
            if ( Character.isLetter(user_in.charAt(i)) ){
                return getGuess();
            }
        }

        return user_in;
    }
}
