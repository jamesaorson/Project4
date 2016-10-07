package project4;

/**
  * What does it do?
  *
  * @author James Osborne
  * @version 1.0 
  * File: <filename>
  * Created:  <current date>
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     XX month XXXX – JAO – 
  * 
  * Description: 
  */
public interface Queue<E> {
    public void enqueue (E element) throws InvalidDataException;
    public E dequeue () throws QueueEmptyException;
    public E front () throws QueueEmptyException;
    public int size();
    public boolean isEmpty();
}