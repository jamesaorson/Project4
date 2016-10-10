package project4;

/**
  * This class implements the queue interface with the use of an array.
  *
  * @author James Osborne
  * @version 1.0 
  * File: ArrayQueue.java
  * Created:  10/7/2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     07 Oct 2016 – JAO – Added body to constructors, enqueue, dequeue, front,
  *     isEmpty, and size.
  *     08 Oct 2016 - JAO - Added jUnit testing and expansion of array.
  *     10 Oct 2016 - JAO - Changed declaration of elements array from
  *     private E[] elements to private Object[] elements to fix some
  *     typing issues present within the constructor.
  * 
  * Description: This class provides the implementations for a queue
  * defined by Queue.java within this package. This queue is also usable with
  * any data type, for it implements generic typing, and is expandable.
  * 
  * @param <E> The chosen type for the queue will go inside the angle brackets.
  */
public class ArrayQueue<E> implements Queue<E> {
    private Object[] elements;
    private int capacity;
    private int numOfElements;
    
    public <E>ArrayQueue() {
        this(100);
    }
    
    public <E>ArrayQueue(int cap) {
        capacity = cap;
        elements = (E[])new Object[capacity];
        numOfElements = 0;
    }
    
    public void enqueue (E element) throws InvalidDataException {
        if (element == null) {
            throw new InvalidDataException("Null input is not valid");
        }
        
        if (numOfElements == capacity) {
            E[] tempArray;
            tempArray  = (E[])new Object[numOfElements];
            
            for (int i = 0; i < numOfElements; ++i) {
                tempArray[i] = (E)elements[i];
            }
            
            capacity += 10;
            elements = (E[])new Object[capacity];
            
            for (int i = 0; i < numOfElements; ++i) {
                elements[i] = tempArray[i];
            }
        }
        
        elements[numOfElements] = element;
        ++numOfElements;
    }
    
    public E dequeue () throws QueueEmptyException {
        if (numOfElements == 0) {
            throw new QueueEmptyException("Queue is empty");
        }
        else {
            E result = (E)elements[0];
            
            for (int i = 0; i < numOfElements - 1; ++i) {
                elements[i] = elements[i + 1];
            }
            elements[numOfElements - 1] = null;
            
            --numOfElements;
            
            return result;
        }
    }
    
    public E front() throws QueueEmptyException {
        if (numOfElements == 0) {
            throw new QueueEmptyException("Queue is empty");
        }
        else {
            return (E)elements[0];
        }
    }
    
    public int size() {
        return numOfElements;
    }
    
    public boolean isEmpty() {
        return numOfElements == 0;
    }
}