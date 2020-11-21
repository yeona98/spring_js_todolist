package com.example.demo.todolist.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String fieldName;
    private String fieldValue;
    private Object value;

    public ResourceNotFoundException(String fieldName, String fieldValue, Object value) {
        super(String.format("field: %s, value: %s, input: %s", fieldName,fieldValue, value));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.value = value;
    }
}
