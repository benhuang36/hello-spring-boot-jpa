package com.example.dogservice.exception;

import com.example.dogservice.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class AllExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<BaseResponse> unknownException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(new BaseResponse(500, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    protected ResponseEntity<BaseResponse> numberException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(new BaseResponse(401, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
