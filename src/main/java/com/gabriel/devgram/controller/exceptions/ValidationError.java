package com.gabriel.devgram.controller.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1983364327168356847L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timeStamp, Integer status, String error, String message, String path) {
        super(timeStamp, status, error, message, path);
    }

    public ValidationError(Long timeStamp, Integer status, String error, String message, String path, List<FieldMessage> errors) {
        super(timeStamp, status, error, message, path);
        this.errors = errors;
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName, String message) {
        this.errors.add(new FieldMessage(message, fieldName));
    }
}