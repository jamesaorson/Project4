package project4;

/**
  * This class implements the QueueEmptyException for the ArrayQueue.
  *
  * @author James Osborne
  * @version 1.0 
  * File: QueueEmptyException.java
  * Created:  07 Oct 2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     07 Oct 2016 – JAO – Created constructor with error message parameter.
  * 
  * Description: This class provides an exception for the situation whenever
  * dequeue() or front() is called on an empty ArrayQueue.
  */
class QueueEmptyException extends Exception {
    QueueEmptyException(String err) {
        super(err);
    }
}
