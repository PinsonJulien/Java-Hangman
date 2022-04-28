package com.jpinson.pendujfx.utils;

import junit.framework.TestCase;

public class AlphanumericTest extends TestCase {

    public void testValidate() {
        assertTrue(Alphanumeric.validate('1'));
        assertTrue(Alphanumeric.validate('A'));
        assertTrue(Alphanumeric.validate('a'));
        assertFalse(Alphanumeric.validate('+'));
        
        assertTrue(Alphanumeric.validate("1a0B"));
        assertFalse(Alphanumeric.validate("1a0+"));
    }
}
