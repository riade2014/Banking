package com.sylviane.banking.validators;

import com.sylviane.banking.exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T> {//classe generique de validation

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T objectToValidate){
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        if(!violations.isEmpty()){//on verifie si on a des violations
            Set<String> errorMessage =  violations.stream()//si on a des violations, on recupere le message
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectValidationException(errorMessage,objectToValidate.getClass().getName());
        }
    }
}
