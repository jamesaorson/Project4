package project4;

/**
  * This class implements the InvalidDataException for the ArrayQueue.
  *
  * @author James Osborne
  * @version 1.0 
  * File: InvalidQueueException.java
  * Created:  07 Oct 2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     07 Oct 2016 – JAO – Created constructor with error message parameter.
  * 
  * Description: This class provides an exception for the situation where
  * the user tries to put a null element into the ArrayQueue with enqueue().
  */
class InvalidDataException extends Exception {
    InvalidDataException(String err) {
        super(err);
    }
}
