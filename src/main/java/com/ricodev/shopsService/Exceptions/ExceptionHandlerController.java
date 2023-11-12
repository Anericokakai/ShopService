package com.ricodev.shopsService.Exceptions;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingFormatArgumentException;

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





//    ! missing servlet request parameter exception

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)

    public  Map<String ,String > misssingParameter(MissingServletRequestParameterException ex){
        Map<String ,String > errorMap= new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }
//    message not readable exception

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String,String> messageNotRedableExceptionHanlder(HttpMessageNotReadableException ex){
        Map<String,String > errorMap= new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundException.class)
    public Map<String ,String > notFoundHanlder(NotFoundException ex){

        Map<String ,String > errMap=new HashMap<>();

         errMap.put("errorMessage", ex.getMessage());
         return errMap;
    }

//    ! handle feign client 400 respose

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(FeignException.BadRequest.class )
    public Map<String,String > feignBadRequst(FeignException ex){

        Map<String ,String > errorMap= new HashMap<>();

        errorMap.put("productsMessage",getMessage(ex.contentUTF8()));
        errorMap.put("shopMessage","store was  deleted successfully");
        return errorMap;

    }
    private  String getMessage(String  res){
        return  res.substring(res.indexOf("\"errorMessage\":")+16,res.indexOf("\"}"));
    }

//    Service not available for feign client

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public Map<String ,String > serviceUnavailbale(FeignException ex){

        Map<String ,String > erorMap= new HashMap<>();
        erorMap.put("errorMessage", ex.contentUTF8());

        return  erorMap;

    }




}
