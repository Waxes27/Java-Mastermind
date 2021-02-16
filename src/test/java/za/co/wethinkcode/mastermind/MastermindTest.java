package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
    public void TestMain(){
        InputStream mockedInput = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        System.out.println(mockedInput.toString());

    }


}