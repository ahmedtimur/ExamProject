package com.examproject.exceptions;

public class NotFoundException1 extends RuntimeException{

    public NotFoundException1() {
    }

    public NotFoundException1(String message) {
        super(message);
    }
}
