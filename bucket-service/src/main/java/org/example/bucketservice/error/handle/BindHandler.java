package org.example.bucketservice.error.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class BindHandler {

    private final MessageSource messageSource;

    @Autowired
    public BindHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindException e, Locale locale){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                messageSource.getMessage("message.error.item.bad_request", new Object[0],
                        "message.error.item.bad_request", locale)
        );
        problemDetail.setProperty("errors", e.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList());
        return ResponseEntity.badRequest()
                .body(problemDetail);
    }
}
