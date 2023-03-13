package com.sylviane.banking.handlers;

import com.sylviane.banking.exceptions.ObjectValidationException;
import com.sylviane.banking.exceptions.OperationNonPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//permet de preciser à spring que c'est le gestionnaire d'exception globale
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectValidationException.class)//permet de catcher une exception
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Une exception d’objet non valide s’est produite")
                .errorSource(exception.getViolationSource())
                .validationErrors(exception.getViolations())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException exception){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(representation);
    }
    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(OperationNonPermittedException exception){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getErrorMsg())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Un utilisateur existe déjà avec cet email")
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionRepresentation> handleDisabledException(){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Vous ne pouvez pas acceder à votre compte car votre compte n'est pas encore activé")
                .build();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(representation);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionRepresentation> handleBadCredentialsException(){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Votre mail et/ou mot de passe est incorrecte ")
                .build();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(representation);
    }
}
