package ca.bcit.comp2526.a3;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
    
    private LinkedList<String> stringList;

    @Before
    public void setUp() throws Exception {
        stringList = new LinkedList<>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLinkedList() {
        assertNull(stringList.getHead());
        assertNull(stringList.getTail());
    }

    @Test
    public void testLinkedListE() {
        LinkedList<String> testList = new LinkedList<>("Yashar");
        assertSame(testList.getHead().getData(), "Yashar");
        assertSame(testList.getTail().getData(), "Yashar");
    }

    @Test
    public void testSizeEmptyList() {
        assertSame(stringList.size(), 0);
    }
    
    @Test
    public void testSizeOneItemList() {
        stringList.add("1");
        assertSame(stringList.size(), 1);
    }
    
    @Test
    public void testSizeLargeItemList() {
        Random rng = new Random();
        
        int num = rng.nextInt(5) + 10;
        
        for (int i = 0; i < num; i++) {
            stringList.add("" + i);
        }
        assertSame(stringList.size(), num);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stringList.isEmpty());
        stringList.add("1");
        assertFalse(stringList.isEmpty());
    }

    @Test
    public void testContainsEmptyList() {
        assertFalse(stringList.contains("1"));
    }
    
    @Test
    public void testContainsListSizeOne() {
        assertFalse(stringList.contains("1"));
    }

    @Test
    public void testIteratorHasNextEmptyList() {
        ListIterator<String> iterator = stringList.iterator();
        assertFalse(iterator.hasNext());
    }
    
    @Test
    public void testIteratorHasNextListSizeOne() {
        stringList.add("Paul");
        ListIterator<String> iterator = stringList.iterator();
        assertTrue(iterator.hasNext());
    }
    
    @Test
    public void testIteratorNextListSizeOne() {
        stringList.add("Paul");
        ListIterator<String> iterator = stringList.iterator();
        String value = iterator.next();
        assertSame(value, "Paul");
    }
    
    @Test
    public void testIteratorHasNextLargeList() {
        Random rng = new Random();
        
        int num = rng.nextInt(5) + 10;
        
        for (int i = 0; i < num; i++) {
            stringList.add("" + i);
        }
        
        ListIterator<String> iterator = stringList.iterator();
        
        for (int i = 0; i < num; i++) {
            assertTrue(iterator.hasNext());
            assertNotNull(iterator.next()); 
        }
        
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }
    
    @Test
    public void testIteratorPreviousListSizeOne() {
        stringList.add("Paul");
        ListIterator<String> iterator = stringList.iterator();
        String value = iterator.previous();
        assertNull(value);
    }
    
    @Test
    public void testIteratorHasPreviousLargeList() {
        Random rng = new Random();
        
        int num = rng.nextInt(5) + 10;
        
        for (int i = 0; i < num; i++) {
            stringList.add("" + i);
        }
        
        ListIterator<String> iterator = stringList.iterator();
        
        for (int i = 0; i < num; i++) {
            iterator.next();
        }
        
        for (int i = 0; i < num; i++) {
            assertTrue(iterator.hasPrevious());
            assertNotNull(iterator.previous()); 
        }
        assertFalse(iterator.hasPrevious());
        assertNull(iterator.previous());
    }


    @Test
    public void testToArray() {
        Random rng = new Random();
        
        int num = rng.nextInt(5) + 10;
        
        for (int i = 0; i < num; i++) {
            stringList.add("" + i);
        }
        
        Object[] array = stringList.toArray(); 
        
        assertSame(array.length, num);
        for (int i = 0; i < num; i++) {
            assertEquals("" + i, array[i]);
        }
    }

    @Test
    public void testToArrayTArray() {
        Random rng = new Random();
        
        int num = rng.nextInt(5) + 10;
        
        for (int i = 0; i < num; i++) {
            stringList.add("" + i);
        }
        
        String[] array = new String[1];
        array = stringList.toArray(array); 
        
        assertSame(array.length, num);
        for (int i = 0; i < num; i++) {
            assertEquals("" + i, array[i]);
        }
    }


    @Test
    public void testRemoveEveryItem() {
        String[] values = {"hello", "hello", "hello"};
        
        for (String d : values) {
            stringList.add(d);
        }
        
        stringList.remove("hello");
        assertTrue(stringList.isEmpty());
    }
    
    @Test
    public void testRemoveItemMultipleTimes() {
        String[] values = {"hello", "hello", "test", "hello", 
            "kale", "nomad", "apple", "fish", "hello"};
        
        for (String d : values) {
            stringList.add(d);
        }
        
        stringList.remove("hello");
        assertEquals(stringList.getHead().getData(), "test");
        assertEquals(stringList.getTail().getData(), "fish");
        assertTrue(stringList.size() == 5);
    }
    
    


    @Test
    public void testContainsAllLargeList() {
        Random rng = new Random();
        
        int num = rng.nextInt(5) + 10;
        
        for (int i = 0; i < num; i++) {
            stringList.add("" + i);
        }
        
        LinkedList<String> test = new LinkedList<String>("1");
        test.add("2");
        test.add("3");
        
        assertTrue(stringList.containsAll(test));
        
        test.add("99");
        assertFalse(stringList.containsAll(test));
    }

    @Test
    public void testAddAll() {
        Random rng = new Random();
        int num = rng.nextInt(5) + 10;
        LinkedList<String> test = new LinkedList<>();
        
        
        for (int i = 0; i < num; i++) {
            test.add("" + i);
        }
        
        stringList.addAll(test);
        
        ListIterator<String> stringListIterator = stringList.iterator();
        ListIterator<String> testIterator = test.iterator(); 
        
        for (int i = 0; i < test.size(); i++) {
            
            assertEquals(stringListIterator.next(), testIterator.next());
        }
    }

    @Test
    public void testRetainAllEmptyList() {
        LinkedList<String> test = new LinkedList<>("test");
        stringList.retainAll(test);
        assertEquals(stringList.getHead(), null);
    }
    
    @Test
    public void testRetainAllListSizeOne() {
        LinkedList<String> test = new LinkedList<>("test");
        stringList.add("test");
        stringList.retainAll(test);
        assertEquals(stringList.getHead().getData(), "test");
    }
    
    @Test
    public void testRetainAllLargeList() {
        LinkedList<String> test = new LinkedList<>("test");
        stringList.add("test");
        stringList.retainAll(test);
        assertEquals(stringList.getHead().getData(), "test");
    }

    @Test
    public void testClear() {
        Random rng = new Random();
        
        int num = rng.nextInt(5) + 10;
        
        for (int i = 0; i < num; i++) {
            stringList.add("" + i);
        }
        
        stringList.clear();
        
        assertSame(stringList.size(), 0);
        assertNull(stringList.getHead());
        assertNull(stringList.getTail());
    }

}
