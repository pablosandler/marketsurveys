package com.mycompany.marketsurveys.validations;

import java.util.Optional;

public class ValidationUtils {

    /*private void checkRange(List<String> errors, Optional<Long> from, Optional<Long> to, String message) {
        if(from.isPresent() && to.isPresent() && to.get()>from.get()){
            errors.add(message);
        }
    }*/

    /*private void checkLower(List<String> errors, Optional<Long> data, long value, String message) {
        if(data.isPresent() && data.get()<value){
            errors.add(message);
        }
    }*/

    public static Optional<String> checkRange(Optional<Long> from, Optional<Long> to, String message) {
        if(from.isPresent() && to.isPresent() && from.get()>to.get()){
            return Optional.ofNullable(message);
        } else{
            return Optional.empty();
        }
    }

    public static Optional<String> checkLargerThan(Optional<Long> data, long value, String message) {
        if(data.isPresent() && data.get()<value){
            return Optional.ofNullable(message);
        } else{
            return Optional.empty();
        }
    }
}
