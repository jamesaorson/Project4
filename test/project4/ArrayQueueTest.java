package project4;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ArrayQueueTest {
    
    //Rule that will be used to check exception type and exception messages.
    @Rule public ExpectedException expected = ExpectedException.none();
    
    /**
     * Test of enqueue and dequeue method, of class ArrayQueue.
     */
    @Test
    public void testEnqueueAndDequeue() throws InvalidDataException, QueueEmptyException {
        ArrayQueue<Integer> testQueue = new ArrayQueue<Integer>(1);
    
        testQueue.enqueue(1);
        
        //Check the expected size and front after the enqueue.
        assertEquals(1, testQueue.size());
        assertEquals(1, (int)testQueue.front());
        
        //Empty the queue for expansion testing.
        testQueue.dequeue();
        
        //Fills queue with 1000 elements, expanding queue from size 1 to a size
        //which will hold the 1000 elements. Because I expand array by doubling
        //initial capacity, the array for the queue should have
        //a capacity of 1024.
        for (int i = 0; i < 1000; ++i) {
            //Make certain that enqueue increments size correctly as it goes.
            assertEquals(i, testQueue.size());
            
            testQueue.enqueue(i);
        }
        
        assertEquals(1000, testQueue.size());
        
        //Dequeues 100 elements so front index is not at 0 index of array.
        for (int i = 0; i < 100; ++i) {
            //Make certain that dequeue decrements size correctly as it goes.
            assertEquals(1000 - i, testQueue.size());
            assertEquals(i, (int)testQueue.dequeue());
        }
        
        //Enqueues 500 elements so array must wrap around and then expand 
        //while wrapped.
        for (int i = 900; i < 1400; ++i) {
            assertEquals(i, testQueue.size());
            
            //Must add 100 because first 100 elements have been removed already.
            testQueue.enqueue(i + 100);
        }
        
        //Check ending size after enqueues.
        assertEquals(1400, testQueue.size());
        
        //Dequeue the rest of the queue.
        for (int i = 100; i < 1500; ++i) {
            assertEquals(1400 - i + 100, testQueue.size());
            assertEquals(i, (int)testQueue.dequeue());
        }
    }

    /**
     * Test for InvalidDataException with enqueue.
     */
    @Test
    public void testNullInput() throws InvalidDataException {
        ArrayQueue testQueue = new ArrayQueue();
        
        Integer i = null;
        
        expected.expect(InvalidDataException.class);
        expected.expectMessage("Null input is not valid");
        
        testQueue.enqueue(null);
    }
    
    /**
     * Test for QueueEmptyException with dequeue.
     */
    @Test
    public void testDequeueOnEmpty() throws InvalidDataException,
                                            QueueEmptyException {
        ArrayQueue testQueue = new ArrayQueue();
        
        expected.expect(QueueEmptyException.class);
        expected.expectMessage("Queue is empty");
        
        testQueue.dequeue();
    }

    /**
     * Test of front method, including its exception throw, of class ArrayQueue.
     */
    @Test
    public void testFront() throws InvalidDataException, QueueEmptyException {
        ArrayQueue<Integer> testQueue = new ArrayQueue<Integer>();
        
        for (int i = 0; i < 100; ++i) {
            testQueue.enqueue(i);
        }
        
        //Test the front of the queue for all 100 elements' dequeueing.
        for (int expFront = 0; expFront < 100; ++expFront) {
            assertEquals(expFront, (int)testQueue.front());
            testQueue.dequeue();
        }
        
        //Using front on an empty queue should throw this exception.
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
        
        //Check size before every enqueue and once after the last enqueue.
        for (int i = 0; i < 100; ++i) {
            assertEquals(i, testQueue.size());
            testQueue.enqueue(1);
        }
        
        assertEquals(100, testQueue.size());
        
        //Check size before every dequeue and once after the last dequeue.
        for (int i = 0; i < 100; ++i) {
            assertEquals(100 - i, testQueue.size());
            testQueue.dequeue();
        }
        
        assertEquals(0, testQueue.size());
    }

    /**
     * Test of isEmpty method, of class ArrayQueue.
     */
    @Test
    public void testIsEmpty() throws InvalidDataException, QueueEmptyException {
        ArrayQueue<Integer> testQueue = new ArrayQueue<Integer>();
        
        //Array should be empty before the first enqueue.
        assertEquals(true, testQueue.isEmpty());
        
        for (int i = 0; i < 100; ++i) {
            testQueue.enqueue(1);
            
            //The queue should not be empty after enqueues,
            //without an equal number of following dequeues.
            assertEquals(false, testQueue.isEmpty());
        }
        
        for (int i = 0; i < 100; ++i) {
            //Queue should not be empty until the last dequeue is ran.
            assertEquals(false, testQueue.isEmpty());
            
            testQueue.dequeue();
        }
        
        //Queue should be empty after the dequeue of all of its elements.
        assertEquals(true, testQueue.isEmpty());        
    }
    
    /**
     * Test queues of different types and even an Object queue.
     */
    @Test
    public void testDifferentTypes() throws InvalidDataException,
                                            QueueEmptyException {
        //Integer queue testing.
        ArrayQueue<Integer> intQueue = new ArrayQueue<Integer>();
        int expIntFront = 1;
        
        intQueue.enqueue(1);
        
        assertEquals(expIntFront, (int)intQueue.front());
        
        //Double queue testing.
        ArrayQueue<Double> doubleQueue = new ArrayQueue<Double>();
        double expDoubleFront = 1.5;
        
        doubleQueue.enqueue(1.5);
        
        assertEquals(expDoubleFront, (double)doubleQueue.front(), 0.0000001);
        
        //String queue testing.
        ArrayQueue<String> stringQueue = new ArrayQueue<String>();
        String expStringFront = "hi mom";
        
        stringQueue.enqueue("hi mom");
        
        assertEquals(expStringFront, (String)stringQueue.front());
        
        //Object queue testing.
        ArrayQueue objectQueue = new ArrayQueue();
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