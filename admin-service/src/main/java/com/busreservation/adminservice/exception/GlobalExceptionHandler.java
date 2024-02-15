package com.busreservation.adminservice.exception;

import com.busreservation.adminservice.valueobjects.CustomErrorResponseVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CustomErrorResponseVO> handleGlobalExceptions(Exception e) {
        CustomErrorResponseVO error = new CustomErrorResponseVO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
        error.setStatus((HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<CustomErrorResponseVO> handleNotFoundException(NotFoundException e) {
        CustomErrorResponseVO error = new CustomErrorResponseVO(HttpStatus.NOT_FOUND.toString(), e.getMessage());
        error.setStatus((HttpStatus.NOT_FOUND.value()));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RouteAlreadyExists.class)
    public ResponseEntity<CustomErrorResponseVO> handleDuplicateDataException(RouteAlreadyExists e) {
        CustomErrorResponseVO error = new CustomErrorResponseVO(HttpStatus.BAD_REQUEST.toString()
                                                                                , e.getMessage());
        error.setStatus((HttpStatus.BAD_REQUEST.value()));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationsErrorList = ex.getBindingResult().getAllErrors();
        validationsErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMessage);
        });
        return new ResponseEntity(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
