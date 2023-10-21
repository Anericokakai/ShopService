package com.ShopsService.shopsService.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController  {

//    ! handle entity not found exception

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)

    public Map<String,String> handleEntityNotFoundExc(EntityNotFoundException ex){

        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;


    }

//    ! handle bad inputs exceptions

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)

public Map<String,String> handlBadCredentialsExc(MethodArgumentNotValidException ex){

        Map<String,String > errorMap= new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });

        return  errorMap;
    }


//    message not readable exception

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String,String> messageNotRedableExceptionHanlder(HttpMessageNotReadableException ex){
        Map<String,String > errorMap= new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }





}
