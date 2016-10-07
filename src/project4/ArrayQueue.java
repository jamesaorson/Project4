package project4;

/**
  * What does it do?
  *
  * @author James Osborne
  * @version 1.0 
  * File: ArrayQueue.java
  * Created:  10/7/2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     XX month XXXX – JAO – 
  * 
  * Description: 
  * @param <E> 
  */
public class ArrayQueue<E> implements Queue<E> {

    private E[] elements;
    private int capacity;
    private int numOfElements;
    
    public ArrayQueue() {
        this(100);
    }
    
    public ArrayQueue(int cap) {
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
                tempArray[i] = elements[i];
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
            E result = elements[0];
            
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
            return elements[0];
        }
    }
    
    public int size() {
        return numOfElements;
    }
    
    public boolean isEmpty() {
        return numOfElements == 0;
    }
}