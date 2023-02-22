package org.houri.QuickPoll.handler;

import org.houri.QuickPoll.dto.Error;
import org.houri.QuickPoll.dto.ValidationError;
import org.houri.QuickPoll.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandlers extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException (ResourceNotFoundException exception,
                                                              HttpServletRequest request) {
        Error error = new Error();
        error.setTimeStamp(new Date().getTime());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTitle("Resource not found");
        error.setDetail(exception.getMessage());
        error.setDeveloperMessage(exception.getClass().getName());

        return new ResponseEntity<>(error,null,HttpStatus.NOT_FOUND);

    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders
            headers,
            HttpStatus status, WebRequest request) {
        Error errorDetail = new Error();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName());
        return handleExceptionInternal(ex, errorDetail, headers,
                status, request);
    }

   /* @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError (MethodArgumentNotValidException manve,
                                                    HttpServletRequest request) {
        Error error = new Error();
        error.setTimeStamp(new Date().getTime());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
         String requestPath = (String)request.getAttribute("javax.servlet.error. request_uri");

         if(requestPath == null) {
             requestPath = request.getRequestURI();
         }
        error.setTitle("Validation Failed");
        error.setDetail("Input validation failed");
        error.setDeveloperMessage(manve.getClass().getName());
        // Create ValidationError instances
        List<FieldError> fieldErrors = manve.getBindingResult().
                getFieldErrors();
        for(FieldError fe : fieldErrors) {
            List<ValidationError> validationErrorList = error.getErrors().get
                    (fe.getField());
            if(validationErrorList == null) {
                validationErrorList = new
                        ArrayList<ValidationError>();

                error.getErrors().put(fe.getField(), validationErrorList);
            }
            ValidationError validationError = new
                    ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(fe.getDefaultMessage());
            validationErrorList.add(validationError);
        }
        return new ResponseEntity<>(error, null, HttpStatus.
                BAD_REQUEST);
    }*/


    public ResponseEntity<Object> handleValidationError (MethodArgumentNotValidException manve,
                                                    HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        Error error = new Error();
        error.setTimeStamp(new Date().getTime());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
       // String requestPath = (String)request.getAttribute("javax.servlet.error. request_uri");

       /* if(requestPath == null) {
            requestPath = request.getRequestURI();
        }*/
        error.setTitle("Validation Failed");
        error.setDetail("Input validation failed");
        error.setDeveloperMessage(manve.getClass().getName());
        // Create ValidationError instances
        List<FieldError> fieldErrors = manve.getBindingResult().
                getFieldErrors();
        for(FieldError fe : fieldErrors) {
            List<ValidationError> validationErrorList = error.getErrors().get
                    (fe.getField());
            if(validationErrorList == null) {
                validationErrorList = new
                        ArrayList<ValidationError>();

                error.getErrors().put(fe.getField(), validationErrorList);
            }
            ValidationError validationError = new
                    ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(fe.getDefaultMessage());
            validationErrorList.add(validationError);
        }
        return handleExceptionInternal(manve,error, headers,
                status,request);
    }
    }

