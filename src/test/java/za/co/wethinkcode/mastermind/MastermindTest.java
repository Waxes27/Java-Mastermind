package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import za.co.wethinkcode.mastermind.CodeGenerator;
import za.co.wethinkcode.mastermind.Mastermind;
import za.co.wethinkcode.mastermind.Player;

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

        assertEquals("""
                4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 3
                Turns left: 11
                Input 4 digit code:
                Number of correct digits in correct place: 4
                Number of correct digits not in correct place: 0
                Congratulations! You are a codebreaker!
                The code was: 2345""",capturedOutput.toString().strip());

    }
    @Test
    public void testOutputIncorrect(){
        when(mockedNumber.nextInt(anyInt())).thenReturn(1,2,3,4);

        InputStream mockedInput = new ByteArrayInputStream("""
                3456
                4567
                5678
                6788
                1122
                2233
                3344
                4455
                5566
                6677
                8888
                1234
                """.getBytes(StandardCharsets.UTF_8));

        Mastermind game = new Mastermind(new CodeGenerator(mockedNumber),new Player(mockedInput));
        game.runGame();

        assertEquals("""
                4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 3
                Turns left: 11
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 2
                Turns left: 10
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 1
                Turns left: 9
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 0
                Turns left: 8
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 2
                Turns left: 7
                Input 4 digit code:
                Number of correct digits in correct place: 1
                Number of correct digits not in correct place: 3
                Turns left: 6
                Input 4 digit code:
                Number of correct digits in correct place: 2
                Number of correct digits not in correct place: 2
                Turns left: 5
                Input 4 digit code:
                Number of correct digits in correct place: 1
                Number of correct digits not in correct place: 3
                Turns left: 4
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 2
                Turns left: 3
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 0
                Turns left: 2
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 0
                Turns left: 1
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 3
                No more turns left.
                The code was: 2345""",capturedOutput.toString().trim());
    }
    @Test
    public void testLettersThenCorrect() {
        when(mockedNumber.nextInt(anyInt())).thenReturn(1,2,3,4);
        InputStream mockedInputStream = new ByteArrayInputStream("""
                qw12\n23er\n34Ed\nqwer\n2345\n
                """.getBytes(StandardCharsets.UTF_8));

        Mastermind game = new Mastermind(new CodeGenerator(mockedNumber),
                new Player(mockedInputStream));
        game.runGame();

        assertEquals("""
                4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.
                Input 4 digit code:
                Please enter exactly 4 digits (each from 1 to 8).
                Input 4 digit code:
                Please enter exactly 4 digits (each from 1 to 8).
                Input 4 digit code:
                Please enter exactly 4 digits (each from 1 to 8).
                Input 4 digit code:
                Please enter exactly 4 digits (each from 1 to 8).
                Input 4 digit code:
                Number of correct digits in correct place: 4
                Number of correct digits not in correct place: 0
                Congratulations! You are a codebreaker!
                The code was: 2345""",capturedOutput.toString().strip());
    }
    @Test
    public void testLettersThenIncorrect(){
        when(mockedNumber.nextInt(anyInt())).thenReturn(1,2,3,4);
        InputStream mockedInputStream = new ByteArrayInputStream("""
                qw23\nQw34
                1234
                3456
                4567
                5687
                2435
                1324
                4657
                8567
                4455
                1122
                2233
                3344
                
                """.getBytes(StandardCharsets.UTF_8));
        Mastermind game = new Mastermind(new CodeGenerator(mockedNumber),
                new Player(mockedInputStream));
        game.runGame();

        assertEquals("""
                4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.
                Input 4 digit code:
                Please enter exactly 4 digits (each from 1 to 8).
                Input 4 digit code:
                Please enter exactly 4 digits (each from 1 to 8).
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 3
                Turns left: 11
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 3
                Turns left: 10
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 2
                Turns left: 9
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 1
                Turns left: 8
                Input 4 digit code:
                Number of correct digits in correct place: 2
                Number of correct digits not in correct place: 2
                Turns left: 7
                Input 4 digit code:
                Number of correct digits in correct place: 1
                Number of correct digits not in correct place: 2
                Turns left: 6
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 2
                Turns left: 5
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 1
                Turns left: 4
                Input 4 digit code:
                Number of correct digits in correct place: 1
                Number of correct digits not in correct place: 3
                Turns left: 3
                Input 4 digit code:
                Number of correct digits in correct place: 0
                Number of correct digits not in correct place: 2
                Turns left: 2
                Input 4 digit code:
                Number of correct digits in correct place: 1
                Number of correct digits not in correct place: 3
                Turns left: 1
                Input 4 digit code:
                Number of correct digits in correct place: 2
                Number of correct digits not in correct place: 2
                No more turns left.
                The code was: 2345""",capturedOutput.toString().strip());

    }
}

