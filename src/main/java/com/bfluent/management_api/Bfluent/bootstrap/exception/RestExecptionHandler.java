package com.bfluent.management_api.Bfluent.bootstrap.exception;

import com.bfluent.management_api.Bfluent.domain.exceptions.AlreadyExistsException;
import com.bfluent.management_api.Bfluent.domain.exceptions.BusinessException;
import com.bfluent.management_api.Bfluent.domain.exceptions.EntityNotFound;
import com.bfluent.management_api.Bfluent.domain.exceptions.GenerateTokenException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExecptionHandler extends ResponseEntityExceptionHandler {

    private final String GENERIC_ERROR_MESSAGE = "An error occurred, please try again later.";
    private final MessageSource messageSource;

    public RestExecptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Problem> handleBusinessException(BusinessException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        Problem problem = Problem.builder()
                .status(status.value())
                .title("Business rule violation")
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<Problem> handleEntityNotFoundException(EntityNotFound ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        Problem problem = Problem.builder()
                .status(status.value())
                .title("Entity not found")
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Problem> handleAlreadyExistsException(AlreadyExistsException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        Problem problem = Problem.builder()
                .status(status.value())
                .title("Entity already exists")
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Problem> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        Problem problem = Problem.builder()
                .status(status.value())
                .title("Unauthorized access")
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Problem> handleGenericException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Problem problem = Problem.builder()
                .status(status.value())
                .title("Internal server error")
                .detail(GENERIC_ERROR_MESSAGE)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Problem> handleBadCredentialsException(BadCredentialsException ex) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        Problem problem = Problem.builder()
                .status(status.value())
                .title("Unauthorized access")
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(GenerateTokenException.class)
    public ResponseEntity<Problem> handleGenerateTokenException(GenerateTokenException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Problem problem = Problem.builder()
                .status(status.value())
                .title("Internal server error")
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(problem);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus httpStatus = (HttpStatus) status;
        BindingResult bindingResult = ex.getBindingResult();
        List<Problem.Field> problemFields = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError ->{
                            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                            return Problem.Field.builder()
                                    .name(fieldError.getField())
                                    .userMessage(message)
                                    .build();
                        }
                )
                .collect(Collectors.toList());


        Problem problem = Problem.builder()
                .status(httpStatus.value())
                .title("Invalid Params")
                .detail("One or more fields are invalid. Fill in correctly and try again")
                .timestamp(LocalDateTime.now())
                .fields(problemFields)
                .build();
        return handleExceptionInternal(ex,problem,headers,httpStatus,request);
    }
}
