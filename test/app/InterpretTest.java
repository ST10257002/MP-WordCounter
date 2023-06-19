
package app;

import app.Interpret;
import org.junit.Test;
import static org.junit.Assert.*;

public class InterpretTest {
    
    public InterpretTest() {
    }

    @Test
    public void testUpdateTable() {
    }

    @Test
    public void testUpdateLogFile() {
    }

    @Test
    public void testGetTotal() {
    }

    @Test
    public void testGetWords() {
    }

    @Test
    public void testGetWordsUnique() {
    }

    @Test
    public void testGetCharacters() {
    }

    @Test
    public void testGetSpace() {
    }

    @Test
    public void testGetSymbols() {
    }

    @Test
    public void testGetLetters() {
    }

    @Test
    public void testGetLettersUpperCase() {
    }

    @Test
    public void testGetLettersLowerCase() {
    }

    @Test
    public void testGetDigits() {
        
        final String testA = "";
        final String testB = "1234567890";
        final String testC = "1x3*4?():A";
        
        assertEquals(0, Interpret.getDigits(testA));
        assertEquals(10, Interpret.getDigits(testB));
        assertEquals(3, Interpret.getDigits(testC));
        
    }
    
}
