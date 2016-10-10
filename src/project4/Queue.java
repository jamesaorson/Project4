package project4;

/**
  * Provides an interface for basic supported functions of a Queue.
  *
  * @author James Osborne
  * @version 1.0 
  * File: <filename>
  * Created:  <current date>
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     07 Oct 2016 – JAO – Created methods of the interface.
  * 
  * Description: This provides all of the basic methods necessary within any
  * queue that would be implemented, regardless of its internal data structure.
  */
public interface Queue<E> {
    public void enqueue (E element) throws InvalidDataException;
    public E dequeue () throws QueueEmptyException;
    public E front () throws QueueEmptyException;
    public int size();
    public boolean isEmpty();
}