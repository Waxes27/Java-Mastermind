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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class MastermindTest {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    Initializing standard In/Out to variable for later I'm guessing                                                  /
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    The Use of Standard Out/In old values came in handy I see (Research @BeforeEach)                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @BeforeEach
    public void setup() {System.setOut(new PrintStream(outputCapture)); }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }
    @Test
    public void TestCorrect() {
        Random mockedNumber = Mockito.mock(Random.class);
        when(mockedNumber.nextInt(anyInt())).thenReturn(1, 2, 3, 4);

//        System.out.println(mockedNumber);
        InputStream mockedInput = new ByteArrayInputStream("1234\n".getBytes());
        Mastermind gameTest = new Mastermind(new CodeGenerator(mockedNumber), new Player(mockedInput));
        gameTest.runGame();


        assertEquals("""
                4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.
                Input 4 digit code:
                Congratulations! You are a codebreaker!
                The code was: 1234""", outputCapture.toString().strip());
    }

    @Test
    public void TestIncorrect(){
        Random mockedNumber = Mockito.mock(Random.class);
        when(mockedNumber.nextInt(anyInt())).thenReturn(1,2,3,4);

        InputStream mockedInput = new ByteArrayInputStream("1235\n1235\n1235\n1235\n1235\n1235\n1235\n1235\n1235\n1235\n1235\n1235\n".getBytes());
        Mastermind game_test = new Mastermind(new CodeGenerator(mockedNumber), new Player(mockedInput));
        game_test.runGame();

        assertEquals("""
                4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.
                Input 4 digit code:
                Number of correct digits in correct place: 3
                Number of correct digits not in correct place: 1
                Turns left: 11
                Input 4 digit code:
                Number of correct digits in correct place: 6
                Number of correct digits not in correct place: 1
                Turns left: 10
                Input 4 digit code:
                Number of correct digits in correct place: 9
                Number of correct digits not in correct place: 1
                Turns left: 9
                Input 4 digit code:
                Number of correct digits in correct place: 12
                Number of correct digits not in correct place: 1
                Turns left: 8
                Input 4 digit code:
                Number of correct digits in correct place: 15
                Number of correct digits not in correct place: 1
                Turns left: 7
                Input 4 digit code:
                Number of correct digits in correct place: 18
                Number of correct digits not in correct place: 1
                Turns left: 6
                Input 4 digit code:
                Number of correct digits in correct place: 21
                Number of correct digits not in correct place: 1
                Turns left: 5
                Input 4 digit code:
                Number of correct digits in correct place: 24
                Number of correct digits not in correct place: 1
                Turns left: 4
                Input 4 digit code:
                Number of correct digits in correct place: 27
                Number of correct digits not in correct place: 1
                Turns left: 3
                Input 4 digit code:
                Number of correct digits in correct place: 30
                Number of correct digits not in correct place: 1
                Turns left: 2
                Input 4 digit code:
                Number of correct digits in correct place: 33
                Number of correct digits not in correct place: 1
                Turns left: 1
                No more turns left.
                The code was: 1234""",outputCapture.toString().strip());
        }

    @Test
    public void testLetters(){
        Random mockerNumber = Mockito.mock(Random.class);
        when(mockerNumber.nextInt(anyInt())).thenReturn(1,2,3,4);

        InputStream mockedInput = new ByteArrayInputStream("12qw\n123e\n123R\nQw23\n1234\n".getBytes());
        Mastermind game_test = new Mastermind(new CodeGenerator(mockerNumber),new Player(mockedInput));
        game_test.runGame();

        assertEquals("""
                4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.
                Input 4 digit code:
                Input 4 digit code:
                Input 4 digit code:
                Input 4 digit code:
                Input 4 digit code:
                Congratulations! You are a codebreaker!
                The code was: 1234""",outputCapture.toString().strip());

    }
}


