package project4;

/**
  * This class implements the queue interface with the use of an array.
  *
  * @author James Osborne
  * @version 1.0 
  * File: ArrayQueue.java
  * Created:  07 Oct 2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     07 Oct 2016 – JAO – Added body to constructors, enqueue, dequeue, front,
  *     isEmpty, and size.
  *     08 Oct 2016 - JAO - Added jUnit testing and expansion of array.
  *     10 Oct 2016 - JAO - Changed declaration of elements array from
  *     private E[] elements to private Object[] elements to fix some
  *     typing issues present within the constructor.
  *     11 Oct 2016 - JAO - Changed method of array expansion to what is
  *     demonstrated within the slides for the course. Removed (E[]) casting
  *     on elements array: simply making it an Object array now.
  *     17 Oct 2016 - JAO - Converted it to a circular array.
  *     18 Oct 2016 - JAO - Removed rearIndex variable and only use a frontIndex
  *     and size variable. Used mod operations rather than complex if-elses.
  * 
  * Description: This class provides the implementations for a queue
  * defined by Queue.java within this package. This queue is also usable with
  * any data type, for it implements generic typing, and is expandable.
  * 
  * @param <E> The chosen type for the queue will go inside the angle brackets.
  */
public class ArrayQueue<E> implements Queue<E> {
    private Object[] elements;
    private int size;
    private int frontIndex;
    
    //Default starting size of queue is set to 100.
    public <E>ArrayQueue() {
        this(100);
    }
    
    /**
      * Constructor which specifies a starting capacity for the queue.
      *
      * @param cap The declared starting size of the queue.
      */
    public <E>ArrayQueue(int cap) {
        elements = new Object[cap];
        size = 0;
        frontIndex = 0;
    }
    
    /**
      * Puts a single variable of data into the queue.
      *
      * @param element The data to be entered into the queue.
      * @throws InvalidDataException If element is null.
      */
    public void enqueue (E element) throws InvalidDataException {
        if (element == null) {
            throw new InvalidDataException("Null input is not valid");
        }
        
        //Checks if array is full and needs expanded.
        if (size == elements.length) {
            //Double the current capacity for expansion.
            Object[] newArray = new Object[elements.length * 2];
            
            //Copies elements to a new array, with front index being 0 again.
            for (int i = 0; i < size; ++i) {
                newArray[i] = elements[(frontIndex + i) % elements.length];
            }
            
            //Set front index to front of array.
            frontIndex = 0;

            //Copy new array into old.
            elements = newArray;
        }
        
        //Inserts the element and increments the tracked number of elements.
        elements[(frontIndex + size) % elements.length] = element;
        ++size;
    }
    
    /**
      * Removes top element from the queue and returns it.
      *
      * @return Top element of the queue.
      * @throws QueueEmptyException If queue is empty before element is removed.
      */
    public E dequeue () throws QueueEmptyException {
        if (size == 0) {
            throw new QueueEmptyException("Queue is empty");
        }
        else {
            //Get resulting element from queue, null its array location, and
            //decrement the size.
            E result = (E)elements[frontIndex];
            
            elements[frontIndex] = null;
            --size;
            
            //Set front index to next spot now. This works also for when the
            //queue is empty after the dequeue, keeping its circular nature.
            frontIndex = (frontIndex + 1) % elements.length;
            
            return result;
        }
    }
    
    /**
      * Returns top element of the queue without removing it.
      *
      * @return Top element of the queue.
      * @throws QueueEmptyException If queue is empty.
      */
    public E front() throws QueueEmptyException {
        if (size == 0) {
            throw new QueueEmptyException("Queue is empty");
        }
        else {
            return (E)elements[frontIndex];
        }
    }
    
    /**
      * Returns the number of elements currently in the queue.
      *
      * @return Number of elements in the queue.
      */
    public int size() {
        return size;
    }
    
    /**
      * Tells whether or not the queue is empty of elements, 
      * regardless of capacity.
      *
      * @return True if the queue is empty, and false otherwise.
      */
    public boolean isEmpty() {
        return size == 0;
    }
}