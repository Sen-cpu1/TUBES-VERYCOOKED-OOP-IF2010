package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InputHandler utility class.
 */
class InputHandlerTest {
    
    @Test
    void testIsValidMenuChoice_ValidInput() {
        assertTrue(InputHandler.isValidMenuChoice("1", 3));
        assertTrue(InputHandler.isValidMenuChoice("2", 3));
        assertTrue(InputHandler.isValidMenuChoice("3", 3));
    }
    
    @Test
    void testIsValidMenuChoice_InvalidInput() {
        assertFalse(InputHandler.isValidMenuChoice("0", 3));
        assertFalse(InputHandler.isValidMenuChoice("4", 3));
        assertFalse(InputHandler.isValidMenuChoice("abc", 3));
        assertFalse(InputHandler.isValidMenuChoice("", 3));
    }
    
    @Test
    void testParseInt_ValidInput() {
        assertEquals(5, InputHandler.parseInt("5", 0));
        assertEquals(100, InputHandler.parseInt("100", 0));
    }
    
    @Test
    void testParseInt_InvalidInput() {
        assertEquals(0, InputHandler.parseInt("abc", 0));
        assertEquals(10, InputHandler.parseInt("invalid", 10));
    }
}
