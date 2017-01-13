package com.tqmars.cardrecycle.infrastructure.mybatis.repositories.exceptions;

/**
 * Created by jjh on 1/11/17.
 */
public class ApplicationException extends RuntimeException{
    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }
}
