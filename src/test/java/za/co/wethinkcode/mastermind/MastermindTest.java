package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class MastermindTest {
    private final PrintStream originalOutputStream = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();

    @BeforeEach
    public void setupOutput() {
        System.setOut(new PrintStream(capturedOutput));
    }
    @AfterEach
    public void resetOutput(){
        System.setOut(new PrintStream(originalOutputStream));
    }
    Random mockedNumber = Mockito.mock(Random.class);

    @Test
    public void testOutputCorrect(){

        when(mockedNumber.nextInt(anyInt())).thenReturn(1,2,3,4);
        InputStream mockedInputStream = new ByteArrayInputStream("1234\n2345\n".getBytes(StandardCharsets.UTF_8));
        Mastermind game = new Mastermind(new CodeGenerator(mockedNumber),new Player(mockedInputStream));
        game.runGame();

        assertEquals("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 3\n"+
                "Turns left: 11\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 4\n"+
                "Number of correct digits not in correct place: 0\n"+
                "Congratulations! You are a codebreaker!\n"+
                "The code was: 2345",capturedOutput.toString().strip());

    }
    @Test
    public void testOutputIncorrect(){
        when(mockedNumber.nextInt(anyInt())).thenReturn(1,2,3,4);

        InputStream mockedInput = new ByteArrayInputStream("3456\n4567\n5678\n6788\n1122\n2233\n3344\n4455\n5566\n6677\n3344\n1234\n".getBytes(StandardCharsets.UTF_8));

        Mastermind game = new Mastermind(new CodeGenerator(mockedNumber),new Player(mockedInput));
        game.runGame();

        assertEquals("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it."+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 3\n"+
                "Turns left: 11\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 2\n"+
                "Turns left: 10\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 1\n"+
                "Turns left: 9\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 0\n"+
                "Turns left: 8\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 2\n"+
                "Turns left: 7\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 1\n"+
                "Number of correct digits not in correct place: 3\n"+
                "Turns left: 6\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 2\n"+
                "Number of correct digits not in correct place: 2\n"+
                "Turns left: 5\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 1\n"+
                "Number of correct digits not in correct place: 3\n"+
                "Turns left: 4\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 2\n"+
                "Turns left: 3\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 0\n"+
                "Turns left: 2\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 0\n"+
                "Turns left: 1\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 3\n"+
                "No more turns left.\n"+
                "The code was: 2345",capturedOutput.toString().trim());
    }
    @Test
    public void testLettersThenCorrect() {
        when(mockedNumber.nextInt(anyInt())).thenReturn(1,2,3,4);
        InputStream mockedInputStream = new ByteArrayInputStream("qw12\n23er\n34Ed\nqwer\n2345\n".getBytes(StandardCharsets.UTF_8));

        Mastermind game = new Mastermind(new CodeGenerator(mockedNumber),
                new Player(mockedInputStream));
        game.runGame();

        assertEquals("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.\n"+
                "Input 4 digit code:\n"+
                "Please enter exactly 4 digits (each from 1 to 8).\n"+
                "Input 4 digit code:\n"+
                "Please enter exactly 4 digits (each from 1 to 8).\n"+
                "Input 4 digit code:\n"+
                "Please enter exactly 4 digits (each from 1 to 8).\n"+
                "Input 4 digit code:\n"+
                "Please enter exactly 4 digits (each from 1 to 8).\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 4\n"+
                "Number of correct digits not in correct place: 0\n"+
                "Congratulations! You are a codebreaker!\n"+
                "The code was: 2345",capturedOutput.toString().strip());
    }
    @Test
    public void testLettersThenIncorrect(){
        when(mockedNumber.nextInt(anyInt())).thenReturn(1,2,3,4);
        InputStream mockedInputStream = new ByteArrayInputStream("qw23\nQw34\n1234\n3456\n4567\n5687\n2435\n1324\n4657\n8567\n4455\n1122\n2233\n3344\n".getBytes(StandardCharsets.UTF_8));
        Mastermind game = new Mastermind(new CodeGenerator(mockedNumber),
                new Player(mockedInputStream));
        game.runGame();

        assertEquals("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.\n"+
                "Input 4 digit code:\n"+
                "Please enter exactly 4 digits (each from 1 to 8).\n"+
                "Input 4 digit code:\n"+
                "Please enter exactly 4 digits (each from 1 to 8).\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 3\n"+
                "Turns left: 11\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 3\n"+
                "Turns left: 10\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 2\n"+
                "Turns left: 9\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 1\n"+
                "Turns left: 8\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 2\n"+
                "Number of correct digits not in correct place: 2\n"+
                "Turns left: 7\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 1\n"+
                "Number of correct digits not in correct place: 2\n"+
                "Turns left: 6\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 2\n"+
                "Turns left: 5\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 1\n"+
                "Turns left: 4\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 1\n"+
                "Number of correct digits not in correct place: 3\n"+
                "Turns left: 3\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 0\n"+
                "Number of correct digits not in correct place: 2\n"+
                "Turns left: 2\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 1\n"+
                "Number of correct digits not in correct place: 3\n"+
                "Turns left: 1\n"+
                "Input 4 digit code:\n"+
                "Number of correct digits in correct place: 2\n"+
                "Number of correct digits not in correct place: 2\n"+
                "No more turns left.\n"+
                "The code was: 2345",capturedOutput.toString().strip());

    }
}

