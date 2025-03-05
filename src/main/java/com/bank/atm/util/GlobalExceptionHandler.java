package com.bank.atm.util;

import com.bank.atm.exception.*;
import com.bank.atm.models.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = LoginException.class)
    public ResponseEntity<ErrorResponse> loginError(HttpServletRequest req, LoginException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(e.getReason());
        errorResponse.setStatus(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> missingParameterError(HttpServletRequest req, MissingServletRequestParameterException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setStackTrace(Arrays.toString(ex.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> typeMisMatchError(HttpServletRequest req, MethodArgumentTypeMismatchException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setStackTrace(Arrays.toString(ex.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AdminUnAuthException.class)
    public ResponseEntity<ErrorResponse> adminUnAuthorized(HttpServletRequest req, AdminUnAuthException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = BankNotFoundException.class)
    public ResponseEntity<ErrorResponse> bankNotFound(HttpServletRequest req, BankNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(value = BankCreationFailedException.class)
    public ResponseEntity<ErrorResponse> bankCreationFailed(HttpServletRequest req, BankCreationFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BankUnAuthException.class)
    public ResponseEntity<ErrorResponse> bankUnAuthorized(HttpServletRequest req, BankUnAuthException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AtmNotFoundException.class)
    public ResponseEntity<ErrorResponse> atmNotFound(HttpServletRequest req, AtmNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(value = AtmCreationFailedException.class)
    public ResponseEntity<ErrorResponse> atmCreationFailed(HttpServletRequest req, AtmCreationFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AtmUpdateFailedException.class)
    public ResponseEntity<ErrorResponse> atmUpdateFailed(HttpServletRequest req, AtmUpdateFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(ex.getStatus());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
    @ExceptionHandler(value = AtmDeleteFailedException.class)
    public ResponseEntity<ErrorResponse> atmDeleteFailed(HttpServletRequest req, AtmDeleteFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(ex.getStatus());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(value = BankDeleteFailedException.class)
    public ResponseEntity<ErrorResponse> bankDeleteFailed(HttpServletRequest req, BankDeleteFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(ex.getStatus());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(value = AtmFoundException.class)
    public ResponseEntity<ErrorResponse> bankIdFound(HttpServletRequest req, AtmFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.FOUND);
    }

    @ExceptionHandler(value = CustomerCreationFailedException.class)
    public ResponseEntity<ErrorResponse> customerCreationFailed(HttpServletRequest req, CustomerCreationFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CardCreationFailedException.class)
    public ResponseEntity<ErrorResponse> cardCreationFailed(HttpServletRequest req, CardCreationFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CardNotFoundException.class)
    public ResponseEntity<ErrorResponse> cardNotFound(HttpServletRequest req, CardNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PinUpdateFailedException.class)
    public ResponseEntity<ErrorResponse> pinUpdateFailed(HttpServletRequest req, PinUpdateFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(ex.getStatus());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(value = CardBalanceUpdateFailedException.class)
    public ResponseEntity<ErrorResponse> cardBalanceUpdateFailed(HttpServletRequest req, CardBalanceUpdateFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(ex.getStatus());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(value = TransactionsCreationFailedException.class)
    public ResponseEntity<ErrorResponse> transactionCreationFailed(HttpServletRequest req, TransactionsCreationFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CardBalanceNotFoundException.class)
    public ResponseEntity<ErrorResponse> cardBalanceNotFound(HttpServletRequest req, CardBalanceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
