package com.mycompany.marketsurveys.validations;


import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MarketSurveyValidator {

    private static final String AGE_FROM_LOWER_THAN_ZERO_ERROR = "error.age.from.lower.than.zero";
    private static final String AGE_TO_LOWER_THAN_ZERO_ERROR = "error.age.to.lower.than.zero";
    private static final String AGE_RANGE_ERROR = "error.age.range";
    private static final String INCOME_FROM_LOWER_THAN_ZERO_ERROR = "error.income.from.lower.than.zero";
    private static final String INCOME_TO_LOWER_THAN_ZERO_ERROR = "error.income.to.lower.than.zero";
    private static final String INCOME_RANGE_ERROR = "error.income.range";
    private static final String INCOME_CURRENCY_NOT_PRESENT = "error.income.currency.not.present";
    private static final String INCOME_CURRENCY_NOT_FOUND_ERROR = "error.income.currency.not.found";
    private static final String COUNTRY_NOT_FOUND_ERROR = "error.country.not.found";


    public Set<String> validateAgeRange(Long ageFrom, Long ageTo){
        Optional<Long> ageFromOpt = Optional.ofNullable(ageFrom);
        Optional<Long> ageToOpt = Optional.ofNullable(ageTo);

        Set<Optional<String>> errors = new HashSet<>();

        errors.add(ValidationUtils.checkLargerThan(ageFromOpt, 0, AGE_FROM_LOWER_THAN_ZERO_ERROR));
        errors.add(ValidationUtils.checkLargerThan(ageToOpt, 0, AGE_TO_LOWER_THAN_ZERO_ERROR));
        errors.add(ValidationUtils.checkRange(ageFromOpt, ageToOpt, AGE_RANGE_ERROR));

        return errors.stream()
                .filter(m -> m.isPresent())
                .map(m -> m.get())
                .collect(Collectors.toSet());
    }

    public Optional<String> validateCountry(String country){
        Optional<String> validationResult = Optional.empty();
        if(Optional.ofNullable(country).isPresent()){
            boolean result = Arrays.asList(Locale.getISOCountries()).contains(country.toUpperCase());
            if(!result){
                validationResult = Optional.of(COUNTRY_NOT_FOUND_ERROR);
            }
        }
        return validationResult;
    }

    public Set<String> validateIncome(String currency, Long incomeFrom, Long incomeTo){
        Optional<Long> incomeFromOpt = Optional.ofNullable(incomeFrom);
        Optional<Long> incomeToOpt = Optional.ofNullable(incomeTo);
        Optional<String> currencyOpt = Optional.ofNullable(currency);

        Set<Optional<String>> errors = new HashSet<>();

        errors.add(ValidationUtils.checkLargerThan(incomeFromOpt, 0, INCOME_FROM_LOWER_THAN_ZERO_ERROR));
        errors.add(ValidationUtils.checkLargerThan(incomeToOpt, 0, INCOME_TO_LOWER_THAN_ZERO_ERROR));
        errors.add(ValidationUtils.checkRange(incomeFromOpt, incomeToOpt, INCOME_RANGE_ERROR));
        
        Optional<String> currencyValidation = Optional.empty();
        if((incomeFromOpt.isPresent() || incomeToOpt.isPresent()) && !currencyOpt.isPresent()){
            currencyValidation = Optional.of(INCOME_CURRENCY_NOT_PRESENT);
        } else {
            if(currencyOpt.isPresent()){
                boolean result = !Currency.getAvailableCurrencies()
                        .stream()
                        .map(c -> c.getCurrencyCode())
                        .anyMatch(c -> c.equals(currency.toUpperCase()));
                if(result){
                    currencyValidation = Optional.of(INCOME_CURRENCY_NOT_FOUND_ERROR);
                }
            }
        }
        errors.add(currencyValidation);


        return errors.stream()
                .filter(m -> m.isPresent())
                .map(m -> m.get())
                .collect(Collectors.toSet());
    }

}
