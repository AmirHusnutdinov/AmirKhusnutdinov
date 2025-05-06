package org.example.exception;

import lombok.experimental.StandardException;

@StandardException
public class BucketNotFoundException extends Exception {

  public BucketNotFoundException(String bucketNotFound) {

  }
}