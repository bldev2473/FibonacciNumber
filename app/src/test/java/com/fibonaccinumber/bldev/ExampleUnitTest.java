package com.fibonaccinumber.bldev;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private TestModel tm;
    long testValue;
    private DBHelper mDB;

    @Before
    public void setup() {
        tm = new TestModel(mDB);

    }

    @Test
    public void fib_isCorrect() throws Exception {
        testValue = tm.fib(0);
        assertEquals(0, testValue);

        testValue = tm.fib(1);
        assertEquals(1, testValue);

        testValue = tm.fib(2);
        assertEquals(1, testValue);

        testValue = tm.fib(3);
        assertEquals(2, testValue);

        testValue = tm.fib(4);
        assertEquals(3, testValue);

        testValue = tm.fib(5);
        assertEquals(5, testValue);

        testValue = tm.fib(35);
        assertEquals(9227465, testValue);
    }
}
