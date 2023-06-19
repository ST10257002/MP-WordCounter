
package app;

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
    
    // ---

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
        
        final String testA = "";
        final String testB = "ABCDEFGHIJ";
        final String testC = "1x3*4?():A";
        final String testD = "ÆæⱭɑᴀ";
        final String testE = "ÅåȂȃÂâα";

        assertEquals(0, Interpret.getLettersUpperCase(testA));
        assertEquals(10, Interpret.getLettersUpperCase(testB));
        assertEquals(1, Interpret.getLettersUpperCase(testC));
        assertEquals(0, Interpret.getLettersUpperCase(testD));
        assertEquals(0, Interpret.getLettersUpperCase(testE));
        
    }

    @Test
    public void testGetLettersLowerCase() {
        
        final String testA = "";
        final String testB = "abcdefghij";
        final String testC = "1x3*4?():A";
        final String testD = "ÆæⱭɑᴀ";
        final String testE = "ÅåȂȃÂâα";
        
        assertEquals(0, Interpret.getLettersLowerCase(testA));
        assertEquals(10, Interpret.getLettersLowerCase(testB));
        assertEquals(1, Interpret.getLettersLowerCase(testC));
        assertEquals(0, Interpret.getLettersLowerCase(testD));
        assertEquals(0, Interpret.getLettersLowerCase(testE));
        
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
