package mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.isA;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

@ExtendWith(MockitoExtension.class)
class MockitoExampleTest {

    @Mock
    Iterator<String> i;

    //instead of annotating @Mock here another option is to pass in as Method parameter
    Comparable<String> c;

    @Test
    void testMultipleReturnValues() {
        when(i.next()).thenReturn("foo").thenReturn("bar");
        String result = i.next() + " " + i.next();
        assertEquals("foo bar", result);
    }

    //@Mock annotation passed in as parameter
    @Test
    void testConditionalReturnValues(@Mock Comparable<String> c) {
        when(c.compareTo("foo")).thenReturn(1);
        when(c.compareTo("bar")).thenReturn(2);

        assertEquals(1, c.compareTo("foo"));
        assertEquals(2, c.compareTo("bar"));
    }

    @Test
    void testReturnZeroWhenIntegerPassed(@Mock Comparable<Integer> c) {
        //Want to return 0 anytime an integer is passed in as an argument to comparator
        when(c.compareTo(isA(Integer.class))).thenReturn(0);

        assertEquals(0, c.compareTo(4));
        assertEquals(0, c.compareTo(1));
        assertEquals(0, c.compareTo(99));

    }

}
