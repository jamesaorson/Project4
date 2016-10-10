package project4;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ArrayQueueTest {
    
    public ArrayQueueTest() {
    }
    
    @Rule public ExpectedException expected = ExpectedException.none();
    
    /**
     * Test of enqueue method, of class ArrayQueue.
     */
    @Test
    public void testEnqueue() throws InvalidDataException, QueueEmptyException {
        ArrayQueue<Integer> testQueue = new ArrayQueue<Integer>(1);
        int expSize = 1;
        int expFront = 1;
        
        testQueue.enqueue(1);
        
        assertEquals(expSize, testQueue.size());
        assertEquals(expFront, (int)testQueue.front());
        
        testQueue.dequeue();
        
        for (int i = 0; i < 1000; ++i) {
            testQueue.enqueue(i);
        }
        
        for (int i = 0; i < 1000; ++i) {
            assertEquals(i, (int)testQueue.dequeue());
        }
        
        expected.expect(InvalidDataException.class);
        expected.expectMessage("Null input is not valid");
        
        Object invalidElement = null;
        testQueue.enqueue((Integer)invalidElement);
    }

    /**
     * Test of dequeue method, of class ArrayQueue.
     */
    @Test
    public void testDequeue() throws QueueEmptyException, InvalidDataException {
        ArrayQueue<Integer> testQueue = new ArrayQueue<Integer>();
        
        testQueue.enqueue(1);
        testQueue.enqueue(2);
        
        int expDequeueResult = 1;
        int expNewFront = 2;
        int expNewSize = 1;
                
        assertEquals(expDequeueResult, (int)testQueue.dequeue());
        assertEquals(expNewFront, (int)testQueue.front());
        assertEquals(expNewSize, testQueue.size());
        
        expected.expect(QueueEmptyException.class);
        expected.expectMessage("Queue is empty");
        
        testQueue.dequeue();
        testQueue.dequeue();
    }

    /**
     * Test of front method, of class ArrayQueue.
     */
    @Test
    public void testFront() throws InvalidDataException, QueueEmptyException {
        ArrayQueue<Integer> testQueue = new ArrayQueue<Integer>();
        
        for (int i = 0; i < 100; ++i) {
            testQueue.enqueue(i);
        }
        
        for (int expFront = 0; expFront < 100; ++expFront) {
            assertEquals(expFront, (int)testQueue.front());
            testQueue.dequeue();
        }
        
        expected.expect(QueueEmptyException.class);
        expected.expectMessage("Queue is empty");
        
        testQueue.front();
    }

    /**
     * Test of size method, of class ArrayQueue.
     */
    @Test
    public void testSize() throws InvalidDataException, QueueEmptyException {
        ArrayQueue<Integer> testQueue = new ArrayQueue<Integer>();
        
        int expResult = 0;
        
        assertEquals(expResult, testQueue.size());
        
        try {
            testQueue.dequeue();
        }
        
        catch (QueueEmptyException e) {
            assertEquals(expResult, testQueue.size());
        }
        
        finally {     
            for (int i = 0; i < 100; ++i) {
                testQueue.enqueue(1);
            }

            expResult = 100;

            assertEquals(expResult, testQueue.size());
        }
    }

    /**
     * Test of isEmpty method, of class ArrayQueue.
     */
    @Test
    public void testIsEmpty() throws InvalidDataException {
        ArrayQueue<Integer> testQueue = new ArrayQueue<Integer>();
        
        boolean expResult = true;
        boolean result = testQueue.isEmpty();
        
        assertEquals(expResult, result);
        
        testQueue.enqueue(1);
        
        expResult = false;
        
        assertEquals(expResult, testQueue.isEmpty());
    }
    
    /**
     * Test queues of different types and even an Object queue.
     */
    @Test
    public void testDifferentTypes() throws InvalidDataException,
                                            QueueEmptyException {
        ArrayQueue<Integer> intQueue = new ArrayQueue<Integer>();
        int expIntFront = 1;
        
        intQueue.enqueue(1);
        
        assertEquals(expIntFront, (int)intQueue.front());
        
        ArrayQueue<Double> doubleQueue = new ArrayQueue<Double>();
        double expDoubleFront = 1.5;
        
        doubleQueue.enqueue(1.5);
        
        assertEquals(expDoubleFront, (double)doubleQueue.front(), 0.0000001);
        
        ArrayQueue<String> stringQueue = new ArrayQueue<String>();
        String expStringFront = "hi mom";
        
        stringQueue.enqueue("hi mom");
        
        assertEquals(expStringFront, (String)stringQueue.front());
        
        ArrayQueue<Object> objectQueue = new ArrayQueue<Object>();
        int expObjIntFront = 1;
        double expObjDoubleFront = 1.5;
        String expObjStringFront = "hi mom";
        
        objectQueue.enqueue(1);
        objectQueue.enqueue(1.5);
        objectQueue.enqueue("hi mom");
        
        assertEquals(expObjIntFront, (int)objectQueue.front()); 
        objectQueue.dequeue();
        assertEquals(expObjDoubleFront, (double)objectQueue.front(), 0.0000001);
        objectQueue.dequeue();
        assertEquals(expObjStringFront, (String)objectQueue.front());
    }
}