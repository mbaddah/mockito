package mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.isA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MockitoExampleTest {

    @Mock
    Iterator<String> i;

    //instead of annotating @Mock here another option is to pass in as Method parameter
    Comparable<String> c;

    @Spy
    List<String> spy = new LinkedList<>();

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

    //doReturn() example. Use doReturn() when spying
    @Test
    void testSpyingOnArrayList(){
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> spiedList = spy(list);
        doReturn("42").when(spiedList).get(99);
        String mockValue = (String) spiedList.get(99);
        assertEquals("42",mockValue);
    }

    //TODO - Add doThrow() example

    //Spy declared at beginning of class
    @Test
    void testSpyingOnLinkedList() {
        //have to use doReturn()
        doReturn("iExpectThisStringValueWhenIGetSpy@Position0").when(spy).get(0);
        assertEquals("iExpectThisStringValueWhenIGetSpy@Position0", spy.get(0));
    }


    @Test
    void testVerifyCalls(@Mock Database database) {
        when(database.getUniqueId()).thenReturn(43);
        database.setUniqueId(12);
        database.getUniqueId();
        database.getUniqueId();

        verify(database, times(2)).getUniqueId(); //Verify getUniqueId() called atleast 2x
        verify(database, never()).isAvailable(); //Verify isAvailable() was never called
        verify(database, atLeastOnce()).setUniqueId(12);
        verify(database, atLeastOnce()).setUniqueId(anyInt());


        verifyNoMoreInteractions(database); //Final verification to check verified all calling methods

    }

}
