package com.mycompany.marketsurveys.errorHandling.exceptions;

import java.util.HashSet;
import java.util.Set;

public class ValidationException extends RuntimeException {

    Set<String> messages;

    public ValidationException(Set<String> messages){
        this.messages = messages;
    }

    public Set<String> getMessages() {
        return new HashSet<>(messages);
    }
}
