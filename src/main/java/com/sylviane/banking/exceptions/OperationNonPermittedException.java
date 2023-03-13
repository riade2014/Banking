package com.sylviane.banking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OperationNonPermittedException extends RuntimeException{
    private final String errorMsg;
    private final String operationId;
    private final String source;
    private final String dependency;
}
