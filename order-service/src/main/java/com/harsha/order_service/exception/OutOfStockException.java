package com.harsha.order_service.exception;

public class OutOfStockException extends RuntimeException {
  public OutOfStockException(String msg) {
    super(msg);
  }
}
